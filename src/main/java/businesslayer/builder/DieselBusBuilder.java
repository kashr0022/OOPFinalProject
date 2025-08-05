
package businesslayer.builder;

import businesslayer.builder.vehicles.DieselBus;

/**
 * author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class DieselBusBuilder implements BuilderInterface {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    public DieselBusBuilder vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public DieselBusBuilder vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public DieselBusBuilder consumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    public DieselBusBuilder consumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
        return this;
    }

    public DieselBusBuilder maxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    public DieselBusBuilder activeRoute(String activeRoute) {
        this.activeRoute = activeRoute;
        return this;
    }

    public DieselBus build() {
        return new DieselBus(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }
}