package transferobjects.reports;

/**
 * Data transfer object of component records from the PTFMS db.
 * Contains private fields for each characteristic, public getters and setters
 * for data access &amp; modification. Enforces separation of application layers.
 * Used to pass information from presentation to business to data.
 *
 * @author Lily S.
 */
public class ComponentDTO {

    private String vehicleNum;
    private String vehicleType;
    private int vehicleId;
    private int componentId;
    private String componentName;
    private String componentType;
    private double hoursUsed;

    /**
     * Getter for vehicle's unique ID
     * @return int, vehicle's unique ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Setter for vehicle's unique ID
     * @param vehicleId, int, vehicle's unique ID
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Getter for vehicle's number identifier
     * @return String, vehicle's number identifier
     */
    public String getVehicleNum() {
        return vehicleNum;
    }

    /**
     * Setter for vehicle's number identifier
     * @param vehicleNum, String, vehicle's number identifier
     */
    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    /**
     * Getter for vehicle's type (DieselBus/ElectricLightRail/DieselElectricTrain)
     * @return String, vehicle's type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Setter for vehicle's type (DieselBus/ElectricLightRail/DieselElectricTrain)
     * @param vehicleType, String, vehicle's type
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Getter for component's unique ID
     * @return int, component's unique ID
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Setter for component's unique ID
     * @param componentID, int, component's unique ID
     */
    public void setComponentId(int componentID) {
        this.componentId = componentID;
    }

    /**
     * Getter for component's name
     * @return String, component's name
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Setter for component's name
     * @param componentName, String, component's name
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * Getter for component's type classification
     * @return String, component's type classification
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * Setter for component's type classification
     * @param componentType, String, component's type classification
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    /**
     * Getter for component's total hours used
     * @return double, component's total hours used
     */
    public double getHoursUsed() {
        return hoursUsed;
    }

    /**
     * Setter for component's total hours used
     * @param hoursUsed, double, component's total hours used
     */
    public void setHoursUsed(double hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

}