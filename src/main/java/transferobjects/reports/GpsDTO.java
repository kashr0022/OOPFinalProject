
package transferobjects.reports;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing GPS tracking information for a vehicle.
 * Contains details about start and end locations, timestamps, related staff and vehicle IDs, and any additional notes.
 * 
 * @author Khairunnisa Ashri, Lily S.
 */
public class GpsDTO {

    private int gpsID;
    private int staffID;
    private int vehicleID;
    private String startingLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String endingLocation;
    private LocalDateTime scheduledEndTime;
    private String notes;

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
     * @return the startingLocation
     */
    public String getStartingLocation() {
        return startingLocation;
    }

    /**
     * @param startingLocation the startingLocation to set
     */
    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
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
     * @return the endingLocation
     */
    public String getEndingLocation() {
        return endingLocation;
    }

    /**
     * @param endingLocation the endingLocation to set
     */
    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

    /**
     * @return the scheduledEndTime
     */
    public LocalDateTime getScheduledEndTime() {
        return scheduledEndTime;
    }

    /**
     * @param scheduledEndTime the scheduledEndTime to set
     */
    public void setScheduledEndTime(LocalDateTime scheduledEndTime) {
        this.scheduledEndTime = scheduledEndTime;
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