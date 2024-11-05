package application.src;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;

public class Database {
    
    private static String url = "jdbc:postgresql://localhost:5432/soendb";
    private static String user = "soen";
    private static String passwd = "";
    private Connection connection;

    Database() {
        this("jdbc:postgresql://localhost:5432/soendb", "soen", "");
    }

    Database(String url, String user, String passwd) {
        Database.url = url;
        Database.user = user;
        Database.passwd = passwd;

        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(passwd);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Connection getConnection() {        
        return connection;
    }

    public void test() throws SQLException {
        PreparedStatement st = connection.prepareStatement("select * from client");
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
