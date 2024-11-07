package application.src;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Schedule {

    enum WeekDay {SUN, MON, TUE, WED, THU, FRI, SAT}

    private long id = -1;
    private boolean active = false;
    private Date startDate = null;
    private Date endDate = null;
    private Time startTime = null;
    private Time endTime = null;
    private ArrayList<Integer> timeSlots = null;
    private String dow = null;

    protected Schedule() {}
    protected Schedule(long id, boolean active, Date startDate, Date endDate, Time startTime, Time endTime, ArrayList<Integer> slots, String dow ) {
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

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
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
