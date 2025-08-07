package transferobjects.reports;

/**
 * Data transfer object of component records from the PTFMS db. Contains private fields for each characteristic, public getters and setters for data access & modification. enforces separation of application layers. Used to pass information from presentation to business to data
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

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentID) {
        this.componentId = componentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public double getHoursUsed() {
        return hoursUsed;
    }

    public void setHoursUsed(double hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

}
