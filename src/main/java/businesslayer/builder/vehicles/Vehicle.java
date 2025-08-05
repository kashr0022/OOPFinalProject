package businesslayer.builder.vehicles;

/**
 * author: Lily S.
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

    public Vehicle(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.consumptionRate = consumptionRate;
        this.consumptionUnit = consumptionUnit;
        this.maxPassengers = maxPassengers;
        this.activeRoute = activeRoute;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getConsumptionRate() {
        return consumptionRate;
    }

    public String getConsumptionUnit() {
        return consumptionUnit;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getActiveRoute() {
        return activeRoute;
    }
}
