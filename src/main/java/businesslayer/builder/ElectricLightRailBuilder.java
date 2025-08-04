
package businesslayer.builder;

import businesslayer.builder.vehicles.DieselBus;

public class ElectricLightRailBuilder implements BuilderInterface {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    public ElectricLightRailBuilder vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public ElectricLightRailBuilder vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public ElectricLightRailBuilder consumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    public ElectricLightRailBuilder consumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
        return this;
    }

    public ElectricLightRailBuilder maxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    public ElectricLightRailBuilder activeRoute(String activeRoute) {
        this.activeRoute = activeRoute;
        return this;
    }

    public DieselBus build() {
        return new DieselBus(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }
}