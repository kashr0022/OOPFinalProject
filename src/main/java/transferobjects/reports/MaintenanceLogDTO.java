/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects.reports;

import java.time.LocalDateTime;

/**
 *
 * @author A
 */
public class MaintenanceLogDTO {
    private int logID;
    private int staffID;
    private int gpsID;
    private int vehicleID;
    private int componentID;
    private double usageAmt;
    private LocalDateTime date;
    private String status;

    /**
     * @return the logID
     */
    public int getLogID() {
        return logID;
    }

    /**
     * @param logID the logID to set
     */
    public void setLogID(int logID) {
        this.logID = logID;
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
     * @return the componentID
     */
    public int getComponentID() {
        return componentID;
    }

    /**
     * @param componentID the componentID to set
     */
    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    /**
     * @return the usageAmt
     */
    public double getUsageAmt() {
        return usageAmt;
    }

    /**
     * @param usageAmt the usageAmt to set
     */
    public void setUsageAmt(double usageAmt) {
        this.usageAmt = usageAmt;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
}
