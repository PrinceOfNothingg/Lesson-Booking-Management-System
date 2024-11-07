package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.src.Schedule.WeekDay;

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
                slots = new ArrayList<>((ArrayList<Integer>)rs.getArray(7).getArray());

                schedules.add(
                    new Schedule(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getDate(3),
                    rs.getDate(4),
                    rs.getTime(5),
                    rs.getTime(6),
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
                slots = new ArrayList<>((ArrayList<Integer>)rs.getArray(7).getArray());
                schedule = new Schedule(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getTime(5),
                        rs.getTime(6),
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
}