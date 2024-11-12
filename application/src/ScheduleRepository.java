package application.src;

import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository {

    private Connection conn;
    private String table = "schedule";
    
    public ScheduleRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Schedule> get() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PGobject tsrange = new PGobject();
                tsrange.setType("tsrange");
                tsrange = (PGobject)rs.getObject("tsrangeType");
                schedules.add(
                    new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    tsrange.toString()));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedules;
    }

    public Schedule get(long id) {
        Schedule schedule = new Schedule();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PGobject tsrange = new PGobject();
                tsrange.setType("tsrange");
                tsrange = (PGobject)rs.getObject("tsrangeType");

                schedule = new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    tsrange.toString());
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedule;
    }

    public List<Schedule> getByLocationId(Location location) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String query = "select s.* from " + table + "s join location_schedule ls on s.id = ls.schedule_id where ls.location_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, location.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PGobject tsrange = new PGobject();
                tsrange.setType("tsrange");
                tsrange = (PGobject)rs.getObject("tsrangeType");
                schedules.add(
                    new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    tsrange.toString()));    
                }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedules;
    }

    public List<Schedule> getByOfferingId(Offering offering) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String query = "select s.* from " + table + "s join location_schedule ls on s.id = ls.schedule_id where ls.offering_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PGobject tsrange = new PGobject();
                tsrange.setType("tsrange");
                tsrange = (PGobject)rs.getObject("tsrangeType");
                schedules.add(
                    new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    tsrange.toString()));    
                }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedules;
    }

    public long insert(Schedule schedule) {
        long id = 0;
        try {
            String query = "insert into "+ table +" (active, slot) values (?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);

            PGobject tsrange = new PGobject();
            tsrange.setType("tsrange");
            tsrange.setValue(schedule.getSlot());

            st.setBoolean(1, true);
            st.setObject(2, tsrange);
            
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

    public boolean delete(Schedule schedule) {
        boolean success = false;
        try {
            String query = "DELETE FROM " + table + " WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, schedule.getId());
            int affectedRows = st.executeUpdate();
            success = affectedRows > 0;
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
