package VehicleBuilderTest;

import dataaccesslayer.PTFMSDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

import businesslayer.simplefactory.SimpleVehicleFactory;
import businesslayer.builder.vehicles.Vehicle;
import transferobjects.vehicles.VehicleDTO;

/**
 * Unit test for verifying that a vehicle can be built using the
 * SimpleVehicleFactory, registered in the database using
 *  PTFMSDaoImpl, and then retrieved correctly.
 * This test covers:
 *     Building a  VehicleDTO with valid attributes
 *     Creating a  Vehicle instance from the DTO using the Simple Factory
 *     Registering the vehicle in the database
 *     Checking if the registered vehicle is marked as taken
 *     Fetching the vehicle by ID and verifying that stored data matches input
 * @author oussema
 */
public class VehicleBuildTest {

    /** DAO instance used to interact with the database. */
    private static PTFMSDaoImpl dao;

    /**
     * Tests the full flow of registering a vehicle:
     */
    @Test
    public void testRegisterVehicleAndCheckTaken() {
        PTFMSDaoImpl dao = new PTFMSDaoImpl();

        // 1) Build a VehicleDTO
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleNumber("DB004");
        // Must match one of the factory’s supported types: "Diesel", "Electric", or "Hybrid"
        dto.setVehicleType("DieselBus");
        dto.setConsumptionRate(12.34);
        dto.setConsumptionUnit("mpg");
        dto.setMaxPassengers(42);
        dto.setActiveRoute("Route");

        // 2) Create a Vehicle from the DTO
        Vehicle v = SimpleVehicleFactory.createVehicle(
                dto.getVehicleNumber(),
                dto.getVehicleType(),
                dto.getConsumptionRate(),
                dto.getConsumptionUnit(),
                dto.getMaxPassengers(),
                dto.getActiveRoute()
        );

        // 3) Register it in the database
        int newId = dao.registerVehicle(v);
        assertTrue("registerVehicle should return an id > 0", newId > 0);

        // 4) Check it was really added
        assertTrue(
                "checkVehicleTaken must return true for the new vehicle",
                dao.checkVehicleTaken(v)
        );

        // 5) Fetch by ID and verify fields
        VehicleDTO fetched = dao.getVehicleByID(newId);
        assertEquals("DB004", fetched.getVehicleNumber());
        assertEquals(dto.getVehicleType(), fetched.getVehicleType());
        assertEquals(dto.getMaxPassengers(), fetched.getMaxPassengers());
        assertEquals(dto.getActiveRoute(), fetched.getActiveRoute());
    }
}
