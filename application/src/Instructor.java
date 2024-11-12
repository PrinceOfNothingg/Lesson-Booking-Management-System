package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Instructor extends User {

    private ArrayList<String> specializations = new ArrayList<>();
    private ArrayList<String> availabilities = new ArrayList<>();

    public Instructor(){}
    public Instructor(long id, boolean active, String name, int age, String phone, String role, ArrayList<String> specializations, ArrayList<String> availabilities) {
        super( id, active, name, age, phone, role);
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
   
    public void viewOfferings(OfferingRepository offerings){
        offerings.getByInstructorId(this).forEach(System.out::println);
    }

    public void viewAvailableOfferings(OfferingRepository offerings){
        offerings.getTaken(false).forEach(System.out::println);
    }

    public void takeOfferings(Scanner scanner, OfferingRepository offerings, LocationRepository locations, InstructorOfferingRepository instructorOfferings){
        boolean done = false;
        while(!done){
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Take an Offering" + this.name);
            System.out.println("--------------------------------------------------------------------------------");
            
            int offeringId = Utils.getInt(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;
        
            Offering offering = offerings.get(offeringId);
            
            if(!getSpecializations().contains(offering.getType())){
                System.out.println("\nInstructor specializations do not match offering.");
                break;
            }
            if(!getAvailabilities().contains(locations.getByOfferingId(offering).getCity())){
                System.out.println("\nInstructor availabilitiess do not match offering.");
                break;
            }

            instructorOfferings.insert(this, offering);

            offering.setTaken(true);
            offering.setStatus("available");
            offerings.update(offering);

            System.out.println("Offering " + offering + " has been taken.");
        }
    }

    public void removeOfferings(Scanner scanner, OfferingRepository offerings, InstructorOfferingRepository instructorOfferings){
        boolean done = false;
        while(!done){
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("                          Remove an Offering" + this.name);
            System.out.println("--------------------------------------------------------------------------------");
            
            int offeringId = Utils.getInt(scanner, "Please enter the id of an Offering (q to quit):");
            if (offeringId == 0)
                break;
        
            Offering offering = offerings.get(offeringId);

            if(instructorOfferings.getByOfferingId(offering).getInstructorId() != this.id){
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

    @Override
    User get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    @Override
    boolean update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    boolean delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public static Instructor login(Scanner scanner, InstructorRepository instructors){
        Instructor instructor = null;
        String username;
        String phone;
        while(true){
            username = Utils.getUserName(scanner);
            if(username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if(phone.isEmpty())
                break;

            instructor = instructors.get(username, phone);

            if(instructor.isEmpty()){
                System.out.println("Invalid credentials.");
            }
            else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return instructor;
    }

    public Instructor logout(){
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

    public static Instructor register(Scanner scanner, InstructorRepository instructors){
        Instructor instructor = new Instructor();
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

            ArrayList<String> specs = Utils.getSpecializations(scanner);
            ArrayList<String> avails = Utils.getAvailabilities(scanner);
            
            
            instructor = instructors.get(username, phone);

            if(instructor.isEmpty()){
                instructor = new Instructor(0, true, username, age, phone, "instructor", specs, avails);
                instructors.insert(instructor);
                instructor = instructors.get(username, phone);
                break;
            }
            else {
                System.out.println("User already exists.");
            }
        }

        return instructor;
    }
    

    private int printMenu(){
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("                          " + this.name);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please select one of the following options:");
        System.out.println("1. View Available Offerings");
        System.out.println("2. View Your Offerings");
        System.out.println("3. Take Offerings");
        System.out.println("4. Remove Offerings");
        System.out.println("5. Logout");
        System.out.println("--------------------------------------------------------------------------------\n");
        return 5;
    }

    public int handleSelection(Scanner scanner) {
        int choice = -1;
        int min = 0;
        int max = -1;
        do {
            max = printMenu();
            choice = Utils.getSelection(scanner, min, max);
        } while (choice < min || choice > max);
        return choice;
    }

    public void process(Scanner scanner, OfferingRepository offerings, LocationRepository locations, InstructorOfferingRepository instructorOfferings){
        boolean done = false;
        while(!done){
            int action = handleSelection(scanner);

            switch (action) {
                case 0: // View offerings not taken
                    viewAvailableOfferings(offerings);
                    break;
                case 1: // View my offerings
                    viewOfferings(offerings);
                    break;
                case 2: // take offerings
                    takeOfferings(scanner, offerings, locations, instructorOfferings);
                    break;
                case 3: // remove offering
                    removeOfferings(scanner, offerings, instructorOfferings);
                    break;
                case 4: // logout
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
        return name + ": " + phone;
    }
}
