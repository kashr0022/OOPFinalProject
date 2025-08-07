package businesslayer.simplefactory;

import businesslayer.builder.DieselBusBuilder;
import businesslayer.builder.DieselElectricTrainBuilder;
import businesslayer.builder.ElectricLightRailBuilder;
import businesslayer.builder.vehicles.Vehicle;

public class SimpleVehicleFactory {
    public static Vehicle createVehicle(String vehicleNumber, String vehicleType, double consumptionRate, String consumptionUnit, int maxPassengers, String activeRoute) {
        Vehicle vehicle = null;
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
