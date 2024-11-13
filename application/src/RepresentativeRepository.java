package application.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepresentativeRepository {

    private Connection conn;
    private String table = "representative";

    public RepresentativeRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<Representative> get() {
        ArrayList<Representative> representatives = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                representatives.add(
                        new Representative(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getLong(3),
                                rs.getLong(4),
                                rs.getString(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return representatives;
    }

    public long insert(Guardian guardian, Client dependant) {
        long id = 0;
        try {
            String query = "insert into " + table
                    + " (active, representative_id, client_id, relationshp) values (?,?,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, guardian.getId());
            st.setLong(3, dependant.getId());
            st.setString(4, "child");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            System.out.println("DEBUG: " + st);
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
