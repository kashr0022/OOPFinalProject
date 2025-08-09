package transferobjects.vehicles;

/**
 * DTO of Vehicle records from the PTFMS db. Contains private fields for each
 * characteristic, public getters and setters for data access and modification. enforces separation of
 * application layers. Used to pass information from presentation to business to data
 * 
 * @author Lily S.
 * @author Khairunnisa Ashri
 */
public class VehicleDTO {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    /**
     * Gets the vehicle number.
     * 
     * @return the vehicle number as a String
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Sets the vehicle number.
     * 
     * @param vehicleNumber the vehicle number to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * Gets the vehicle type.
     * 
     * @return the vehicle type as a String
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type.
     * 
     * @param vehicleType the vehicle type to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the consumption rate of the vehicle.
     * 
     * @return the consumption rate as a double
     */
    public double getConsumptionRate() {
        return consumptionRate;
    }

    /**
     * Sets the consumption rate of the vehicle.
     * 
     * @param consumptionRate the consumption rate to set
     */
    public void setConsumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    /**
     * Gets the unit used for the consumption rate.
     * 
     * @return the consumption unit as a String
     */
    public String getConsumptionUnit() {
        return consumptionUnit;
    }

    /**
     * Sets the unit for the consumption rate.
     * 
     * @param consumptionUnit the consumption unit to set
     */
    public void setConsumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
    }

    /**
     * Gets the maximum number of passengers the vehicle can carry.
     * 
     * @return the max passengers as an int
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Sets the maximum number of passengers the vehicle can carry.
     * 
     * @param maxPassengers the max passengers to set
     */
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * Gets the active route assigned to the vehicle.
     * 
     * @return the active route as a String
     */
    public String getActiveRoute() {
        return activeRoute;
    }

    /**
     * Sets the active route assigned to the vehicle.
     * 
     * @param activeRoute the active route to set
     */
    public void setActiveRoute(String activeRoute) {
        this.activeRoute = activeRoute;
    }
}
