package application.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Instructor extends User {

    private ArrayList<String> specializations = new ArrayList<>();
    private ArrayList<String> availabilities = new ArrayList<>();

    public Instructor() {
    }

    public Instructor(long id, boolean active, String name, int age, String phone, String role,
            ArrayList<String> specializations, ArrayList<String> availabilities) {
        super(id, active, name, age, phone, role);
        this.specializations = specializations;
        this.availabilities = availabilities;
    }

    public ArrayList<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(ArrayList<String> specializations) {
        this.specializations = specializations;
    }

    public ArrayList<String> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(ArrayList<String> availabilities) {
        this.availabilities = availabilities;
    }

    protected void viewOfferings(OfferingRepository offerings, LocationRepository locations, ScheduleRepository schedules) {
        List<Offering> offeringList = offerings.getByInstructorId(this);
        if (offeringList.isEmpty())
            System.out.println("No offerings available.");
        else {
            for (Offering offering : offeringList) {
            List<Schedule> scheduleList = schedules.getByOfferingId(offering);
            Location location = locations.getByOfferingId(offering);

            System.out.println(offering);
            System.out.println(location);
            scheduleList.forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    private void viewAvailableOfferings(OfferingRepository offerings, LocationRepository locations, ScheduleRepository schedules) {
        List<Offering> offeringList = offerings.getTaken(false);
        if (offeringList.isEmpty())
            System.out.println("No offerings available.");
        else {
            for (Offering offering : offeringList) {
            List<Schedule> scheduleList = schedules.getByOfferingId(offering);
            Location location = locations.getByOfferingId(offering);

            System.out.println(offering);
            System.out.println(location);
            scheduleList.forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    private void updateSpecializations(Scanner scanner, InstructorRepository instructors) {
        ArrayList<String> specs = Utils.getSpecializations(scanner);
        if (specs == null || specs.isEmpty())
            return;

        this.setSpecializations(specs);

        instructors.update(this);
    }
    private void updateAvailabilities(Scanner scanner, InstructorRepository instructors) {
        ArrayList<String> avails = Utils.getAvailabilities(scanner);
        if (avails == null || avails.isEmpty())
            return;

        this.setAvailabilities(avails);

        instructors.update(this);
    }

    private void takeOfferings(Scanner scanner, OfferingRepository offerings, LocationRepository locations,
            ScheduleRepository schedules, InstructorOfferingRepository instructorOfferings) {
        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Take an Offering " + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            long offeringId = Utils.getLong(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;

            Offering offering = offerings.get(offeringId);
            if (offering == null || offering.isEmpty()) {
                System.out.println("Offering not found.");
                continue;
            }
            if (offering.isTaken()) {
                System.out.println("Offering is not available.");
                continue;
            }

            if (!getSpecializations().contains(offering.getType())) {
                System.out.println("\nInstructor specializations do not match offering.");
                continue;
            }
            if (!getAvailabilities().contains(locations.getByOfferingId(offering).getCity())) {
                System.out.println("\nInstructor availabilitiess do not match offering.");
                continue;
            }

            List<Schedule> scheduleList = schedules.getByInstructorId(this);
            List<Schedule> newScheduleList = schedules.getByOfferingId(offering);
            
            for (Schedule schedule : newScheduleList) {
                if(scheduleList.contains(schedule)) {
                    System.out.println("You already have a booking at the same time.");
                    return;
                }
            }

            instructorOfferings.insert(this, offering);

            offering.setTaken(true);
            offering.setStatus("available");
            offerings.update(offering);

            System.out.println("Offering " + offering + " has been taken.");
        }
    }

    private void removeOfferings(Scanner scanner, OfferingRepository offerings,
            InstructorOfferingRepository instructorOfferings) {

        List<Offering> offeringList = offerings.getTaken(false);
        if (offeringList.isEmpty()){
            System.out.println("No offerings available.");
            return;
        }

        boolean done = false;
        while (!done) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Remove an Offering " + this.name);
            System.out.println("--------------------------------------------------------------------------------");

            long offeringId = Utils.getLong(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;

            Offering offering = offerings.get(offeringId);

            if (instructorOfferings.getByOfferingId(offering).getInstructorId() != this.id) {
                System.out.println("\nOffering does not belong to instructor.");
                break;
            }

            instructorOfferings.delete(this, offering);

            offering.setTaken(false);
            offering.setStatus("non-available");
            offerings.update(offering);

            System.out.println("Offering " + offering + " has been removed.");
        }
    }

    public static Instructor login(Scanner scanner, InstructorRepository instructors) {
        Instructor instructor = null;
        String username;
        String phone;
        while (true) {
            username = Utils.getUserName(scanner);
            if (username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if (phone.isEmpty())
                break;

            instructor = instructors.get(username, phone);

            if (instructor.isEmpty()) {
                System.out.println("Invalid credentials.");
                instructor = null;
            } else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return instructor;
    }

    public Instructor logout() {
        Instructor instructor = new Instructor();
        this.id = instructor.id;
        this.active = instructor.active;
        this.name = instructor.name;
        this.age = instructor.age;
        this.phone = instructor.phone;
        this.role = instructor.role;
        this.specializations = instructor.specializations;
        this.availabilities = instructor.availabilities;
        return instructor;
    }

    public static Instructor register(Scanner scanner, InstructorRepository instructors) {
        Instructor instructor = new Instructor();
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

            instructor = instructors.get(phone);

            if (instructor.isEmpty()) {
                ArrayList<String> specs = Utils.getSpecializations(scanner);
                ArrayList<String> avails = Utils.getAvailabilities(scanner);

                instructor = new Instructor(0, true, username, age, phone, "instructor", specs, avails);
                long id = instructors.insert(instructor);
                if(id == 0){
                    instructor = new Instructor();
                    break;
                }
                instructor = instructors.get(id);
                break;
            } else {
                System.out.println("User conflict");
                instructor = new Instructor();
            }
        }
        if(!instructor.isEmpty())
            System.out.println("Registered " + instructor);

        return instructor;
    }

    @Override
    protected int printMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Available Offerings");
        System.out.println("2. View Your Offerings");
        System.out.println("3. Take Offerings");
        System.out.println("4. Remove Offerings");
        System.out.println("5. Update Specializations");
        System.out.println("6. Update Availabilities");
        System.out.println("7. Logout");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 7;
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

    public void process(Scanner scanner, InstructorRepository instructors, OfferingRepository offerings, LocationRepository locations,
            ScheduleRepository schedules, InstructorOfferingRepository instructorOfferings) {
        boolean done = false;
        while (!done) {
            int action = handleSelection(scanner);

            switch (action) {
                case 0: // View offerings not taken
                    viewAvailableOfferings(offerings, locations, schedules);
                    break;
                case 1: // View my offerings
                    viewOfferings(offerings, locations, schedules);
                    break;
                case 2: // take offerings
                    takeOfferings(scanner, offerings, locations, schedules, instructorOfferings);
                    break;
                case 3: // remove offering
                    removeOfferings(scanner, offerings, instructorOfferings);
                    break;
                case 4:
                    updateSpecializations(scanner, instructors);
                    break;
                case 5: 
                    updateAvailabilities(scanner, instructors);
                    break;
                case 6: // logout
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
        return this.id == ((Instructor)b).id;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("name", name);
        json.put("phone", phone);
        json.put("age", age);
        json.put("specs", specializations);
        json.put("avails", availabilities);

        String string = json.toString();

        return string;
    }
}
