package application.src;

public class Client extends User {

    protected boolean dependant = false;

    public Client(){}
    public Client(long id, boolean active, String name, int age, String phone, boolean dependant) {
        super( id, active, name, age, phone, "client");
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

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}
