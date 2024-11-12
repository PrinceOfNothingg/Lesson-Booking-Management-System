package application.src;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Schedule {

    enum WeekDay {SUN, MON, TUE, WED, THU, FRI, SAT}

    private long id = -1;
    private boolean active = false;
    private String startDate = null;
    private String endDate = null;
    private String startTime = null;
    private String endTime = null;
    private ArrayList<Integer> timeSlots = null;
    private String dow = null;

    protected Schedule() {}
    protected Schedule(long id, boolean active, String startDate, String endDate, String startTime, String endTime, ArrayList<Integer> slots, String dow ) {
        this.id = id;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeSlots = slots;
        this.dow = dow;
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(long id) {
        this.id = id;
    }
   
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public ArrayList<Integer> getTimeSlots() {
        return timeSlots;
    }
    public void setTimeSlots(ArrayList<Integer> timeSlots) {
        this.timeSlots = timeSlots;
    }
    public String getDow() {
        return dow;
    }
    public void setDow(String dow) {
        this.dow = dow;
    }

    public boolean isEmpty() {
        return this.startDate == null && 
                this.startTime == null &&
                this.endDate == null &&
                this.endTime == null;
    }

    public String toString() {
        return "On"+ dow +" from "+ startTime + " to " + endTime + " from " + startDate +" "+endDate;
    }
}
