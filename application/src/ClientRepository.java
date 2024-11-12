package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private Connection conn;
    private String table = "client";
    
    public ClientRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Client> get() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                clients.add(
                    new Client(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getBoolean(7)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }

    public Client get(long id) {
        Client client = new Client();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                client = new Client(
                                    rs.getLong(1),
                                    rs.getBoolean(2),
                                    rs.getString(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    rs.getBoolean(7));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return client;
    }

    public Client get(String name, String phone) {
        Client client = new Client();
        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();
            System.out.println("DEBUG: " + st);

            while (rs.next()) {
                client = new Client(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getBoolean(7));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return client;
    }

    public long insert(Client client) {
        long id = 0;
        try {
            String query = "insert into " + table + " (active, \"name\", age, phone, \"role\", dependant) values (?,?,?,?,?::roleType,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setString(2, client.name);
            st.setInt(3, client.age);
            st.setString(4, client.phone);
            st.setString(5, client.role);
            st.setBoolean(6, client.dependant);
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
