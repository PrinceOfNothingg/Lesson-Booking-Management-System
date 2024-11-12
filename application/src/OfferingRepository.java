package application.src;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
                    rs.getString(5),
                    rs.getBoolean(6),
                    rs.getInt(7)));
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
                    rs.getString(5),
                    rs.getBoolean(6),
                    rs.getInt(7));
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
                    rs.getString(5),
                    rs.getBoolean(6),
                    rs.getInt(7)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offerings;
    }

    public List<Offering> getByInstructorId(Instructor instructor) {
        ArrayList<Offering> offerings = new ArrayList<>();
        try {
            String query = "select o.* from " + table + " o join instructor_offering io on o.id = io.offering_id where io.instructor_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, instructor.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                offerings.add(new Offering(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getString(5),
                    rs.getBoolean(6),
                    rs.getInt(7)));
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
                    rs.getString(5),
                    rs.getBoolean(6),
                    rs.getInt(7));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offering;
    }

    public Offering update(Offering offering) {
        try {
            String query = "update " + table + " set active = ?, taken = ?, status = ?, type = ?, mode = ?, seats = ? where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, offering.isActive());
            st.setBoolean(2, offering.isTaken());
            st.setString(3, offering.getStatus());
            st.setString(4, offering.getType());
            st.setBoolean(5, offering.isGroup());
            st.setInt(6, offering.getSeats());
            st.setLong(7, offering.getId());
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return offering;
    }

    public boolean insert(Offering offering) {
        boolean success = false;
        try {
            String query = "insert into " + table + " (active, status, taken, type, mode, seats) values (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, offering.isActive());
            st.setString(2, offering.getStatus());
            st.setBoolean(3, offering.isTaken());
            st.setString(4, offering.getType());
            st.setBoolean(5, offering.isGroup());
            st.setInt(6, offering.getSeats());

            st.executeQuery();
            System.out.println("DEBUG: " + st);
            st.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
