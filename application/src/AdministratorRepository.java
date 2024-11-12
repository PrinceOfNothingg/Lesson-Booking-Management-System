package application.src;


import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorRepository {

    private Connection conn;
    private String table = "administrator";
    
    public AdministratorRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Administrator> get() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                System.out.println("DEBUG: " + rs);
                administrators.add(
                    new Administrator(
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
        
        return administrators;
    }

    public Administrator get(long id) {
        Administrator administrator = new Administrator();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                System.out.println("DEBUG: " + rs);
                administrator = new Administrator(
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
        
        return administrator;
    }

    public Administrator get(String name, String phone) {
        Administrator administrator = new Administrator();
        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                System.out.println("DEBUG: " + rs);
                administrator = new Administrator(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6));
                System.out.println("DEBUG: " + rs);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return administrator;
    }

    public long insert(Administrator administrator) {
        long id = 0;
        try {
            String query = "insert into " + table + " (active, \"name\", age, phone, \"role\") values (?,?,?,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);            
            st.setBoolean(1, true);
            st.setString(2, administrator.name);
            st.setInt(3, administrator.age);
            st.setString(4, administrator.phone);
            st.setString(5, administrator.role);
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
