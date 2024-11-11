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


    public void takeOffering(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeOffering'");
    }
    public void removeOffering(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOffering'");
    }
    public void viewOfferings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOfferings'");
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
        Instructor instructor;
        String username;
        String phone;
        while(true){
            System.out.println("Enter your username:");
            username = scanner.nextLine();

            System.out.println("Enter your phone:");
            phone = scanner.nextLine();

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
    
    public void process(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}
