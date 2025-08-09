package businesslayer.simplefactory;

import businesslayer.builder.DieselBusBuilder;
import businesslayer.builder.DieselElectricTrainBuilder;
import businesslayer.builder.ElectricLightRailBuilder;
import businesslayer.builder.vehicles.Vehicle;

/**
 * Simple factory idiom used in combo with builder pattern to create different types of vehicle objects.
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class SimpleVehicleFactory {

    /**
     *     Creates vehicle object of specified type and passed in fields, utilizes builder method chaining internally.
     *     @param vehicleNumber, vehicle identifier (e.g. DB001)
     *     @param vehicleType, type of vehicle (e.g. diesel bus, diesel electric train, electric light rail)
     *     @param consumptionRate, rate that the vehicle consumes fuel
     *     @param consumptionUnit, fuel type vehicle uses
     *     @param maxPassengers, maximum amount of passengers that the vehicle can hold
     *     @param activeRoute, vehicle's current route
     * @return Vehicle objects with all the specified characteristics
     */
    public static Vehicle createVehicle(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        Vehicle vehicle = null;
//      if statement to determine what type of vehicle to create
        if (vehicleType.equals("DieselBus")) {

            vehicle = new DieselBusBuilder().vehicleNumber(vehicleNumber)
                    .vehicleType(vehicleType)
                    .consumptionRate(consumptionRate)
                    .consumptionUnit(consumptionUnit)
                    .maxPassengers(maxPassengers)
                    .activeRoute(activeRoute)
                    .build();
        } else if (vehicleType.equals("DieselElectricTrain")) {

            vehicle = new DieselElectricTrainBuilder().vehicleNumber(vehicleNumber).vehicleType(vehicleType) .consumptionRate(consumptionRate)
                    .consumptionUnit(consumptionUnit)
                    .maxPassengers(maxPassengers)
                    .activeRoute(activeRoute)
                    .build();
        } else if (vehicleType.equals("ElectricLightRail")) {

            vehicle = new ElectricLightRailBuilder().vehicleNumber(vehicleNumber).vehicleType(vehicleType) .consumptionRate(consumptionRate)
                    .consumptionUnit(consumptionUnit)
                    .maxPassengers(maxPassengers)
                    .activeRoute(activeRoute)
                    .build();
        }
        return vehicle;
    }
}
