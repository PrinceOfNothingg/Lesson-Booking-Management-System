package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class LocationSchedule {

    private long id = -1;
    private boolean active = false;
    private long locationId = -1;
    private long scheduleId = -1;
    private long offeringId = -1;

    

    public LocationSchedule(){}
    public LocationSchedule(long id, boolean active, long locationId, long scheduleId, long offeringId) {
        this.id = id;
        this.active = active;
        this.locationId = locationId;
        this.scheduleId = scheduleId;
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
    public long getOfferingId() {
        return offeringId;
    }
    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }
}
