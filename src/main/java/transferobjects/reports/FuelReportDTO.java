
package transferobjects.reports;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a fuel report.
 * Contains details about fuel consumption, vehicle, and related metrics.
 *
 * @author Khairunnisa Ashri
 */
public class FuelReportDTO {
    private int reportID;
    private int staffID;
    private int vehicleID;
    private String vehicleType;
    private double fuelConsumed;
    private double distanceTraveled;
    private String fuelType;
    private double fuelEfficiency;
    private String fuelEfficiencyUnit;
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

    /**
     * @return the fuelConsumed
     */
    public double getFuelConsumed() {
        return fuelConsumed;
    }

    /**
     * @param fuelConsumed the fuelConsumed to set
     */
    public void setFuelConsumed(double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    /**
     * @return the fuelEfficiency
     */
    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    /**
     * @param fuelEfficiency the fuelEfficiency to set
     */
    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * @return the fuelEfficiencyUnit
     */
    public String getFuelEfficiencyUnit() {
        return fuelEfficiencyUnit;
    }

    /**
     * @param fuelEfficiencyUnit the fuelEfficiencyUnit to set
     */
    public void setFuelEfficiencyUnit(String fuelEfficiencyUnit) {
        this.fuelEfficiencyUnit = fuelEfficiencyUnit;
    }


}
