package businesslayer.builder;

/**
 * author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public interface BuilderInterface {

    BuilderInterface vehicleNumber(String vehicleNumber);

    BuilderInterface vehicleType(String vehicleType);

    BuilderInterface consumptionRate(double consumptionRate);

    BuilderInterface consumptionUnit(String consumptionUnit);

    BuilderInterface maxPassengers(int maxPassengers);

    BuilderInterface activeRoute(String activeRoute);

}