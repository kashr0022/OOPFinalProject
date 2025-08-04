package transferobjects.reports;

import java.time.LocalDateTime;

/**
 *
 * @author Khairunnisa Asrhi
 */
public class MaintenanceLogDTO {
    private int logID;
    private int staffID;
    private int gpsID;
    private int vehicleID;
    private String vehicleType;
    private int componentID;
    private String componentName;
    private double usageAmt;
    private String diagnostics;
    private LocalDateTime date;
    private String status;
    private double cost;

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

    /**
     * @return the componentName
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * @param componentName the componentName to set
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * @return the diagnostics
     */
    public String getDiagnostics() {
        return diagnostics;
    }

    /**
     * @param diagnostics the diagnostics to set
     */
    public void setDiagnostics(String diagnostics) {
        this.diagnostics = diagnostics;
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
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    
}
