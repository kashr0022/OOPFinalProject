
package transferobjects.reports;

import java.time.LocalDateTime;

/**
 *
 * @author Khairunnisa Ashri, Lily S.
 */
public class GpsDTO {

    private String startingLocation;
    private String endingLocation;
    private int gpsID;
    private int staffID;
    private int vehicleID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;

    /**
     *
     * @return starting location of GPS route
     */
    public String getStartingLocation() {
        return startingLocation;
    }

    /**
     * take in startinglocation, set it to local field in the object
     * @param startingLocation, location of GPS at start of route
     */
    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    /**
     *
     * @return ending location of GPS route
     */
    public String getEndingLocation() {
        return endingLocation;
    }

    /**
     * take in endinglocation, set it to local field in the object
     * @param endingLocation, location of GPS at end of route
     */
    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

    /**
     * @return the gpsID
     */
    public int getGpsID() {
        return gpsID;
    }

    /**
     * @param gpsID the gpsID to set
     */
    public void setGpsID(int gpsID) {
        this.gpsID = gpsID;
    }

    /**
     * @return the staffID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * @return the vehicleID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * @param vehicleID the vehicleID to set
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }


}
