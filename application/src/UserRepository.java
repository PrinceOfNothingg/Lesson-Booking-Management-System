package application.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection conn;
    private String table = "users";

    public UserRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<User> get() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users.add(
                        new User(
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

        return users;
    }

    public User get(long id) {
        User user = new User();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                user = new User(
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

        return user;
    }

    public User get(String name, String phone) {
        User user = new User();
        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                user = new User(
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

        return user;
    }
}
