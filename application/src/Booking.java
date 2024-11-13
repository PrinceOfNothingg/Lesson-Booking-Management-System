package application.src;

import java.util.List;

import org.json.simple.JSONObject;

public class Booking {
    private long id = -1;
    private boolean active = false;
    private String status = null;
    private long clientId = -1;
    private long offeringId = -1;

    protected Booking() {}

    public Booking(long id, boolean active, String status, long clientId, long offeringId) {
        this.id = id;
        this.active = active;
        this.clientId = clientId;
        this.offeringId = offeringId;
        this.status = status;
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

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }

    @Override
    public boolean equals(Object b){
        return this.id == ((Booking)b).id;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("status", status);
        json.put("clientId", clientId);
        json.put("offeringId", offeringId);

        String string = json.toString();

        return string;
    }
}
