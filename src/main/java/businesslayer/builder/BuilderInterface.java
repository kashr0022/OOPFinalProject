package businesslayer.builder;

/**
 * Interface for the vehicle creation builder pattern, allows to chain create characteristics for complex objects step by step
 * @author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public interface BuilderInterface {

    /**
     * Interface method setter for vehicleNumber (i.e. DB001), returns this instance to use in method chain
     * @param vehicleNumber, vehicle identifier number
     * @return Builder instance
     */
    BuilderInterface vehicleNumber(String vehicleNumber);

    /**
     * Interface method setter for vehicle type (i.e. diesel bus), returns this instance to use in method chaining
     * @param vehicleType, type of vehicle
     * @return Builder instance
     */
    BuilderInterface vehicleType(String vehicleType);

    /**
     * Interface method setter for fuel consumption rate of built vehicle returns instance for use in method chaining
     * @param consumptionRate, rate of consumption of fuel
     * @return Builder instance
     */
    BuilderInterface consumptionRate(double consumptionRate);

    /**
     * Interface method setter for fuel consumption unit of measurement of built vehicle. returns instance for use in method chaining
     * @param consumptionUnit, unit of measurement for fuel
     * @return Builder instance
     */
    BuilderInterface consumptionUnit(String consumptionUnit);

    /**
     * Interface method setter for max passenger capacity of built vehicle. returns instance for use in method chaining
     * @param maxPassengers, max capacity of passengers
     * @return Builder instance
     */
    BuilderInterface maxPassengers(int maxPassengers);

    /**
     * Interface method setter for current active route of built vehicle. returns instance for use in method chaining
     * @param activeRoute, current active route
     * @return Builder instance
     */
    BuilderInterface activeRoute(String activeRoute);

}