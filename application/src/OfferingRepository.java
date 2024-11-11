package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferingRepository {

    private Connection conn;
    private String table = "offering";
    
    public OfferingRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Offering> get() {
        ArrayList<Offering> offerings = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                offerings.add(
                    new Offering(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offerings;
    }

    public Offering get(long id) {
        Offering offering = new Offering();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                offering = new Offering(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offering;
    }

    public List<Offering> getTaken(boolean taken) {
        ArrayList<Offering> offerings = new ArrayList<>();
        try {
            String query = "select * from " + table + " where taken = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, taken);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                offerings.add(
                    new Offering(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offerings;
    }

    public Offering getByBookingId(long bookingId) {
        Offering offering = new Offering();
        try {
            String query = "select * from " + table + " where booking_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, bookingId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                offering = 
                    new Offering(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offering;
    }
}
