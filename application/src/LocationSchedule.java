package application.src;


public class LocationSchedule {

    private long id = -1;
    private boolean active = false;
    private long locationId = -1;
    private long scheduleId = -1;

    public LocationSchedule() {
    }

    public LocationSchedule(long id, boolean active, long locationId, long scheduleId) {
        this.id = id;
        this.active = active;
        this.locationId = locationId;
        this.scheduleId = scheduleId;
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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public boolean isEmpty(){
        return this.locationId == -1 && this.scheduleId == -1;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((LocationSchedule)b).id;
    }
}
