package at.adesso.enrich.model;

public class TurbineData {
    private String turbineID;
    private String windparkID;
    private float tempOutside;
    private float tempInside;
    private boolean bats;
    private float windSpeed;
    private String timestamp;
    public TurbineStatus status;

    public TurbineData(String windparkID, String turbineID, String timestamp, float tempOutside,
                float tempInside, boolean bats, float windSpeed) {
        setTempInside(tempInside);
        setTempOutside(tempOutside);
        setBats(bats);
        setTurbineID(turbineID);
        setWindSpeed(windSpeed);
        setWindparkID(windparkID);
        setTimestamp(timestamp);
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

    @Override
    public String toString() {
        return getWindparkID() + ";" + getTurbineID() + ";" + getTimestamp() + ";" + getTempOutside() + ";"
                + getTempInside() + ";" + isBats() + ";" + getWindSpeed() + ";" + getStatus();
    }

    public enum TurbineStatus {
        OPERATIONAL,
        CRITICAL,
        DANGEROUS
    }
}