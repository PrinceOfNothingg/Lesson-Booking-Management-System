public class Client extends User {
    
    public Client(){}
    public Client(long id, boolean active, String name, int age, String phone, boolean dependant) {
        super( id, active, name, age, phone, dependant);
    }

    @Override
    public String toString() {
        return name + ": " + phone;
    }
}
