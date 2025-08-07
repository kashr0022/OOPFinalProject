package businesslayer.builder.vehicles;

/**
 * author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class DieselBus extends Vehicle {

    public DieselBus(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        super(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }

}
