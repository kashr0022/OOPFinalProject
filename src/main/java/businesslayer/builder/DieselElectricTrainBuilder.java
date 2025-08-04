
package businesslayer.builder;

import businesslayer.builder.vehicles.DieselBus;

public class DieselElectricTrainBuilder implements BuilderInterface {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    public DieselElectricTrainBuilder vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public DieselElectricTrainBuilder vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public DieselElectricTrainBuilder consumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    public DieselElectricTrainBuilder consumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
        return this;
    }

    public DieselElectricTrainBuilder maxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    public DieselElectricTrainBuilder activeRoute(String activeRoute) {
        this.activeRoute = activeRoute;
        return this;
    }

    public DieselBus build() {
        return new DieselBus(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }
}