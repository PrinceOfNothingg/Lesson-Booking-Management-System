package application.src;

import org.json.simple.JSONObject;

public class Offering {

    private long id = -1;
    private boolean active = false;
    private String status = "non-available";
    private boolean taken = false;
    private String type = null;
    private boolean mode = false;
    private int seats = 1;

    protected Offering() {
    }

    public Offering(long id, boolean active, String status, boolean taken, String type, boolean mode, int seats) {
        this.id = id;
        this.active = active;
        this.taken = taken;
        this.status = status;
        this.type = type;
        this.mode = mode;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isGroup() {
        return mode;
    }

    public void setGroup(boolean mode) {
        this.mode = mode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Offering)b).id;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("status", status);
        json.put("taken", taken);
        json.put("type", type);
        json.put("mode", mode);
        json.put("seats", seats);

        String string = json.toString();

        return string;
    }
}
