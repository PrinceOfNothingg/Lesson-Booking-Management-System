package application.src;

public class Event {

    private long id = -1;
    private boolean active = false;
    private long locationScheduleId = -1;
    private long offeringId = -1;

    public Event() {
    }

    public Event(long id, boolean active, long offeringId, long locationScheduleId) {
        this.id = id;
        this.active = active;
        this.locationScheduleId = locationScheduleId;
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


    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }

    public long getLocationScheduleId() {
        return locationScheduleId;
    }

    public void setLocationScheduleId(long locationScheduleId) {
        this.locationScheduleId = locationScheduleId;
    }

    public boolean isEmpty(){
        return this.offeringId == -1 && this.locationScheduleId == -1;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Event)b).id;
    }
}
