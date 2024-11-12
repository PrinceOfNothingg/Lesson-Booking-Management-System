package application.src;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {

    private Connection conn;
    private String table = "location";
    
    public LocationRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Location> get() {
        ArrayList<Location> locations = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locations.add(
                    new Location(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locations;
    }

    public Location get(long id) {
        Location location = new Location();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                location = new Location(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return location;
    }

    public Location get(String name, String address, String city) {
        Location location = new Location();
        try {
            String query = "select * from " + table + " where name = ? and address = ? and city = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, address);
            st.setString(3, city);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                location = new Location(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return location;
    }
    public List<Location> getByName(String name) {
        ArrayList<Location> locations = new ArrayList<>();
        try {
            String query = "select * from " + table + " where name = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locations.add(new Location(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locations;
    }

    public List<Location> getByCity(String city) {
        ArrayList<Location> locations = new ArrayList<>();
        try {
            String query = "select * from " + table + " where city = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, city);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locations.add(new Location(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locations;
    }

    public Location getByOfferingId(Offering offering) {
        Location location = new Location();
        try {
            String query = "select l.* from " + table + " l join location_schedule ls on l.id = ls.location_id join offering o on ls.offering_id = o.id where o.id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                location = new Location(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return location;
    }

    public long insert(Location location) {
        long id = 0;
        try {
            String query = "insert into "+ table +" (active, name, address, city) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setString(2, location.getName());
            st.setString(3, location.getAddress());
            st.setString(4, location.getCity());
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
