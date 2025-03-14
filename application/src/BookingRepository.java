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

    public BookingRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<Booking> get() {
        ArrayList<Booking> bookings = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                bookings.add(new Booking(
                            rs.getLong(1),
                            rs.getBoolean(2),
                            rs.getString(3),
                            rs.getLong(4),
                            rs.getLong(5)));
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
            String query = "select b.* from "
                    + table + " b join offering o on b.offering_id = o.id where b.id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getLong(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public List<Booking> getByClientId(Client client) {
        ArrayList<Booking> bookings = new ArrayList<>();
        try {
            String query = "select b.* from "
                    + table + " b join offering o on b.offering_id = o.id where b.client_id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, client.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getLong(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public Booking getByOfferingId(Offering offering) {
        Booking booking = new Booking();
        try {
            String query = "select b.* from "
                    + table + " b join offering o on b.offering_id = o.id where b.offering_id = ? and b.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                booking = new Booking(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getLong(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public long insert(Client client, Offering offering) {
        long id = 0;
        try {
            String query = "insert into " + table
                    + " (active, client_id, offering_id, \"status\") values (?,?,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, client.getId());
            st.setLong(3, offering.getId());
            st.setString(4, "booked");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean delete(Client client, Booking booking) {
        boolean success = false;
        try {
            String query = "delete from " + table + " where id = ? and client_id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, booking.getId());
            st.setLong(2, client.getId());
            st.executeQuery();
            st.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
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
