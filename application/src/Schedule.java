package application.src;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Schedule {

    enum WeekDay {SUN, MON, TUE, WED, THU, FRI, SAT}

    private long id = -1;
    private boolean active = false;
    private String slot = null;
    private List<String> dow = null;

    protected Schedule() {}
    protected Schedule(long id, boolean active, String slot, List<String> dow ) {
        this.id = id;
        this.active = active;
        this.slot = slot;
        this.dow = dow;
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
    public String getSlot() {
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }
    public List<String> getDow() {
        return dow;
    }
    public void setDow(List<String> dow) {
        this.dow = dow;
    }

    public boolean isEmpty() {
        return this.slot == null && this.dow == null;
    }

    public String toString() {
       JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("slot", slot);
        json.put("dow", dow);
        return json.toString();
    }
}
