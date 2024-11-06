package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private Connection conn;
    
    public ClientRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Client> get() {
        ArrayList<Client> clients = new ArrayList<>();
        try {

            PreparedStatement st = conn.prepareStatement("select * from client");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                clients.add(
                    new Client(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
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

            PreparedStatement st = conn.prepareStatement("select * from client where id = ? and active = true");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                client = new Client(
                                    rs.getLong(1),
                                    rs.getBoolean(2),
                                    rs.getString(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getBoolean(6));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return client;
    }
}
