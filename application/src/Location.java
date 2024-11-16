package application.src;

import org.json.simple.JSONObject;

public class Location {

    private long id = -1;
    private boolean active = false;
    private String name = null;
    private String address = null;
    private String city = null;

    protected Location() {
    }

    protected Location(long id, boolean active, String name, String address, String city) {
        this.id = id;
        this.active = active;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public boolean isActive() {
        return active;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEmpty() {
        return this.city == null;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Location)b).id;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("name", name);
        json.put("city", city);
        json.put("address", address);

        String string = "Location: {id: "+id+", active: "+active+", name: "+name+", address: "+address+", city: "+city+"}";

        return string;
    }
}
