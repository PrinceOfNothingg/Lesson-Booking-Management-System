package application.src;


import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
        ArrayList<Integer> slots = null;
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //slots = new ArrayList<>((ArrayList<Integer>)rs.getArray(7).getArray());
                slots = null;

                schedules.add(
                    new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    slots,
                    rs.getString(8)));
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
        ArrayList<Integer> slots = null;
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                slots = null;//new ArrayList<>((ArrayList<Integer>)rs.getArray(7).getArray());
                schedule = new Schedule(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        slots,
                        rs.getString(8));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedule;
    }

    public Schedule get(String startDate, String endDate, String startTime, String endTime, String dow) {
        Schedule schedule = new Schedule();
        ArrayList<Integer> slots = null;
        try {
            String query = "select * from " + table + " where start_date = ? and end_date = ? and start_t = ? and end_t = ? and weekdays = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Date.valueOf(schedule.getStartDate()));
            st.setDate(2, Date.valueOf(schedule.getEndDate()));
            st.setTime(3, Time.valueOf(schedule.getStartTime()));
            st.setTime(4, Time.valueOf(schedule.getEndTime()));
            st.setString(5, schedule.getDow());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                slots = null;//new ArrayList<>((ArrayList<Integer>)rs.getArray(7).getArray());
                schedule = new Schedule(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        slots,
                        rs.getString(8));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedule;
    }

    public long insert(Schedule schedule) {
        long id = 0;
        try {
            String query = "insert into "+ table +" (active, start_date, end_date, start_t, end_t, time_slots, weekdays) values (?,?,?,?,?,?,?::_day_of_week) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setDate(2, Date.valueOf(schedule.getStartDate()));
            st.setDate(3, Date.valueOf(schedule.getEndDate()));
            st.setTime(4, Time.valueOf(schedule.getStartTime()));
            st.setTime(5, Time.valueOf(schedule.getEndTime()));
            st.setArray(6, null);
            st.setString(7, schedule.getDow());
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
}
