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
            String query = "select b.id, b.active, b.status as bookingStatus, b.client_id, b.offering_id, o.status, o.taken, l.type, l.mode, l.seats from +"+table+" b join offering o on b.offering_id = o.id join lesson l on o.lesson_id = l.id where b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                bookings.add(
                    new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getLong(5),
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getString(8),
                    rs.getBoolean(9),
                    rs.getInt(10)));
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
            String query = "select b.id, b.active, b.status as bookingStatus, b.client_id, b.offering_id, o.status, o.taken, l.type, l.mode, l.seats from +"+table+" b join offering o on b.offering_id = o.id join lesson l on o.lesson_id = l.id where b.id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getLong(5),
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getString(8),
                    rs.getBoolean(9),
                    rs.getInt(10));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return booking;
    }

    public List<Booking> getByClientId(long id) {
        ArrayList<Booking> bookings = new ArrayList<>();
        try {
            String query = "select b.id, b.active, b.status as bookingStatus, b.client_id, b.offering_id, o.status, o.taken, l.type, l.mode, l.seats from +"+table+" b join offering o on b.offering_id = o.id join lesson l on o.lesson_id = l.id where b.client_id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getLong(5),
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getString(8),
                    rs.getBoolean(9),
                    rs.getInt(10)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    public Booking getByOfferingId(long id) {
        Booking booking = new Booking();
        try {
            String query = "select b.id, b.active, b.status as bookingStatus, b.client_id, b.offering_id, o.status, o.taken, l.type, l.mode, l.seats from +"+table+" b join offering o on b.offering_id = o.id join lesson l on o.lesson_id = l.id where b.offering_id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getLong(5),
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getString(8),
                    rs.getBoolean(9),
                    rs.getInt(10));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return booking;
    }
}
