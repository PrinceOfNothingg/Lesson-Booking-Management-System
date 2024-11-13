package application.src;

import org.json.simple.JSONObject;

public class Booking {
    // TODO fix add location and schedule etc to retrieve and present all pffering
    // related data.

    private long id = -1;
    private boolean active = false;
    private String status = null;
    private long clientId = -1;
    private long offeringId = -1;
    private String offeringStatus = null;
    private boolean taken = false;
    private String type = null;
    private boolean mode = false;
    private int seats = 1;

    protected Booking() {
    }

    public Booking(long id, boolean active, String status, long clientId, long offeringId, String offeringStatus,
            boolean taken, String type, boolean mode, int seats) {
        this.id = id;
        this.active = active;
        this.clientId = clientId;
        this.offeringId = offeringId;
        this.status = status;
        this.offeringStatus = offeringStatus;
        this.taken = taken;
        this.type = type;
        this.mode = mode;
        this.seats = seats;
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

    public String getOfferingStatus() {
        return offeringStatus;
    }

    public void setOfferingStatus(String offeringStatus) {
        this.offeringStatus = offeringStatus;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("active", active);
        json.put("status", status);
        json.put("clientId", clientId);
        json.put("offeringId", offeringId);
        json.put("offeringStatus", offeringStatus);
        json.put("taken", taken);
        json.put("type", type);
        json.put("mode", mode);
        json.put("seats", seats);

        String string = json.toString();

        return string;
    }
}
