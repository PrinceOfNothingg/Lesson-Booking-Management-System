package application.src;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructorRepository {

    private Connection conn;
    private String table = "instructor";

    public InstructorRepository(Database db) {
        this.conn = db.getConnection();
    }

    public List<Instructor> get() {
        ArrayList<Instructor> instructors = new ArrayList<>();
        String[] specs = null;
        String[] avails = null;
        try {
            String query = "select * from " + table;
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[]) rs.getArray(7).getArray();
                avails = (String[]) rs.getArray(8).getArray();
                instructors.add(
                        new Instructor(
                                rs.getLong(1),
                                rs.getBoolean(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                new ArrayList<String>(Arrays.asList(specs)),
                                new ArrayList<String>(Arrays.asList(avails))));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructors;
    }

    public Instructor get(long id) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;

        try {
            String query = "select * from " + table + " where id = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[]) rs.getArray(7).getArray();
                avails = (String[]) rs.getArray(8).getArray();
                instructor = new Instructor(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        new ArrayList<String>(Arrays.asList(specs)),
                        new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    public Instructor getByOfferingId(Offering offering) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;

        try {
            String query = "select i.* from " + table + " i join instructor_offering io on i.id = io.instructor_id join offering o on o.id = io.offering_id where o.id = ? and i.active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, offering.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[]) rs.getArray(7).getArray();
                avails = (String[]) rs.getArray(8).getArray();
                instructor = new Instructor(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        new ArrayList<String>(Arrays.asList(specs)),
                        new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    public Instructor get(String name, String phone) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;

        try {
            String query = "select * from " + table + " where name = ? and phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, phone);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[]) rs.getArray(7).getArray();
                avails = (String[]) rs.getArray(8).getArray();
                instructor = new Instructor(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        new ArrayList<String>(Arrays.asList(specs)),
                        new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    public Instructor get(String phone) {
        Instructor instructor = new Instructor();
        String[] specs = null;
        String[] avails = null;

        try {
            String query = "select * from " + table + " where phone = ? and active = true";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                specs = (String[]) rs.getArray(7).getArray();
                avails = (String[]) rs.getArray(8).getArray();
                instructor = new Instructor(
                        rs.getLong(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        new ArrayList<String>(Arrays.asList(specs)),
                        new ArrayList<String>(Arrays.asList(avails)));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    public long insert(Instructor instructor) {
        Array specs = null;
        Array avails = null;

        long id = 0;
        try {
            String query = "insert into " + table
                    + " (active, \"name\", age, phone, \"role\", specializations, availabilities) values (?,?,?,?,?::roleType,?,?) returning id";
            PreparedStatement st = conn.prepareStatement(query);
            ArrayList<String> s = instructor.getSpecializations();
            String[] dataSpecs = s.toArray(new String[s.size()]);
            ArrayList<String> a = instructor.getAvailabilities();
            String[] dataAvails = a.toArray(new String[s.size()]);

            specs = conn.createArrayOf("text", dataSpecs);
            avails = conn.createArrayOf("text", dataAvails);

            st.setBoolean(1, true);
            st.setString(2, instructor.name);
            st.setInt(3, instructor.age);
            st.setString(4, instructor.phone);
            st.setString(5, instructor.role);
            st.setArray(6, specs);
            st.setArray(7, avails);
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

    public long update(Instructor instructor) {
        Array specs = null;
        Array avails = null;

        long id = 0;
        try {
            String query = "update " + table
                    + " set active = ?, \"name\" = ?, age = ?, phone = ?, \"role\" = ?::roleType, specializations = ?, availabilities = ? where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            ArrayList<String> s = instructor.getSpecializations();
            String[] dataSpecs = s.toArray(new String[s.size()]);
            ArrayList<String> a = instructor.getAvailabilities();
            String[] dataAvails = a.toArray(new String[s.size()]);

            specs = conn.createArrayOf("text", dataSpecs);
            avails = conn.createArrayOf("text", dataAvails);

            st.setBoolean(1, instructor.active);
            st.setString(2, instructor.name);
            st.setInt(3, instructor.age);
            st.setString(4, instructor.phone);
            st.setString(5, instructor.role);
            st.setArray(6, specs);
            st.setArray(7, avails);
            st.setLong(8, instructor.id);
            st.executeQuery();
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
