package businesslayer.builder.vehicles;

/**
 * Abstract class that vehicle type classes extend. defines the characteristics of the child objects alongside with getters that can be utilized with any of the children.
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public abstract class Vehicle {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    /**
     * Default vehicle object creator
     * @param vehicleNumber, vehicle identifier (e.g. DB001)
     * @param vehicleType, type of vehicle (e.g. diesel bus, diesel electric train, electric light rail)
     * @param consumptionRate, rate that the vehicle consumes fuel
     * @param consumptionUnit, fuel type vehicle uses
     * @param maxPassengers, maximum amount of passengers that the vehicle can hold
     * @param activeRoute, vehicle's current route
     */
    public Vehicle(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.consumptionRate = consumptionRate;
        this.consumptionUnit = consumptionUnit;
        this.maxPassengers = maxPassengers;
        this.activeRoute = activeRoute;
    }

    /**
     * Getter for vehicle num (e.g. DB001)
     * @return String, vehicle number, string due to alphabetic chars used in id naming convention
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Getter for vehicle type (e.g. diesel bus)
     * @return String, vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Getter for fuel consumption rate of vehicle
     * @return double, fuel consumption rate
     */
    public double getConsumptionRate() {
        return consumptionRate;
    }

    /**
     * Getter for consumption unit of a vehicle
     * @return String, consumption unit
     */
    public String getConsumptionUnit() {
        return consumptionUnit;
    }

    /**
     * Getter for maximum passenger number value for vehicle
     * @return int, max passenger capacity
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Getter for vehicle's current active route
     * @return String, vehicle's active route
     */
    public String getActiveRoute() {
        return activeRoute;
    }
}
