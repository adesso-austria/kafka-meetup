package at.adesso.api.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "turbines")
public class Turbine extends AuditModel{
    @EmbeddedId
    private TurbineId Id;
    private float tempOutside;
    private float tempInside;
    private boolean bats;
    private float windSpeed;
    private String timestamp;
    public TurbineStatus status;

    public Turbine() {
        super();
    }

    public Turbine(String windparkId, String turbineId, String timestamp, float tempOutside,
                   float tempInside, boolean bats, float windSpeed, TurbineStatus status) {
        setTempInside(tempInside);
        setTempOutside(tempOutside);
        setBats(bats);
        setWindSpeed(windSpeed);
        setTimestamp(timestamp);
        setStatus(status);
        setId(new TurbineId(windparkId, turbineId));
    }

    public float getTempOutside() {
        return tempOutside;
    }

    public void setTempOutside(float tempOutside) {
        this.tempOutside = tempOutside;
    }

    public float getTempInside() {
        return tempInside;
    }

    public void setTempInside(float tempInside) {
        this.tempInside = tempInside;
    }

    public boolean isBats() {
        return bats;
    }

    public void setBats(boolean bats) {
        this.bats = bats;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TurbineStatus getStatus() {
        return status;
    }

    public void setStatus(TurbineStatus status) {
        this.status = status;
    }

    public TurbineId getId() {
        return Id;
    }

    public void setId(TurbineId id) {
        Id = id;
    }

    @Override
    public String toString() {
        return this.Id.windparkID + ";" + this.Id.turbineID + ";" + getTimestamp() + ";" + getTempOutside() + ";"
                + getTempInside() + ";" + isBats() + ";" + getWindSpeed() + ";" + getStatus();
    }

    public enum TurbineStatus {
        OPERATIONAL,
        CRITICAL,
        DANGEROUS
    }

    @Embeddable
    public static class TurbineId implements Serializable {
        String windparkID;
        String turbineID;

        public TurbineId() {
            super();
        }

        public TurbineId(String windparkID, String turbineID) {
            super();
            setTurbineID(turbineID);
            setWindparkID(windparkID);
        }

        public String getTurbineID() {
            return turbineID;
        }

        public void setTurbineID(String turbineID) {
            this.turbineID = turbineID;
        }

        public String getWindparkID() {
            return windparkID;
        }

        public void setWindparkID(String windparkID) {
            this.windparkID = windparkID;
        }
    }
}

