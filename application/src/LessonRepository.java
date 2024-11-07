package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository {

    private Connection conn;
    private String table = "lesson";
    
    public LessonRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<Lesson> get() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lessons.add(
                    new Lesson(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lessons;
    }

    public Lesson get(long id) {
        Lesson lesson = new Lesson();
        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lesson = new Lesson(
                    rs.getLong(1),
                    rs.getBoolean(2),
                    rs.getString(3),
                    rs.getBoolean(4),
                    rs.getInt(5));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lesson;
    }
}
