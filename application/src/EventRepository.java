package application.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private Connection conn;
    private String table = "event";

    public EventRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<Event> get() {
        ArrayList<Event> event = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                event.add(
                        new Event(
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

        return event;
    }

    public Event get(long id) {
        Event event = new Event();
        try {
            String query = "select * from " + table + " where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                event = new Event(
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

        return event;
    }

    public List<Event> getByOfferingId(Offering offering) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            String query = "select e.* from " + table + " e on join location_schedule ls on e.location_schedule_id = ls.id where e.offering_id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                events.add(
                        new Event(
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

        return events;
    }

    public long insert(Event event) {
        long id = 0;
        try {
            String query = "insert into " + table
                    + " (active, offering_id, location_schedule_id) values (?,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, event.getOfferingId());
            st.setLong(3, event.getLocationScheduleId());
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
}
