package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    protected long id;
    protected boolean active = true;
    protected String name;
    protected int age;
    protected String phone;
    protected String role;

    protected User() {}
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

    public void viewOfferings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewBookings'");
    }

    public static User login(Scanner scanner, UserRepository repo){
        // ask creds
        // attempt login
        // err ask register
        User user;

        while(true){
            String username, phone;

            System.out.println("Enter your username:");
            username = scanner.nextLine();

            System.out.println("Enter your phone:");
            phone = scanner.nextLine();

            user = repo.get(username, phone);

            if(user.name == null){
                System.out.println("Invalid credentials.");
            }
            else {
                System.out.println("Authentication successful.");
                break;
            }
        }
        return user;
    }
}
