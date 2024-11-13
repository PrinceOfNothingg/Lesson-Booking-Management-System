package application.src;

import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class User {

    protected long id = -1;
    protected boolean active = false;
    protected String name = null;
    protected int age = -1;
    protected String phone = null;
    protected String role = "guest";

    enum Type {
        CLIENT, GUARDIAN, INTRUCTOR, ADMIN, GUEST
    }

    protected User() {
    }

    protected User(long id, boolean active, String name, int age, String phone, String role) {
        this.id = id;
        this.active = active;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEmpty() {
        return this.name == null && this.phone == null;
    }

    protected void viewOfferings(InstructorRepository instructors, OfferingRepository offerings, LocationRepository locations, ScheduleRepository schedules) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                              View Offerings!");
        System.out.println("--------------------------------------------------------------------------------");
        
        List<Offering> offeringList = offerings.get();
        if (offeringList.isEmpty())
            System.out.println("No offerings available.");
        else {
            for (Offering offering : offeringList) {
                Instructor instructor = instructors.getByOfferingId(offering);
                List<Schedule> scheduleList = schedules.getByOfferingId(offering);
                Location location = locations.getByOfferingId(offering);

                System.out.println(offering);
                System.out.println(instructor);
                System.out.println(location);
                scheduleList.forEach(System.out::println);
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    protected int printMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                                 Welcome!");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Offerings");
        System.out.println("2. Quit to start menu.");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 2;
    }

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

    public void process(Scanner scanner, InstructorRepository instructors, OfferingRepository offerings, LocationRepository locations, ScheduleRepository schedules) {
        boolean done = false;
        while (!done) {
            int action = handleSelection(scanner);

            switch (action) {
                case 0: // View offerings
                    viewOfferings(instructors, offerings, locations, schedules);
                    break;
                case 1: // exit
                    done = true;
                    break;
                default:
                    break;
            }
        }
    }

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
