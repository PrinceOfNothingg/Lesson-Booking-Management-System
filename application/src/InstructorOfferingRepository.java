package application.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorOfferingRepository {

    private Connection conn;
    private String table = "instructor_offering";
    
    public InstructorOfferingRepository(Database db){
        this.conn = db.getConnection();
    }

    public List<InstructorOffering> get() {
        ArrayList<InstructorOffering> instructorOfferings = new ArrayList<>();
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                instructorOfferings.add(
                    new InstructorOffering(
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
        
        return instructorOfferings;
    }


    public boolean insert(Instructor instructor, Offering offering) {
        boolean success = false;
        try {
            String query = "insert into "+ table +" (active, instructor_id, offering_id) values (?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setBoolean(1, true);
            st.setLong(2, instructor.getId());
            st.setLong(3, offering.getId());
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
