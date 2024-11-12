package application.src;

import java.util.Scanner;

import org.json.simple.JSONObject;

public class Client extends User {

    protected boolean dependant = false;

    public Client(){}
    public Client(long id, boolean active, String name, int age, String phone, String role, boolean dependant) {
        super( id, active, name, age, phone, role);
        this.dependant = dependant;
    }

    public boolean isDependant() {
        return dependant;
    }

    public void setDependant(boolean dependant) {
        this.dependant = dependant;
    }
    

    private void viewBookings(BookingRepository bookings){
        bookings.getByClientId(this).forEach(System.out::println);
    }

    private void viewBooking(Scanner scanner, BookingRepository bookings){
        boolean done = false;
        while(!done){
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          View Booking Details" + this.name);
            System.out.println("--------------------------------------------------------------------------------");
            
            int bookingId = Utils.getInt(scanner, "Please enter the id of a booking (q to quit):");
            if (bookingId == 0)
                break;
            Booking booking = bookings.get(bookingId);
            System.out.println(booking);
        }
    }
    
    private void makeBooking(Scanner scanner, OfferingRepository offerings, BookingRepository bookings){
        throw new UnsupportedOperationException("Unimplemented method 'makeBooking'");
        
        // Booking booking;
        // boolean done = false;
        // while(!done){
        //     System.out.println("\n--------------------------------------------------------------------------------");
        //     System.out.println("                          Make a Booking" + this.name);
        //     System.out.println("--------------------------------------------------------------------------------");
            
        //     int offeringId = Utils.getInt(scanner, "Please enter the id of an Offering (q to quit):");
        //     if (offeringId == 0)
        //         break;
        //     Offering offering = offerings.get(offeringId);

        //     //TODO
        //     // check offering is valid
        //     // check offering schedule not conflicting
        //     // get clients bookings > get schedules > compare offering schedule not overlaping

        //     bookings.insert(this, offering);
        //     booking = bookings.getByOfferingId(offering);

        //     offering.setSeats(offering.getSeats() - 1);
        //     if(offering.getSeats() == 0){
        //         offering.setStatus("non-available");
        //     }

        //     offerings.update(offering);

        //     System.out.println(booking);
        // }
    }

    private void cancelBooking(Scanner scanner, OfferingRepository offerings, BookingRepository bookings){
        boolean done = false;
        while(!done){
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Cancel a Booking" + this.name);
            System.out.println("--------------------------------------------------------------------------------");
            
            int bookingId = Utils.getInt(scanner, "Please enter the id of a Booking (q to quit):");
            if (bookingId == 0)
                break;
        
            Booking booking = bookings.get(bookingId);

            if(booking.getClientId() == id){
                bookings.delete(this, booking);

                Offering offering = offerings.get(booking.getOfferingId());
                offering.setSeats(offering.getSeats()+1);
                offering.setStatus("available");

                offerings.update(offering);

                System.out.println("Deleted booking " + booking);
            }
            else {
                System.out.println("Invalid booking selected.");
            }
        }
    }


    public static Client login(Scanner scanner, ClientRepository clients){
        Client client = null;
        String username;
        String phone;

        while(true){
            username = Utils.getUserName(scanner);
            if(username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if(phone.isEmpty())
                break;

            client = clients.get(username, phone);

            if(client.isEmpty()){
                System.out.println("Invalid credentials.");
            }
            else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return client;
    }
    public Client logout(){
        Client client = new Client();
        this.id = client.id;
        this.active = client.active;
        this.name = client.name;
        this.age = client.age;
        this.phone = client.phone;
        this.role = client.role;
        this.dependant = client.dependant;
        return client;
    }
    
    public static Client register(Scanner scanner, ClientRepository clients){
        Client client = new Client();
        String username;
        String phone;
        while(true){
            username = Utils.getUserName(scanner);
            if(username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if(phone.isEmpty())
                break;

            int age = Utils.getAge(scanner);
            if(age < 18)
                break;

            client = clients.get(username, phone);

            if(client.isEmpty()){
                client = new Client(0, true, username, age, phone, "client", false);
                clients.insert(client);
                client = clients.get(username, phone);
                break;
            }
            else {
                System.out.println("User already exists.");
            }
        }

        return client;
    }

    @Override
    protected int printMenu(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Offerings");
        System.out.println("2. View Bookings");
        System.out.println("3. View Booking Details");
        System.out.println("4. Make a Booking");
        System.out.println("5. Cancel a Booking");
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

    public void process(Scanner scanner, OfferingRepository offerings, BookingRepository bookings){
        boolean done = false;
        while(!done){
            int action = handleSelection(scanner);

            switch (action) {
                case 0: // View offerings
                    viewOfferings(offerings);
                    break;
                case 1: // View bookings
                    viewBookings(bookings);
                    break;
                case 2: // View a booking
                    viewBooking(scanner, bookings);
                    break;
                case 3: // Make a Booking
                    makeBooking(scanner, offerings, bookings);
                    break;
                case 4: // Cancel Booking
                    cancelBooking(scanner, offerings, bookings);
                    break;
                case 5: // logout
                    done = true;
                    logout();
                    break;
                
                default:
                    break;
            }       
        }
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active",active);
        json.put("name", name);
        json.put("phone", phone);
        json.put("age", age);
        json.put("dependant", dependant);

        String string = json.toString();

        return string;
    }
}
