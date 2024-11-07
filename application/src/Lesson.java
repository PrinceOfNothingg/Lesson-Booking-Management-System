package application.src;

public class Lesson {

    private long id = -1;
    private boolean active = false;
    private String type = null;
    private boolean mode = false;
    private int seats = 1;
    
    protected Lesson() {}
    public Lesson(long id, boolean active, String type, boolean mode, int seats) {
        this.id = id;
        this.active = active;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isMode() {
        return mode;
    }
    public void setMode(boolean mode) {
        this.mode = mode;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public String toString() {
        return "Lesson:"+type+", format:"+mode;
    }
}
