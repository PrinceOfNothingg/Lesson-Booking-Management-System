package application.src;

import java.util.Scanner;

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

    public static Client login(Scanner scanner, ClientRepository clients){
        Client client = new Client();
        String username;
        String phone;
        String input;

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
    public String toString() {
        return name + ": " + phone;
    }
}
