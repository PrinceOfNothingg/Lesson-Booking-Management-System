package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {

    public Administrator(){}
    public Administrator(long id, boolean active, String name, int age, String phone, String role) {
        super( id, active, name, age, phone, role);
    }

    public void getOfferings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOfferings'");
    }
    public void createOffering(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOffering'");
    }
    public void updateOffering(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOffering'");
    }
    public void deleteOffering(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOffering'");
    }

    public void getUsers(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsers'");
    }
    public void createUser(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
    public void updateUser(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
    public void deleteUser(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public void getBookings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookings'");
    }
    public void createBooking(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBooking'");
    }
    public void updateBooking(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBooking'");
    }
    public void deleteBooking(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBooking'");
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

    public static Administrator login(Scanner scanner, AdministratorRepository administrators){
        Administrator administrator;
        String username;
        String phone;
        while(true){
            System.out.println("Enter your username:");
            username = scanner.nextLine();

            System.out.println("Enter your phone:");
            phone = scanner.nextLine();

            administrator = administrators.get(username, phone);

            if(administrator.isEmpty()){
                System.out.println("Invalid credentials.");
            }
            else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return administrator;
    }

    public static Administrator register(Scanner scanner, AdministratorRepository administrators){
        Administrator administrator = new Administrator();
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

            administrator = administrators.get(username, phone);

            if(administrator.isEmpty()){
                administrator = new Administrator(0, true, username, age, phone, "admin");
                administrators.insert(administrator);
                administrator = administrators.get(username, phone);
                break;
            }
            else {
                System.out.println("User already exists.");
            }
        }

        return administrator;
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}
