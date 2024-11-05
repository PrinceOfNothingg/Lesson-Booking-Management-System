
abstract class User {

    protected long id;
    protected boolean active = true;
    protected String name;
    protected int age;
    protected String phone;
    protected boolean dependant = false;

    protected User() {}
    protected User(long id, boolean active, String name, int age, String phone, boolean dependant) {
        this.id = id;
        this.active = active;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.dependant = dependant;
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

    public boolean isDependant() {
        return dependant;
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

    public void setDependant(boolean dependant) {
        this.dependant = dependant;
    }
    
}
