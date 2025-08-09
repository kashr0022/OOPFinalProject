package dataaccesslayer;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import transferobjects.reports.CostReportDTO;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

import java.util.List;

/**
 * Unit tests for PTFMSDaoImpl, verifying DAO operations
 * related to user, staff, and reporting functionality.
 * @author oussema
 */
public class PTFMSDaoImpTest {

    /** Shared DAO instance for all tests. */
    private static PTFMSDaoImpl dao;

    /**
     * Initializes the DAO implementation once before running all tests.
     */
    @BeforeClass
    public static void setUpOnce() {
        dao = new PTFMSDaoImpl();
    }

    /**
     * Verifies that  PTFMSDaoImpl#checkUserTaken(UsersDTO)
     * correctly identifies when a username exists or does not exist in the database.
     */
    @Test
    public void checkUserExists() {
        UsersDTO user = new UsersDTO();

        // Non-existing username
        user.setUsername("ExistingUser"); // Made-up username
        assertFalse("Non-existing username should return false", dao.checkUserTaken(user));

        // Existing username
        user.setUsername("Bruce"); // Known username in DB
        assertTrue("Existing username should return true", dao.checkUserTaken(user));
    }

    /**
     * Verifies that  PTFMSDaoImpl#checkStaffTaken(StaffDTO)
     * correctly identifies when staff does not exist in the database.
     */
    @Test
    public void checkStaffExists() {
        StaffDTO staff = new StaffDTO();
        staff.setFirstName("StaffFirstName");
        staff.setLastName("StaffLastName");

        assertFalse("Non-existing staff should return false", dao.checkStaffTaken(staff));
    }

    /**
     * Verifies that adding a staff member and corresponding user
     * via  PTFMSDaoImpl#addStaffUser(StaffDTO, UsersDTO) persists both records.
     */
    @Test
    public void testAddStaffUser() {
        // Prepare staff
        StaffDTO staff = new StaffDTO();
        staff.setFirstName("Tester101");
        staff.setLastName("TL");
        staff.setEmail("t@ex.com");
        staff.setRole("Operator");

        // Prepare user
        UsersDTO user = new UsersDTO();
        user.setUsername("Tester102");
        user.setPassword("p");
        user.setRole("Operator");

        // Insert into DB
        dao.addStaffUser(staff, user);

        // Verify both exist
        assertTrue("Staff must exist", dao.checkStaffTaken(staff));
        assertTrue("User must exist", dao.checkUserTaken(user));
    }

    /**
     * Verifies that a user added via  PTFMSDaoImpl#addStaffUser(StaffDTO, UsersDTO)
     * can be retrieved correctly using  PTFMSDaoImpl#getUserByUsername(String).
     */
    @Test
    public void addUserStaffUserGetUserByUsername() {
        UsersDTO user = new UsersDTO();
        StaffDTO staff = new StaffDTO();

        // Prepare staff
        staff.setFirstName("TesterOperator1");
        staff.setLastName("TO");
        staff.setEmail("operatortester@operator.com");
        staff.setRole("Operator");

        // Prepare user
        user.setUsername("TesterOperator2");
        user.setPassword("p");
        user.setRole("Operator");

        // Insert into DB
        dao.addStaffUser(staff, user);

        // Retrieve and verify
        UsersDTO fetched = dao.getUserByUsername(user.getUsername());
        assertNotNull("Expected a non-null UserDTO", fetched);
        assertEquals("Expected username to match", user.getUsername(), fetched.getUsername());
        assertEquals("Expected password to match", user.getPassword(), fetched.getPassword());
    }

    /**
     * Verifies that  PTFMSDaoImpl getCostReport() returns a non-null,
     * non-empty list and that each {CostReportDTO} contains valid values.
     */
    @Test
    public void getCostReport() {
        List<CostReportDTO> reports = dao.getCostReport();

        assertNotNull("Cost report list should be non-null", reports);
        assertFalse("Cost report list should not be empty", reports.isEmpty());

        for (CostReportDTO r : reports) {
            assertNotNull("Report type should not be null", r.getReportType());
            assertNotNull("Report date should not be null", r.getDate());
            assertTrue("Report cost should be > 0", r.getCost() > 0);
            assertNotNull("Report description should not be null", r.getDescription());
        }
    }
}
