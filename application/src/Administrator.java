package application.src;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(long id, boolean active, String name, int age, String phone, String role) {
        super(id, active, name, age, phone, role);
    }

    public void viewBookings(BookingRepository bookings) {
        bookings.get().forEach(System.out::println);
    }

    @Override
    public void viewOfferings(OfferingRepository offerings) {
        offerings.get().forEach(System.out::println);
    }

    public void viewSchedules(ScheduleRepository schedules) {
        schedules.get().forEach(System.out::println);
    }

    public void viewLocations(LocationRepository locations) {
        locations.get().forEach(System.out::println);
    }

    public void viewClients(ClientRepository clients) {
        clients.get().forEach(System.out::println);
    }

    public void viewGuardians(GuardianRepository guardians) {
        guardians.get().forEach(System.out::println);
    }

    public void viewInstructors(InstructorRepository instructors) {
        instructors.get().forEach(System.out::println);
    }

    public void viewAdmins(AdministratorRepository administrators) {
        administrators.get().forEach(System.out::println);
    }

    public void createBookings(BookingRepository bookings) {
        throw new UnsupportedOperationException("Unimplemented method 'createBookings'");
    }

    public void createOfferings(Scanner scanner, OfferingRepository offerings, ScheduleRepository schedules,
            LocationRepository locations, LocationScheduleRepository locationSchedules) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          Create an Offering" + this.name);
        System.out.println("--------------------------------------------------------------------------------");

        String val = Utils.getString(scanner, "Create a new Schedule? y/n (q to quit):");
        if (!val.isEmpty() || val.equalsIgnoreCase("y")) {
            createSchedules(scanner, schedules);
        }

        viewSchedules(schedules);
        long id = Utils.getInt(scanner, "Enter the schedule id:");
        Schedule schedule = schedules.get(id);

        // create location
        val = Utils.getString(scanner, "Create a new location? y/n (q to quit):");
        if (!val.isEmpty() || val.equalsIgnoreCase("y")) {
            createLocations(scanner, locations);
        }

        viewLocations(locations);
        id = Utils.getInt(scanner, "Enter the location id:");
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

            LocationSchedule ls = new LocationSchedule(0, true, location.getId(), schedule.getId(), offering.getId());
            locationSchedules.insert(ls);
            System.out.println("Offering " + offering + " has been created.");
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
                LocationSchedule ls = new LocationSchedule(0, true, location.getId(), schedule.getId(),
                        offering.getId());
                locationSchedules.insert(ls);

                System.out.println("Offering " + offering + " has been created.");
            }
        }
    }

    public void createSchedules(Scanner scanner, ScheduleRepository schedules) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Create a schedule" + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            String startDate = Utils.getDate(scanner, "Please enter the start date 'YYYY-MM-DD':");
            if (startDate.isEmpty())
                break;
            String endDate = Utils.getDate(scanner, "Please enter the end date 'YYYY-MM-DD':");
            if (endDate.isEmpty())
                break;
            String startTime = Utils.getTime(scanner, "Please enter the start time 'hh:mm:ss':");
            if (startTime.isEmpty())
                break;
            String endTime = Utils.getTime(scanner, "Please enter the end time 'hh:mm:ss':");
            if (endTime.isEmpty())
                break;
            String dow = Utils.getString(scanner,
                    "Please enter the array of days of the week '{sun, mon, tue, wed, thu, fri, sat}':");
            if (dow.isEmpty())
                break;

            Schedule schedule = new Schedule(0, true, startDate, endDate, startTime, endTime, null, dow);

            schedules.insert(schedule);
            schedule = schedules.get(startDate, endDate, startTime, endTime, dow);

            System.out.println("Schedule " + schedule + " has been created.");

            if (Utils.getString(scanner, "Enter q to quit:").isEmpty())
                break;
        }
    }

    public void createLocations(Scanner scanner, LocationRepository locations) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Create a location" + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            String name = Utils.getString(scanner, "Please enter location name:");
            if (name.isEmpty())
                break;
            String address = Utils.getString(scanner, "Please enter location address:");
            if (address.isEmpty())
                break;
            String city = Utils.getString(scanner, "Please enter location city:");
            if (city.isEmpty())
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

            if (Utils.getString(scanner, "Enter q to quit:").isEmpty())
                break;
        }
    }

    public void createClients(ClientRepository clients) {
        throw new UnsupportedOperationException("Unimplemented method 'createClients'");
    }

    public void createGuardians(GuardianRepository guardians) {
        throw new UnsupportedOperationException("Unimplemented method 'createGuardians'");
    }

    public void createInstructors(InstructorRepository instructors) {
        throw new UnsupportedOperationException("Unimplemented method 'createInstructors'");
    }

    public void createAdmins(AdministratorRepository administrators) {
        throw new UnsupportedOperationException("Unimplemented method 'createAdmins'");
    }

    public void deleteBookings(BookingRepository bookings) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBookings'");
    }

    public void deleteOfferings(OfferingRepository offerings) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteOfferings'");
    }

    public void deleteSchedules(ScheduleRepository schedules) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteSchedules'");
    }

    public void deleteLocations(LocationRepository locations) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteLocations'");
    }

    public void deleteClients(ClientRepository clients) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteClients'");
    }

    public void deleteGuardians(GuardianRepository guardians) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteGuardians'");
    }

    public void deleteInstructors(InstructorRepository instructors) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteInstructors'");
    }

    public void deleteAdmins(AdministratorRepository administrators) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAdmins'");
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

        System.out.println(administrator);

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
        System.out.println("                          " + this.name);
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
            LocationScheduleRepository locationSchedules) {
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
                    createBookings(bookings);
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
                // case 12:
                // createClients(clients);
                // break;
                // case 13:
                // createGuardians(guardians);
                // break;
                // case 14:
                // createInstructors(instructors);
                // break;
                // case 15:
                // createAdmins(administrators);
                // break;
                // case 16:
                // deleteBookings(bookings);
                // break;
                // case 17:
                // deleteOfferings(offerings);
                // break;
                // case 18:
                // deleteSchedules(schedules);
                // break;
                // case 19:
                // deleteLocations(locations);
                // break;
                // case 20:
                // deleteClients(clients);
                // break;
                // case 21:
                // deleteGuardians(guardians);
                // break;
                // case 22:
                // deleteInstructors(instructors);
                // break;
                // case 23:
                // deleteAdmins(administrators);
                // break;
                case 24: // logout
                    done = true;
                    logout();
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
