package application.src;

import java.security.Guard;
import java.util.Scanner;

public class App extends Thread {
    
    Database psql = new Database();
    UserRepository userRepo = new UserRepository(psql);
    ClientRepository clientRepo = new ClientRepository(psql);
    GuardianRepository guardianRepo = new GuardianRepository(psql);
    RepresentativeRepository representativeRepo = new RepresentativeRepository(psql);
    InstructorRepository instructorRepo = new InstructorRepository(psql);
    AdministratorRepository administratorRepo = new AdministratorRepository(psql);

    App() {}

    private void printBanner(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------Welcome to Activities Board!--------------------------");
        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private int printStartMenu(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------           Start            --------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. View Offerings");
        System.out.println("4. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 4;
    }

    private int printLoginMenu(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------           Login            --------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Log in as Client");
        System.out.println("2. Log in as Guardian");
        System.out.println("3. Log in as Instructor");
        System.out.println("4. Log in as Admin");
        System.out.println("5. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 5;
    }

    private int printRegistrationMenu(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("--------------------------        Registration        --------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Register as Client");
        System.out.println("2. Register as Guardian");
        System.out.println("3. Register as Instructor");
        System.out.println("4. Register as Admin");
        System.out.println("5. Quit");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 5;
    }

    private int getSelection(Scanner scanner, int min, int max) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                if (choice < min || choice > max) {
                    System.out.println("Please select between " + min + " and " + max + ".");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
            }
        }
        return choice - 1; // because we use it in a switch statement
    }

    private int handleStartSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printStartMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    private int handleRegistrationSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printRegistrationMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    private int handleLoginSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printLoginMenu();
            choice = getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public void run() {

        printBanner();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        enum StartMenu {LOGIN, REGISTER, VIEW_OFFERINGS, QUIT}


        while(running) {

            int choice = handleStartSelection(scanner);


            User user = null;
            Client client = null;
            Guardian guardian = null;
            Instructor instructor = null;
            Administrator administrator = null;

            switch (choice) {
            case 0: //Login
                
                User.Type selectedLogin = User.Type.values()[handleLoginSelection(scanner)];

                switch (selectedLogin) {
                    case User.Type.CLIENT:
                        user = Client.login(scanner, clientRepo);
                        break;
                    case User.Type.GUARDIAN:
                        user = Guardian.login(scanner, guardianRepo);
                        break;
                    case User.Type.INTRUCTOR:
                        user = Instructor.login(scanner, instructorRepo);
                        break;
                    case User.Type.ADMIN:
                        user = Administrator.login(scanner, administratorRepo);
                        break;
                    default:
                        break;
                }
                break;
            case 1: //Register
                User.Type selectedRegistration = User.Type.values()[handleRegistrationSelection(scanner)];

                switch (selectedRegistration) {
                    case User.Type.CLIENT:
                        user = Client.register(scanner, clientRepo);
                        break;
                    case User.Type.GUARDIAN:
                        user = Guardian.register(scanner, guardianRepo, clientRepo, representativeRepo);
                        break;
                    case User.Type.INTRUCTOR:
                        user = Instructor.register(scanner, instructorRepo);
                        break;
                    case User.Type.ADMIN:
                        user = Administrator.register(scanner, administratorRepo);
                        break;
                    default:
                        break;
                }
            case 2: //View offerings
                user = new User();
                break;
            case 3: // quit
            default:
                System.out.println("\nQuitting.");
                running = false;
                break;
            }

            if(choice == 1 && choice == 2 && user == null){
                System.out.println("Invalid User. Restarting.");
                continue;
            }

            // switch (user.getRole()) {
            //     case "client":
            //         client = (Client) user;
            //         while(!done){
            //             client.displayClientMenu();
            //             client.execute();
            //             if(terminate)
            //                 running = false;
            //                 client.logout();
            //         }
            //         break;
            //     case "guardian":
            //         guardian = (Guardian) user;
            //         break;
            //     case "instructor":
            //         instructor = (Instructor) user;
            //         break;
            //     case "admin":
            //         administrator = (Administrator) user;
            //         break;
            //     case "guest":
            //         break;
            //     default:
            //         break;
            // }
        }

        scanner.close();
    }
}
