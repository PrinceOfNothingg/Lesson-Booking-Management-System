package application.src;

public class Booking {

    private long id = -1;
    private boolean active = false;
    private long clientId = -1;
    private long offeringId = -1;
    private String status = null;
    
    protected Booking() {}
    public Booking(long id, boolean active, long clientId, long offeringId, String status) {
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return id+", "+status+", "+clientId+", "+offeringId;
    }
}