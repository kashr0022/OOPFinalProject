package businesslayer.builder.vehicles;

/**
 * Electric light rail class useed to construct electric light rail vehicles via the simple factory + builder combo
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class ElectricLightRail extends Vehicle {

    /**
     * Electric Light Rail object creator
     * @param vehicleNumber, vehicle identifier (e.g. DB001)
     * @param vehicleType, type of vehicle (e.g. diesel bus, diesel electric train, electric light rail)
     * @param consumptionRate, rate that the vehicle consumes fuel
     * @param consumptionUnit, fuel type vehicle uses
     * @param maxPassengers, maximum amount of passengers that the vehicle can hold
     * @param activeRoute, vehicle's current route
     */
    public ElectricLightRail(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        super(vehicleNumber, vehicleType, consumptionRate, consumptionUnit, maxPassengers, activeRoute);
    }

}
