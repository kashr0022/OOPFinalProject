package businesslayer.builder;

public interface BuilderInterface {

    BuilderInterface vehicleNumber(String vehicleNumber);

    BuilderInterface vehicleType(String vehicleType);

    BuilderInterface consumptionRate(double consumptionRate);

    BuilderInterface consumptionUnit(String consumptionUnit);

    BuilderInterface maxPassengers(int maxPassengers);

    BuilderInterface activeRoute(String activeRoute);

}