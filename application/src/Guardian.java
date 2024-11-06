package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Guardian extends User {

    private ArrayList<Guardian> dependants = new ArrayList<>();

    public Guardian(){}
    public Guardian(long id, boolean active, String name, int age, String phone, String role) {
        super( id, active, name, age, phone, role);
    }


    public ArrayList<Guardian> getDependants() {
        return dependants;
    }
    public void setDependants(ArrayList<Guardian> dependants) {
        this.dependants = dependants;
    }
    
    public void makeBooking(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeBooking'");
    }
    public void viewBookings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewBookings'");
    }
    public void viewBookingDetails(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewBookingDetails'");
    }
    public void cancelBooking(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelBooking'");
    }
    @Override
    public void viewOfferings(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOfferings'");
    }

    public static Guardian login(Scanner scanner, GuardianRepository guardians){
        Guardian guardian = new Guardian();
        String username;
        String phone;
        while(true){
            username = Utils.getUserName(scanner);
            if(username.isEmpty())
                break;

            phone = Utils.getPhone(scanner);
            if(phone.isEmpty())
                break;

            guardian = guardians.get(username, phone);

            if(guardian.isEmpty()){
                System.out.println("Invalid credentials.");
            }
            else {
                System.out.println("Login Successful.");
                break;
            }
        }

        return guardian;
    }

    public static Guardian register(Scanner scanner, GuardianRepository guardians, ClientRepository clients, RepresentativeRepository representatives){
        Guardian guardian = new Guardian();
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
            
            guardian = guardians.get(username, phone);

            if(guardian.isEmpty()){
                ArrayList<Client> children = registerDependants(scanner, clients);

                guardian = new Guardian(0, true, username, age, phone, "guardian");
                guardians.insert(guardian);
                guardian = guardians.get(username, phone);

                for (Client client : children) {
                    representatives.insert(guardian, client);
                }
                break;
            }
            else {
                System.out.println("User already exists.");
            }
        }

        return guardian;
    }

    public static ArrayList<Client> registerDependants(Scanner scanner, ClientRepository clients){
        int count = Utils.getInt(scanner, "Enter the number of dependants to register:");
        ArrayList<Client> children = new ArrayList<>();
        
        int i = 1;
        while(i++ < count) {
            System.out.println("%n%nEnter dependant "+i+" information.");
            Client c = Client.register(scanner, clients);
            children.add(c);
        }

        return children;
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}
