package application.src;
import java.sql.SQLException;


public class Application {

    public static void main(String[] args) {
        App app = new App();
        Server server = new Server();

        server.setName("Server");
        app.setName("App");

        server.start();
        app.start();

        
        Database psql = new Database();
        try {
            psql.test();
            psql.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserRepository userRepo = new UserRepository();
        userRepo.get().forEach(System.out::println);
        System.out.println(userRepo.get(1));

    }
}
