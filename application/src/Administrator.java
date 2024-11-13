package application.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(long id, boolean active, String name, int age, String phone, String role) {
        super(id, active, name, age, phone, role);
    }

    public void viewBookings(BookingRepository bookings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Bookings " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        bookings.get().forEach(System.out::println);
    }

    @Override
    public void viewOfferings(OfferingRepository offerings) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Offerings " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        offerings.get().forEach(System.out::println);
    }

    public void viewSchedules(ScheduleRepository schedules) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Schedules " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        schedules.get().forEach(System.out::println);
    }

    public void viewLocations(LocationRepository locations) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Locations " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        locations.get().forEach(System.out::println);
    }

    public void viewClients(ClientRepository clients) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Clients " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        clients.get().forEach(System.out::println);
    }

    public void viewGuardians(GuardianRepository guardians) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Guardians " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        guardians.get().forEach(System.out::println);
    }

    public void viewInstructors(InstructorRepository instructors) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Instructors " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        instructors.get().forEach(System.out::println);
    }

    public void viewAdmins(AdministratorRepository administrators) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          View Admins " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        administrators.get().forEach(System.out::println);
    }

    public void createBookings(Scanner scanner, ClientRepository clients, OfferingRepository offerings, BookingRepository bookings,
    LocationRepository locations, ScheduleRepository schedules) {
        Booking booking;
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                        Make a Booking" + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            long clientId = Utils.getLong(scanner, "Please enter the id of the Client (q to quit):");
            if (clientId == 0)
                break;

            long offeringId = Utils.getLong(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;

            Offering offering = offerings.get(offeringId);
            Client client = clients.get(clientId);
            List<Offering> offeringList = offerings.getByClientId(client);
            List<Location> locationList = new ArrayList<>();
            List<List<Schedule>> scheduleListList = new ArrayList<>();
            for (Offering o : offeringList) {
                locationList.add(locations.getByOfferingId(o));
                scheduleListList.add(schedules.getByOfferingId(o));
            }

            Location curLocation = locations.getByOfferingId(offering);
            List<Schedule> curScheduleList = schedules.getByOfferingId(offering);
            boolean bookingConflict = false;
            for (Location location : locationList) {
                if (location.getName().equalsIgnoreCase(curLocation.getName()) &&
                        location.getAddress().equalsIgnoreCase(curLocation.getAddress()) &&
                        location.getCity().equalsIgnoreCase(curLocation.getCity())) {
                    if (scheduleListList.contains(curScheduleList)) {
                        bookingConflict = true;
                        break;
                    }
                }
            }

            if (bookingConflict) {
                System.out.println("Another booking at that location and time already exists.");
                break;
            }

            long id = bookings.insert(client, offering);
            booking = bookings.get(id);

            offering.setSeats(offering.getSeats() - 1);
            if (offering.getSeats() == 0) {
                offering.setStatus("non-available");
            }

            offerings.update(offering);

            System.out.println(booking);
        }
    }

    public void createOfferings(Scanner scanner, OfferingRepository offerings, ScheduleRepository schedules,
            LocationRepository locations, LocationScheduleRepository locationSchedules) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create an Offering " + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        String val = Utils.getString(scanner, "Create a new Schedule? y/n (q to quit):");
        if (!val.isEmpty() || val.equalsIgnoreCase("y")) {
            createSchedules(scanner, schedules);
        }

        viewSchedules(schedules);
        System.out.println("Enter 1 or more schedule ids to associate with offering:");
        List<Schedule> scheduleList = new ArrayList<>();

        long id = 0;
        while (true) {
            id = Utils.getInt(scanner, "Enter a schedule id (q to quit):");
            if (id == 0)
                break;
            scheduleList.add(schedules.get(id));
        }

        // create location
        val = Utils.getString(scanner, "\nCreate a new location? y/n (q to quit):");
        if (!val.isEmpty() || val.equalsIgnoreCase("y")) {
            createLocations(scanner, locations);
        }

        viewLocations(locations);
        id = Utils.getLong(scanner, "\nEnter the location id:");
        if (id == 0)
            return;
        
        Location location = locations.get(id);

        Offering offering = new Offering();
        offering.setActive(true);
        offering.setTaken(false);
        offering.setStatus("non-available");

        String type = Utils.getString(scanner, "Enter offering lesson type (q to quit):");
        offering.setType(type);

        val = Utils.getString(scanner, "Make an Offering private/group/both? (q to quit):");

        if (val.equalsIgnoreCase("both") || val.equalsIgnoreCase("private")) {
            offering.setGroup(false);
            offering.setSeats(1);
            id = offerings.insert(offering);
            offering = offerings.get(id);

            for (Schedule schedule : scheduleList) {
                LocationSchedule ls = new LocationSchedule(0, true, location.getId(), schedule.getId(),
                        offering.getId());
                id = locationSchedules.insert(ls);
                if (id == 0) {
                    System.out.println("Failed to add schedule " + schedule + "to offering");
                    offerings.delete(offering);
                    break;
                } else
                    System.out.println("Offering " + offering + " has been created.");
            }
        }
        if (val.equalsIgnoreCase("both") || val.equalsIgnoreCase("group")) {
            int value = Utils.getInt(scanner, "How many participants? (q to quit):");
            if (value == 0) {
                System.out.println("Exiting without creating group offering");
            } else {
                offering.setGroup(true);
                offering.setSeats(value);
                id = offerings.insert(offering);
                offering = offerings.get(id);

                for (Schedule schedule : scheduleList) {
                    LocationSchedule ls = new LocationSchedule(0, true, location.getId(), schedule.getId(),
                            offering.getId());
                    id = locationSchedules.insert(ls);
                    if (id == 0) {
                        System.out.println("Failed to add schedule " + schedule + " to offering");
                        offerings.delete(offering);
                        break;
                    } else
                        System.out.println("Offering " + offering + " has been created.");
                }
            }
        }
    }

    public void createSchedules(Scanner scanner, ScheduleRepository schedules) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Create a schedule " + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            String start = Utils.getDate(scanner, "Please enter the start time 'YYYY-MM-DD hh:mm:ss':");
            if (start == null || start.isEmpty())
                break;
            String end = Utils.getDate(scanner, "Please enter the end time 'YYYY-MM-DD hh:mm:ss':");
            if (end == null || end.isEmpty())
                break;

            Schedule schedule = new Schedule(0, true, "[" + start + ", " + end + ")");

            long id = schedules.insert(schedule);
            if (id == 0) {
                System.out.println("Schedule not created due to error or overlap");
                continue;
            }
            schedule = schedules.get(id);

            System.out.println("Schedule " + schedule + " has been created.");

            String input = Utils.getString(scanner, "Enter q to quit:");
            if (input == null || input.isEmpty())
                break;
        }
    }

    public void createLocations(Scanner scanner, LocationRepository locations) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Create a location " + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            String name = Utils.getString(scanner, "Please enter location name:");
            if (name == null || name.isEmpty())
                break;
            String address = Utils.getString(scanner, "Please enter location address:");
            if (address == null || address.isEmpty())
                break;
            String city = Utils.getString(scanner, "Please enter location city:");
            if (city == null || city.isEmpty())
                break;

            Location location = new Location(0, true, name, address, city);

            Location temp = locations.get(name, address, city);
            if (temp.getName().equalsIgnoreCase(name) &&
                    temp.getAddress().equalsIgnoreCase(address) &&
                    temp.getCity().equalsIgnoreCase(city)) {
                System.out.println("Location already exists.");
                break;
            }

            locations.insert(location);
            location = locations.get(name, address, city);

            System.out.println("Location " + location + " has been created.");

            String input = Utils.getString(scanner, "Enter q to quit:");
            if (input == null || input.isEmpty())
                break;
        }
    }

    public void createClient(Scanner scanner, ClientRepository clients) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create a Client " + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        Client.register(scanner, clients);
    }

    public void createGuardian(Scanner scanner, GuardianRepository guardians, ClientRepository clients,
            RepresentativeRepository representatives) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create a Guardian " + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        Guardian.register(scanner, guardians, clients, representatives);
    }

    public void createInstructor(Scanner scanner, InstructorRepository instructors) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create an Instructor " + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        Instructor.register(scanner, instructors);
    }

    public void createAdministrator(Scanner scanner, AdministratorRepository administrators) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create an Instructor " + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        Administrator.register(scanner, administrators);
    }

    public void deleteBookings(Scanner scanner, OfferingRepository offerings, BookingRepository bookings) {
        long id = Utils.getLong(scanner, "Enter the ID of the booking to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Booking booking = bookings.get(id);
        if (booking != null) {
            bookings.delete(id);
            System.out.println("Booking " + id + " has been deleted.");

            Offering offering = offerings.get(booking.getOfferingId());
            offering.setSeats(offering.getSeats() + 1);
            offering.setStatus("available");

            offerings.update(offering);
        } else {
            System.out.println("Booking not found.");
        }
    }

    public void deleteOfferings(Scanner scanner, OfferingRepository offerings) {
        long id = Utils.getLong(scanner, "Enter the ID of the offering to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Offering offering = offerings.get(id);
        if (offering != null) {
            offerings.delete(offering);
            // associated location schedules are delete by DB cascade
            System.out.println("Offering " + id + " has been deleted.");
        } else {
            System.out.println("Offering not found.");
        }
    }

    public void deleteSchedules(Scanner scanner, ScheduleRepository schedules) {
        long id = Utils.getLong(scanner, "Enter the ID of the schedule to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Schedule schedule = schedules.get(id);
        if (schedule != null) {
            schedules.delete(id);
            // associated location schedules are delete by DB cascade
            System.out.println("Schedule " + id + " has been deleted.");
        } else {
            System.out.println("Schedule not found.");
        }
    }

    public void deleteLocations(Scanner scanner, LocationRepository locations) {
        long id = Utils.getLong(scanner, "Enter the ID of the location to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Location location = locations.get(id);
        if (location != null) {
            locations.delete(id);
            // associated location schedules are delete by DB cascade
            System.out.println("Location " + id + " has been deleted.");
        } else {
            System.out.println("Location not found.");
        }
    }

    public void deleteClients(Scanner scanner, ClientRepository clients) {
        long id = Utils.getLong(scanner, "Enter the ID of the client to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Client client = clients.get(id);
        if (client != null) {
            clients.delete(id);
            // associated bookings are delete by DB cascade
            System.out.println("Client " + id + " has been deleted.");
        } else {
            System.out.println("Client not found.");
        }
    }

    public void deleteGuardians(Scanner scanner, ClientRepository clients, GuardianRepository guardians) {
        long id = Utils.getLong(scanner, "Enter the ID of the guardian to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Guardian guardian = guardians.get(id);
        if (guardian != null) {
            guardians.delete(id);
            // associated dependant associations are delete by DB cascade
            // make child inactive
            for (Client client : clients.getByGuardianId(guardian)) {
                client.setActive(false);
                clients.update(client);
            }
            System.out.println("Guardian " + id + " has been deleted.");
        } else {
            System.out.println("Guardian not found.");
        }
    }

    public void deleteInstructors(Scanner scanner, OfferingRepository offerings, InstructorRepository instructors) {
        long id = Utils.getLong(scanner, "Enter the ID of the instructor to delete:");
        if(id == 0) {
            System.out.println("Exiting.");
            return;
        }
        Instructor instructor = instructors.get(id);
        if (instructor != null) {
            instructors.delete(id);
            // offering associations are delete by DB cascade
            // update assoiated offerings
            for (Offering offering : offerings.getByInstructorId(instructor)) {
                offering.setTaken(false);
                offering.setStatus("non-available");
                offerings.update(offering);
            }

            System.out.println("Instructor " + id + " has been deleted.");
        } else {
            System.out.println("Instructor not found.");
        }
    }

    public void deleteAdmins(Scanner scanner, AdministratorRepository administrators) {
        long id = Utils.getLong(scanner, "Enter the ID of the admin to delete:");

        if (id == 0) {
            System.out.println("Invalid id entered.");
        } else if (id == this.id) {
            System.out.println("Cannot delete self.");
        } else {
            Administrator admin = administrators.get(id);
            if (admin != null && !admin.isEmpty()) {
                administrators.delete(id);
                System.out.println("Admin " + id + " has been deleted.");
            } else {
                System.out.println("Admin not found.");
            }
        }
    }

    public static Administrator login(Scanner scanner, AdministratorRepository administrators) {
        Administrator administrator = null;
        String username;
        String phone;
        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty())
                break;

            administrator = administrators.get(username, phone);

            if (administrator.isEmpty()) {
                System.out.println("Invalid credentials.");
            } else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return administrator;
    }

    public Administrator logout() {
        Administrator administrator = new Administrator();
        this.id = administrator.id;
        this.active = administrator.active;
        this.name = administrator.name;
        this.age = administrator.age;
        this.phone = administrator.phone;
        this.role = administrator.role;
        return administrator;
    }

    public static Administrator register(Scanner scanner, AdministratorRepository administrators) {
        Administrator administrator = new Administrator();
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
            if (age < 18)
                break;

            administrator = administrators.get(username, phone);

            if (administrator.isEmpty()) {
                administrator = new Administrator(0, true, username, age, phone, "admin");
                administrators.insert(administrator);
                administrator = administrators.get(username, phone);
                break;
            } else {
                System.out.println("User already exists.");
            }
        }

        return administrator;
    }

    @Override
    protected int printMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                               " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Bookings");
        System.out.println("2. View Offerings");
        System.out.println("3. View Schedules");
        System.out.println("4. View Locations");
        System.out.println("5. View Clients");
        System.out.println("6. View Guardians");
        System.out.println("7. View Instructors");
        System.out.println("8. View Admins");

        System.out.println("9. Create Booking");
        System.out.println("10. Create Offering");
        System.out.println("11. Create Schedule");
        System.out.println("12. Create Location");
        System.out.println("13. Create Client");
        System.out.println("14. Create Guardian");
        System.out.println("15. Create Instructor");
        System.out.println("16. Create Admin");

        System.out.println("17. Delete Booking");
        System.out.println("18. Delete Offering");
        System.out.println("19. Delete Schedule");
        System.out.println("20. Delete Location");
        System.out.println("21. Delete Client");
        System.out.println("22. Delete Guardian");
        System.out.println("23. Delete Instructor");
        System.out.println("24. Delete Admin");

        System.out.println("25. Logout");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 25;
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

    public void process(Scanner scanner, BookingRepository bookings, OfferingRepository offerings,
            ScheduleRepository schedules, LocationRepository locations, ClientRepository clients,
            GuardianRepository guardians, InstructorRepository instructors, AdministratorRepository administrators,
            LocationScheduleRepository locationSchedules, RepresentativeRepository representatives) {
        boolean done = false;
        while (!done) {
            int action = handleSelection(scanner);

            switch (action) {
                case 0:
                    viewBookings(bookings);
                    break;
                case 1:
                    viewOfferings(offerings);
                    break;
                case 2:
                    viewSchedules(schedules);
                    break;
                case 3:
                    viewLocations(locations);
                    break;
                case 4:
                    viewClients(clients);
                    break;
                case 5:
                    viewGuardians(guardians);
                    break;
                case 6:
                    viewInstructors(instructors);
                    break;
                case 7:
                    viewAdmins(administrators);
                    break;
                case 8:
                    createBookings(scanner, clients, offerings, bookings, locations, schedules);
                    break;
                case 9:
                    createOfferings(scanner, offerings, schedules, locations, locationSchedules);
                    break;
                case 10:
                    createSchedules(scanner, schedules);
                    break;
                case 11:
                    createLocations(scanner, locations);
                    break;
                case 12:
                    createClient(scanner, clients);
                    break;
                case 13:
                    createGuardian(scanner, guardians, clients, representatives);
                    break;
                case 14:
                    createInstructor(scanner, instructors);
                    break;
                case 15:
                    createAdministrator(scanner, administrators);
                    break;
                case 16:
                    deleteBookings(scanner, offerings, bookings);
                    break;
                case 17:
                    deleteOfferings(scanner, offerings);
                    break;
                case 18:
                    deleteSchedules(scanner, schedules);
                    break;
                case 19:
                    deleteLocations(scanner, locations);
                    break;
                case 20:
                    deleteClients(scanner, clients);
                    break;
                case 21:
                    deleteGuardians(scanner, clients, guardians);
                    break;
                case 22:
                    deleteInstructors(scanner, offerings, instructors);
                    break;
                case 23:
                    deleteAdmins(scanner, administrators);
                    break;
                case 24: // logout
                    done = true;
                    logout();
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Option invalid.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("name", name);
        json.put("phone", phone);
        json.put("age", age);

        String string = json.toString();

        return string;
    }
}
