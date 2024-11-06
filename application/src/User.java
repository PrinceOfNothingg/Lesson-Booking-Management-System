package application.src;

public class User {

    protected long id = -1;
    protected boolean active = true;
    protected String name = null;
    protected int age = -1;
    protected String phone = null;
    protected String role = null;

    enum Type {CLIENT, GUARDIAN, INTRUCTOR, ADMIN, GUEST}

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

    public boolean isEmpty() {
        return this.name == null && this.phone == null && this.role == null;
    }

    public void viewOfferings(){
        throw new UnsupportedOperationException("Unimplemented method 'viewBookings'");
    }

    User get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    boolean update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    boolean delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    public String toString() {
        return name + ": " + phone;
    }
}
