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
public class FuelReportDTO {
    private int reportID;
    private int staffID;
    private int vehicleID;
    private String vehicleType;
    private double usageAmt;
    private double distanceTraveled;
    private String fuelType;
    private LocalDateTime date;
    private String status;

    /**
     * @return the reportID
     */
    public int getReportID() {
        return reportID;
    }

    /**
     * @param reportID the reportID to set
     */
    public void setReportID(int reportID) {
        this.reportID = reportID;
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
     * @return the distanceTraveled
     */
    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * @param distanceTraveled the distanceTraveled to set
     */
    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    /**
     * @return the fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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
    
    public double getFuelConsumed() {
        return usageAmt;
    }
    
    public double getFuelEfficiency() {
        return distanceTraveled == 0 ? 0 : distanceTraveled / usageAmt;
    }

    /**
     * @return the vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


}
