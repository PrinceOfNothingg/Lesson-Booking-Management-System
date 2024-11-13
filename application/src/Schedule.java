package application.src;

import org.json.simple.JSONObject;

public class Schedule {

    private long id = -1;
    private boolean active = false;
    private String slot = null;

    protected Schedule() {
    }

    protected Schedule(long id, boolean active, String slot) {
        this.id = id;
        this.active = active;
        this.slot = slot;
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

    public boolean isEmpty() {
        return this.id == -1 && this.slot == null;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Schedule)b).id;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("slot", slot);
        return json.toString();
    }
}
