package application.src;

public class InstructorOffering {

    private long id = -1;
    private boolean active = false;
    private long instructorId = -1;
    private long offeringId = -1;

    public InstructorOffering() {
    }

    public InstructorOffering(long id, boolean active, long instructorId, long offeringId) {
        this.id = id;
        this.active = active;
        this.instructorId = instructorId;
        this.offeringId = offeringId;
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

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }

}
