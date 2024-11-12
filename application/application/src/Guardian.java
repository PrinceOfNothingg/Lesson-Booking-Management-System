package application.src;

import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class Guardian extends User {

    private ArrayList<Client> dependants = new ArrayList<>();

    public Guardian() {}

    public Guardian(long id, boolean active, String name, int age, String phone, String role) {
        super(id, active, name, age, phone, role);
    }

    public ArrayList<Client> getDependants() {
        return dependants;
    }

    public void setDependants(ArrayList<Client> dependants) {
        this.dependants = dependants;
    }

    public void makeBooking(Scanner scanner, OfferingRepository offerings, BookingRepository bookings) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Make a Booking for Dependant");
            System.out.println("--------------------------------------------------------------------------------");
            
            // select a dependnt client by index
            int dependantIndex = Utils.getInt(scanner, "Select a dependant by index (q to quit):");
            if (dependantIndex < 0 || dependantIndex >= dependants.size()) {
                System.out.println("Invalid dependant selected.");
                continue;
            }

            Client dependant = dependants.get(dependantIndex);

             // enter ID of offering to book.
            int offeringId = Utils.getInt(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0) break;

            Offering offering = offerings.get(offeringId);
            if (offering == null) {
                System.out.println("Offering not found.");
                continue;
            }

            // Check offering availability
            if (offering.getSeats() > 0) {
                bookings.insert(dependant, offering);
                offering.setSeats(offering.getSeats() - 1);
                if (offering.getSeats() == 0) {
                    offering.setStatus("non-available");
                }
                System.out.println("Booking successfully made for " + dependant.getName());
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
            bookings.getByClientId(dependant.getId()).forEach(System.out::println);
        }
    }

    public void viewBookingDetails(Scanner scanner, BookingRepository bookings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Booking Details for Dependants");
        System.out.println("--------------------------------------------------------------------------------");

        for (Client dependant : dependants) {
            System.out.println("Select a booking for " + dependant.getName() + ":");
            bookings.getByClientId(dependant.getId()).forEach(System.out::println);

            int bookingId = Utils.getInt(scanner, "Enter booking ID to view details (q to quit):");
            if (bookingId == 0) break;

            Booking booking = bookings.get(bookingId);
            if (booking != null) {
                System.out.println(booking);
            } else {
                System.out.println("Booking not found.");
            }
        }
    }

    public void cancelBooking(Scanner scanner, BookingRepository bookings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Cancel Booking for Dependants");
        System.out.println("--------------------------------------------------------------------------------");

        for (Client dependant : dependants) {
            System.out.println("Select a booking to cancel for " + dependant.getName() + ":");
            bookings.getByClientId(dependant.getId()).forEach(System.out::println);

            // enter booking ID to cancel
            int bookingId = Utils.getInt(scanner, "Enter booking ID to cancel (q to quit):");
            if (bookingId == 0) break;

            Booking booking = bookings.get(bookingId);
            if (booking != null) {
                bookings.delete(booking);
                System.out.println("Booking canceled for " + dependant.getName());
            } else {
                System.out.println("Booking not found.");
            }
        }
    }

    public void viewOfferings(OfferingRepository offerings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Available Offerings");
        System.out.println("--------------------------------------------------------------------------------");

        offerings.getAll().forEach(System.out::println);
    }

    public static Guardian login(Scanner scanner, GuardianRepository guardians) {
        Guardian guardian = new Guardian();
        String username;
        String phone;

        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty()) break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty()) break;

            guardian = guardians.get(username, phone);

            if (guardian.isEmpty()) {
                System.out.println("Invalid credentials.");
            } else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return guardian;
    }

    public static Guardian register(Scanner scanner, GuardianRepository guardians, ClientRepository clients, RepresentativeRepository representatives) {
        Guardian guardian = new Guardian();
        String username;
        String phone;

        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty()) break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty()) break;

            int age = Utils.getAge(scanner);
            guardian = guardians.get(username, phone);

            // register dependants if guardian doesn't exist
            if (guardian.isEmpty()) {
                ArrayList<Client> children = registerDependants(scanner, clients);

                guardian = new Guardian(0, true, username, age, phone, "guardian");
                guardians.insert(guardian);
                guardian = guardians.get(username, phone);

                // link each dependant to guardian
                for (Client client : children) {
                    representatives.insert(guardian, client);
                }
                break;
            } else {
                System.out.println("User already exists.");
            }
        }

        return guardian;
    }

    public static ArrayList<Client> registerDependants(Scanner scanner, ClientRepository clients) {
        int count = Utils.getInt(scanner, "Enter the number of dependants to register:");
        ArrayList<Client> children = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            System.out.println("\nEnter dependant " + i + " information.");
            Client dependant = Client.register(scanner, clients);
            children.add(dependant);
        }

        return children;
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
        return json.toString();
    }
}
