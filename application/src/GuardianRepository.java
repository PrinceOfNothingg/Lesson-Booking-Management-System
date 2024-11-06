package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianRepository {

    private Connection conn;
    private String table = "guardian";
    
    public GuardianRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Guardian> get() {
        ArrayList<Guardian> guardians = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                guardians.add(
                    new Guardian(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return guardians;
    }

    public Guardian get(long id) {
        Guardian guardian = new Guardian();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                guardian = new Guardian(
                                    rs.getLong(1),
                                    rs.getBoolean(2),
                                    rs.getString(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getString(6));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return guardian;
    }

    public Guardian get(String name, String phone) {
        Guardian guardian = new Guardian();
        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                guardian = new Guardian(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return guardian;
    }

    public boolean insert(Guardian guardian) {
        boolean success = false;
        try {
            String query = "insert into " + table + " (active, \"name\", age, phone, \"role\") values (?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setString(2, guardian.name);
            st.setInt(3, guardian.age);
            st.setString(4, guardian.phone);
            st.setString(5, guardian.role);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);
            rs.close();
            st.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return success;
    }
}
