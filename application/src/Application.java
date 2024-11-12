package application.src;
import java.sql.SQLException;


public class Application {

    public static void main(String[] args) {
        App app = new App();
        app.setName("App");

        
        Database psql = new Database();
        try {
            System.out.println("Testing DB conection.");
            psql.test();
            psql.close();
            System.out.println("Testing done.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        app.start();
    }
}
