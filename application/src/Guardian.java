package application.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class Guardian extends User {

    private ArrayList<Client> dependants = new ArrayList<>();

    public Guardian() {
    }

    public Guardian(long id, boolean active, String name, int age, String phone, String role) {
        super(id, active, name, age, phone, role);
    }

    public ArrayList<Client> getDependants() {
        return dependants;
    }

    public void setDependants(ArrayList<Client> dependants) {
        this.dependants = dependants;
    }

    public void makeBooking(Scanner scanner, ClientRepository clients, OfferingRepository offerings,
            BookingRepository bookings, LocationRepository locations, ScheduleRepository schedules) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Make a Booking for Dependant");
            System.out.println("--------------------------------------------------------------------------------");

            // select a dependnt client by index
            System.out.println(dependants);
            long dependantId= Utils.getLong(scanner, "Enter a dependant by index (q to quit):");
            if (dependantId == 0) {
                System.out.println("Exiting.");
                return;
            }

            Client dependant = clients.get(dependantId);

            if(!dependants.contains(dependant)){
                System.out.println("Invalid id.");
                continue;
            }

            int offeringId = Utils.getInt(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;

            Offering offering = offerings.get(offeringId);
            if (offering == null) {
                System.out.println("Offering not found.");
                continue;
            }
            if (offering.getStatus().equals("non-available")) {
                System.out.println("Offering is not available.");
                continue;
            }

            List<Schedule> scheduleList = schedules.getByClientId(dependant);
            List<Schedule> newScheduleList = schedules.getByOfferingId(offering);
            for (Schedule schedule : newScheduleList) {
                if(scheduleList.contains(schedule)){
                    System.out.println("You already have a booking at the same time.");
                    return;
                }
            }

            // Check offering availability
            if (offering.getSeats() > 0) {
                long id = bookings.insert(dependant, offering);
                Booking booking = bookings.get(id);
                offering.setSeats(offering.getSeats() - 1);
                if (offering.getSeats() == 0) {
                    offering.setStatus("non-available");
                }
                offerings.update(offering);
                System.out.println("Booking successfully made for " + dependant.getName());
                System.out.println(booking);
            } else {
                System.out.println("No seats available for this offering.");
            }
        }
    }

    public void viewBookings(BookingRepository bookings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Bookings for Dependants");
        System.out.println("--------------------------------------------------------------------------------");

        for (Client dependant : dependants) {
            System.out.println("Bookings for " + dependant.getName() + ":");
            List<Booking> booklist = bookings.getByClientId(dependant);
            if (booklist.isEmpty()) {
                System.out.println("You have no bookings.");
            }
            else{
                booklist.forEach(System.out::println);
            }
        }
    }

    public void viewBookingDetails(Scanner scanner, ClientRepository clients, InstructorRepository instructors, BookingRepository bookings, OfferingRepository offerings,
            LocationRepository locations, ScheduleRepository schedules) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Booking Details for Dependants");
        System.out.println("--------------------------------------------------------------------------------");
            
        for (Client dependant : dependants) {
            System.out.println("Select a booking for " + dependant.getName() + ":");
            
            List<Booking> booklist = bookings.getByClientId(dependant);
            if (booklist.isEmpty()) {
                System.out.println("You have no bookings.");
                return;
            }

            booklist.forEach(System.out::println);

            int bookingId = Utils.getInt(scanner, "Enter booking ID to view details (q to quit):");
            if (bookingId == 0)
                break;

            Booking booking = bookings.get(bookingId);
            Offering offering = offerings.getByBookingId(booking);
            Client client = clients.get(booking.getClientId());
            Instructor instructor = instructors.getByOfferingId(offering);
            List<Schedule> scheduleList = schedules.getByOfferingId(offering);
            Location location = locations.getByOfferingId(offering);

            System.out.println(booking);
            System.out.println(offering);
            System.out.println(client);
            System.out.println(instructor);
            System.out.println(location);
            scheduleList.forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public void cancelBooking(Scanner scanner, ClientRepository clients, BookingRepository bookings,
            OfferingRepository offerings) {
        
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Cancel Booking for Dependants");
        System.out.println("--------------------------------------------------------------------------------");

        long dependantId = Utils.getLong(scanner, "Enter a dependant by index (q to quit):");
        if (dependantId == 0) {
            System.out.println("Exiting.");
            return;
        }

        Client dependant = clients.get(dependantId);

        List<Booking> booklist = bookings.getByClientId(dependant);
        if (booklist.isEmpty()) {
            System.out.println("Dependant has no bookings.");
            return;
        }

        System.out.println("Select a booking to cancel for " + dependant.getName() + ":");
        booklist.forEach(System.out::println);

        // enter booking ID to cancel
        int bookingId = Utils.getInt(scanner, "Enter booking ID (q to quit):");
        if (bookingId == 0)
            return;

        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getClientId() == dependant.getId()) {
            bookings.delete(dependant, booking);

            Offering offering = offerings.get(booking.getOfferingId());
            offering.setSeats(offering.getSeats() + 1);
            offering.setStatus("available");

            offerings.update(offering);
            System.out.println("Booking canceled for " + dependant.getName());
        } else {
            System.out.println("Booking not found.");
        }
}

    public static Guardian login(Scanner scanner, GuardianRepository guardians) {
        Guardian guardian = new Guardian();
        String username;
        String phone;

        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty())
                break;

            guardian = guardians.get(username, phone);

            if (guardian.isEmpty()) {
                System.out.println("Invalid credentials.");
                guardian = null;
            } else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return guardian;
    }

    public Guardian logout() {
        Guardian guardian = new Guardian();
        this.id = guardian.id;
        this.active = guardian.active;
        this.name = guardian.name;
        this.age = guardian.age;
        this.phone = guardian.phone;
        this.role = guardian.role;
        return guardian;
    }

    public static Guardian register(Scanner scanner, GuardianRepository guardians, ClientRepository clients,
            RepresentativeRepository representatives) {
        Guardian guardian = new Guardian();
        String username;
        String phone;

        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty())
                break;

            int age = Utils.getAge(scanner);
            guardian = guardians.get(phone);

            // register dependants if guardian doesn't exist
            if (guardian.isEmpty()) {
                ArrayList<Client> children = registerDependants(scanner, clients);

                guardian = new Guardian(0, true, username, age, phone, "guardian");
                long id = guardians.insert(guardian);
                guardian = guardians.get(id);

                // link each dependant to guardian
                for (Client client : children) {
                    representatives.insert(guardian, client);
                    System.out.println("Added dependant " + client);
                }
                guardian.setDependants(children);
                break;
            } else {
                System.out.println("User conflict");
                guardian = new Guardian();
            }
        }
        if(!guardian.isEmpty())
            System.out.println("Registered " + guardian);

        return guardian;
    }

    public static ArrayList<Client> registerDependants(Scanner scanner, ClientRepository clients) {
        int count = Utils.getInt(scanner, "Enter the number of dependants to register:");
        ArrayList<Client> children = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            System.out.println("\nEnter dependant " + i + " information.");
            Client dependant = registerDependant(scanner, clients);
            if(dependant != null && !dependant.isEmpty())
                children.add(dependant);
        }

        return children;
    }

    private static Client registerDependant(Scanner scanner, ClientRepository clients){
        Client client = new Client();
        String username;
        String phone;
        username = Utils.getUserName(scanner);
        if (username.isEmpty())
            return null;

        phone = Utils.getPhone(scanner);
        if (phone.isEmpty())
            return null;

        int age = Utils.getInt(scanner, "Enter the childs age:");
        if (age == 0)
            return null;

        client = clients.get(phone);

        if (client.isEmpty()) {
            client = new Client(0, true, username, age, phone, "client", true);
            long id = clients.insert(client);
            client = clients.get(id);
        } else {
            System.out.println("User already exists.");
            client = new Client();
        }

        return client;
    }

    @Override
    protected int printMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Offerings");
        System.out.println("2. View Bookings");
        System.out.println("3. View Booking Details");
        System.out.println("4. Make a Booking for a Dependant");
        System.out.println("5. Remove a Booking for a Dependant");
        System.out.println("6. Logout");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 6;
    }

    @Override
    protected int handleSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printMenu();
            choice = Utils.getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public void process(Scanner scanner, ClientRepository clients, InstructorRepository instructors, OfferingRepository offerings,
            BookingRepository bookings, LocationRepository locations, ScheduleRepository schedules) {
        boolean done = false;
        while (!done) {
            int action = handleSelection(scanner);

            switch (action) {
                case 0: // View offerings
                    viewOfferings(instructors, offerings, locations, schedules);
                    break;
                case 1: // View bookings
                    viewBookings(bookings);
                    break;
                case 2: // View a booking
                    viewBookingDetails(scanner, clients, instructors, bookings, offerings, locations, schedules);
                    break;
                case 3: // Make a Booking
                    makeBooking(scanner, clients, offerings, bookings, locations, schedules);
                    break;
                case 4: // Cancel Booking
                    cancelBooking(scanner, clients, bookings, offerings);
                    break;
                case 5: // logout
                    done = true;
                    logout();
                    System.out.println("Logged out.");
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Guardian)b).id;
    }
    

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("name", name);
        json.put("phone", phone);
        json.put("age", age);
        json.put("dependants", dependants);
        String string = "Guardian: {id: "+id+", active: "+active+", name: "+name+", phone: "+phone+", age: "+age+", dependant: "+dependants+"}";
        
        return string;
    }
}
