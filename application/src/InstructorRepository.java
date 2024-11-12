package application.src;


import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructorRepository {

    private Connection conn;
    private String table = "instructor";
    
    public InstructorRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Instructor> get() {
        ArrayList<Instructor> instructors = new ArrayList<>();
        String[] specs = null;
        String[] avails = null;
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[])rs.getArray(7).getArray();
                avails = (String[])rs.getArray(8).getArray();
                instructors.add(
                    new Instructor(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    new ArrayList<String>(Arrays.asList(specs)),
                    new ArrayList<String>(Arrays.asList(avails))));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return instructors;
    }

    public Instructor get(long id) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;

        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[])rs.getArray(7).getArray();
                avails = (String[])rs.getArray(8).getArray();
                System.out.println("DEBUG: " + rs);
                instructor = new Instructor(
                                    rs.getLong(1),
                                    rs.getBoolean(2),
                                    rs.getString(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    new ArrayList<String>(Arrays.asList(specs)),
                                    new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return instructor;
    }

    public Instructor get(String name, String phone) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;
        
        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                System.out.println("DEBUG: " + rs);
                specs = (String[])rs.getArray(7).getArray();
                avails = (String[])rs.getArray(8).getArray();
                instructor = new Instructor(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                new ArrayList<String>(Arrays.asList(specs)),
                                new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return instructor;
    }

    public long insert(Instructor instructor) {
        Array specs = null;
        Array avails = null;

        long id = 0;
        try {
            String query = "insert into " + table + " (active, \"name\", age, phone, \"role\", specializations, availabilities) values (?,?,?,?,?::roleType,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            specs = (Array) instructor.getSpecializations();
            avails = (Array) instructor.getAvailabilities();
            
            st.setBoolean(1, true);
            st.setString(2, instructor.name);
            st.setInt(4, instructor.age);
            st.setString(3, instructor.phone);
            st.setString(5, instructor.role);
            st.setArray(6, specs);
            st.setArray(7, avails);
            ResultSet rs = st.executeQuery();
            id = rs.getLong(1);
            System.out.println("DEBUG: " + st);
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return id;
    }

    public boolean delete(long id) {
        boolean success = false;
        try {
            String query = "DELETE FROM " + table + " WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            int affectedRows = st.executeUpdate();
            success = affectedRows > 0;
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}
