package application.src;

public class Client extends User {
    
    public Client(){}
    public Client(long id, boolean active, String name, int age, String phone, boolean dependant) {
        super( id, active, name, age, phone, dependant);
    }

    

    @Override
    public String toString() {
        return name + ": " + phone;
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
}
