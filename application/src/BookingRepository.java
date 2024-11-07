package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private Connection conn;
    private String table = "booking";
    
    public BookingRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Booking> get() {
        ArrayList<Booking> bookings = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                bookings.add(
                    new Booking(
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
        
        return bookings;
    }

    public Booking get(long id) {
        Booking booking = new Booking();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return booking;
    }

    public Booking getByClientId(long id) {
        Booking booking = new Booking();
        try {
            String query = "select * from " + table + " where client_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return booking;
    }

    public Booking getByOfferingId(long id) {
        Booking booking = new Booking();
        try {
            String query = "select * from " + table + " where offering_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return booking;
    }
}