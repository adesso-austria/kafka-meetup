package at.adesso.meetup;

/**
 * Public interface for our wind-turbine
 */
public interface IntTurbine {
    void setTempOutside(float tempOutside);
    float getTempOutside();
    void setTempInside(float tempInside);
    float getTempInside();
    String getTurbineID();
    void setTurbineID(String turbineID);
    String getWindparkID();
    void setWindparkID(String windparkID);
    void setBats(boolean bats);
    boolean hasBats();
    void setLat(double tempOutside);
    double getLat();
    void setLng(double tempOutside);
    double getLng();
}
