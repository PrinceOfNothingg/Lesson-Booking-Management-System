package application.src;

import java.util.Scanner;

public class App extends Thread {

    Database psql = new Database();
    UserRepository userRepo = new UserRepository(psql);
    ClientRepository clientRepo = new ClientRepository(psql);
    GuardianRepository guardianRepo = new GuardianRepository(psql);
    AdministratorRepository administratorRepo = new AdministratorRepository(psql);
    RepresentativeRepository representativeRepo = new RepresentativeRepository(psql);
    InstructorRepository instructorRepo = new InstructorRepository(psql);
    BookingRepository bookingRepo = new BookingRepository(psql);
    InstructorOfferingRepository instructorOfferingRepo = new InstructorOfferingRepository(psql);
    LocationRepository locationRepo = new LocationRepository(psql);
    ScheduleRepository scheduleRepo = new ScheduleRepository(psql);
    LocationScheduleRepository locationScheduleRepo = new LocationScheduleRepository(psql);
    OfferingRepository offeringRepo = new OfferingRepository(psql);

    App() {
    }

    public void run() {

        Utils.printBanner();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // enum StartMenu {LOGIN, REGISTER, VIEW_OFFERINGS, QUIT}

        while (running) {

            User user = new User();
            Client client = null;
            Guardian guardian = null;
            Instructor instructor = null;
            Administrator administrator = null;

            int choice = Utils.handleStartSelection(scanner);
            boolean restart = false;
            switch (choice) {
                case 0: // Login

                    User.Type selectedLogin = User.Type.values()[Utils.handleLoginSelection(scanner)];

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
                            restart = true;
                            break;
                    }
                    break;
                case 1: // Register
                    User.Type selectedRegistration = User.Type.values()[Utils.handleRegistrationSelection(scanner)];

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
                            restart = true;
                            break;
                    }
                    // fall through
                case 2: // View offerings
                    if (user == null) {
                        System.out.println("Invalid User. Restarting.");
                        restart = true;
                    }
                    break;
                case 3: // quit
                default:
                    System.out.println("\nQuitting.");
                    running = false;
                    break;
            }

            if (restart || !running) {
                continue;
            }

            switch (user.getRole()) {
                case "client":
                    client = (Client) user;
                    client.process(scanner, offeringRepo, bookingRepo, locationRepo, scheduleRepo);
                    break;
                case "guardian":
                    guardian = (Guardian) user;
                    guardian.process(scanner, offeringRepo);
                    break;
                case "instructor":
                    instructor = (Instructor) user;
                    instructor.process(scanner, offeringRepo, locationRepo, scheduleRepo, instructorOfferingRepo);
                    break;
                case "admin":
                    administrator = (Administrator) user;
                    administrator.process(scanner, bookingRepo, offeringRepo, scheduleRepo, locationRepo, clientRepo,
                            guardianRepo, instructorRepo, administratorRepo, locationScheduleRepo, representativeRepo);
                    break;
                case "guest":
                    user.process(scanner, offeringRepo);
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }
}
