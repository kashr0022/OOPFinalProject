
package businesslayer.builder;

import businesslayer.builder.vehicles.DieselBus;

/**
 * Builder class for the Diesel Electric train vehicle that implements builder interface.
 * author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class DieselElectricTrainBuilder implements BuilderInterface {
    private String vehicleNumber;
    private String vehicleType;
    private double consumptionRate;
    private String consumptionUnit;
    private int maxPassengers;
    private String activeRoute;

    /**
     * Setter for vehicleNumber (i.e. DB001), returns this instance to use in method chain
     * @param vehicleNumber, vehicle identifier number
     * @return Builder instance
     */
    public DieselElectricTrainBuilder vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    /**
     * Setter for vehicle type (i.e. diesel bus), returns this instance to use in method chaining
     * @param vehicleType, type of vehicle
     * @return Builder instance
     */
    public DieselElectricTrainBuilder vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    /**
     * Setter for fuel consumption rate of built vehicle returns instance for use in method chaining
     * @param consumptionRate, rate of consumption of fuel
     * @return Builder instance
     */
    public DieselElectricTrainBuilder consumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    /**
     * Setter for fuel consumption unit of measurement of built vehicle. returns instance for use in method chaining
     * @param consumptionUnit, unit of measurement for fuel
     * @return Builder instance
     */
    public DieselElectricTrainBuilder consumptionUnit(String consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
        return this;
    }

    /**
     * Setter for max passenger capacity of built vehicle. returns instance for use in method chaining
     * @param maxPassengers, max capacity of passengers
     * @return Builder instance
     */
    public DieselElectricTrainBuilder maxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    /**
     * Setter for current active route of built vehicle. returns instance for use in method chaining
     * @param activeRoute, current active route
     * @return Builder instance
     */
    public DieselElectricTrainBuilder activeRoute(String activeRoute) {
        this.activeRoute = activeRoute;
        return this;
    }

    /**
     * constructor for diesel electric train
     * @return constructed vehicle object
     */
    public DieselBus build() {
        return new DieselBus(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }
}