package application.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationScheduleRepository {

    private Connection conn;
    private String table = "location_schedule";

    public LocationScheduleRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<LocationSchedule> get() {
        ArrayList<LocationSchedule> locationSchedules = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locationSchedules.add(
                        new LocationSchedule(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getLong(3),
                                rs.getLong(4)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationSchedules;
    }

    public LocationSchedule get(long id) {
        LocationSchedule locationSchedule = new LocationSchedule();
        try {
            String query = "select * from " + table + " where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locationSchedule = new LocationSchedule(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getLong(3),
                                rs.getLong(4));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationSchedule;
    }

    public LocationSchedule getByLocationIdAndScheduleId(Location location, Schedule schedule) {
        LocationSchedule locationSchedule = new LocationSchedule();
        try {
            String query = "select * from " + table + " where location_id = ? and schedule_id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, location.getId());
            st.setLong(2, schedule.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locationSchedule = new LocationSchedule(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getLong(3),
                                rs.getLong(4));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationSchedule;
    }


    public List<LocationSchedule> getByOfferingId(Offering offering) {
        ArrayList<LocationSchedule> locationSchedules = new ArrayList<>();
        try {
            String query = "select ls.* from " + table + " ls on join event e on e.location_schedule_id = ls.id where e.offering_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                locationSchedules.add(
                        new LocationSchedule(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getLong(3),
                                rs.getLong(4)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationSchedules;
    }

    public long insert(LocationSchedule ls) {
        long id = 0;
        try {
            String query = "insert into " + table
                    + " (active, location_id, schedule_id) values (?,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, ls.getLocationId());
            st.setLong(3, ls.getScheduleId());
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

    public boolean delete(LocationSchedule ls) {
        boolean success = false;
        try {
            String query = "delete from " + table + " where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, ls.getId());
            st.executeQuery();
            st.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
