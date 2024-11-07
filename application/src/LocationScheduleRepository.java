package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationScheduleRepository {

    private Connection conn;
    private String table = "locationSchedule";
    
    public LocationScheduleRepository(Database db){
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
                    rs.getLong(4),
                    rs.getLong(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locationSchedules;
    }


    public boolean insert(LocationSchedule ls) {
        boolean success = false;
        try {
            String query = "insert into "+ table +" (active, location_id, schedule_id, lesson_id) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, ls.getLocationId());
            st.setLong(3, ls.getScheduleId());
            st.setLong(4, ls.getLessonId());
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