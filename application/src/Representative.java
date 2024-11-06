package application.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Representative extends User {

    private long id = -1;
    private boolean active = true;
    private long guardianId = -1;
    private long clientId = -1;
    private String relationship = null;

    public Representative(){}
    public Representative(long id, boolean active, long guardianId, long clientId, String relationship) {
        this.id = id;
        this.active = active;
        this.guardianId = guardianId;
        this.clientId = clientId;
        this.relationship = relationship;
    }

    
    

    @Override
    public String toString() {
        return name + ": " + phone;
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
    public long getGuardianId() {
        return guardianId;
    }
    public void setGuardianId(long guardianId) {
        this.guardianId = guardianId;
    }
    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
