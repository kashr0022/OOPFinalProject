package VehicleBuilderTest;


import dataaccesslayer.PTFMSDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

import businesslayer.simplefactory.SimpleVehicleFactory;
import businesslayer.builder.vehicles.Vehicle;
import transferobjects.vehicles.VehicleDTO;


public class VehicleBuildTest {
    private static PTFMSDaoImpl dao;
    @Test
    public void testRegisterVehicleAndCheckTaken() {
        PTFMSDaoImpl dao = new PTFMSDaoImpl();


        // 1) Build a VehicleDTO
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleNumber("DB004");
        // must match one of your factory’s types: “Diesel”, “Electric”, or “Hybrid”
        dto.setVehicleType("DieselBus");
        dto.setConsumptionRate(12.34);
        dto.setConsumptionUnit("mpg");
        dto.setMaxPassengers(42);
        dto.setActiveRoute("Route" );

        // 2) turn it into a Vehicle
        Vehicle v = SimpleVehicleFactory.createVehicle(
                dto.getVehicleNumber(),
                dto.getVehicleType(),
                dto.getConsumptionRate(),
                dto.getConsumptionUnit(),
                dto.getMaxPassengers(),
                dto.getActiveRoute()
        );

        // 3) register it
        int newId = dao.registerVehicle(v);
        assertTrue("registerVehicle should return an id > 0", newId > 0);

        // 4) check it was really added
        assertTrue(
                "checkVehicleTaken must return true for the new vehicle",
                dao.checkVehicleTaken(v)
        );

        // 5) (optional) fetch by id and verify fields
        VehicleDTO fetched = dao.getVehicleByID(newId);
        assertEquals("DB004", fetched.getVehicleNumber());
        assertEquals(dto.getVehicleType(), fetched.getVehicleType());
        assertEquals(dto.getMaxPassengers(), fetched.getMaxPassengers());
        assertEquals(dto.getActiveRoute(), fetched.getActiveRoute());
    }
}
