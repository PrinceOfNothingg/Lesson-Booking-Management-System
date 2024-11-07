package application.src;

public class Offering {

    private long id = -1;
    private boolean active = false;
    private String status = "non-available";
    private boolean taken = false;
    private long lessonId = -1;
    
    protected Offering() {}
    public Offering(long id, boolean active, String status, boolean taken, long lessonId) {
        this.id = id;
        this.active = active;
        this.taken = taken;
        this.status = status;
        this.lessonId = lessonId;
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
    public long getLessonId() {
        return lessonId;
    }
    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String toString() {
        return id+", "+status+", "+taken+", "+lessonId;
    }
}