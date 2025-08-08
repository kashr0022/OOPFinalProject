package dataaccesslayer;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import transferobjects.reports.CostReportDTO;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;
import transferobjects.reports.CostReportDTO;

import java.util.List;

public class PTFMSDaoImpTest {
    private static PTFMSDaoImpl dao;

    @BeforeClass
    public static void setUpOnce() {
        dao = new PTFMSDaoImpl();
    }

    @Test
    public void checkUserExists(){
        UsersDTO user = new UsersDTO();
        user.setUsername("ExistingUser"); // made up username , does not exist in the db
        assertFalse(dao.checkUserTaken(user)); // this should be false

        user.setUsername("Bruce");
        assertTrue(dao.checkUserTaken(user)); // User  with name "Bruce" exists in the Db
    }


    @Test
    public void checkStaffExists(){
        StaffDTO staff = new StaffDTO();
        staff.setFirstName("StaffFirstName");
        staff.setLastName("StaffLastName");
        assertFalse(dao.checkStaffTaken(staff)); // staff do not exist , so this  statement is true
    }
    @Test
    public void testAddStaffUser() {


        // Prepare staff
        StaffDTO staff = new StaffDTO();
        staff.setFirstName("Tester101" );
        staff.setLastName( "TL" );
        staff.setEmail("t@ex.com");
        staff.setRole("Operator");


        // Prepare user
        UsersDTO user = new UsersDTO();
        user.setUsername("Tester102" );
        user.setPassword("p" );
        user.setRole("Operator");

        // Insert into DB
        dao.addStaffUser(staff, user);

        // Verify both exist
        assertTrue("Staff must exist", dao.checkStaffTaken(staff));
        assertTrue("User must exist",  dao.checkUserTaken(user));
    }
    @Test
    public void addUserStaffUserGetUserByUsername(){
        UsersDTO user = new UsersDTO();
        StaffDTO staff = new StaffDTO();

        // Prepare staff dto

        staff.setFirstName("TesterOperator1" );
        staff.setLastName( "TO" );
        staff.setEmail("operatortester@operator.com");
        staff.setRole("Operator");

        // prepare user dto

        user.setUsername("TesterOperator2");
        user.setPassword("p");
        user.setRole("Operator");
        dao.addStaffUser(staff, user);

        UsersDTO fetched = dao.getUserByUsername(user.getUsername());

        assertNotNull("Expected a non null UserDTO", fetched);//make sure that we dont get a null User

        assertEquals("Expected username to match", user.getUsername(), fetched.getUsername());
        assertEquals("Expected password to match", user.getPassword(), fetched.getPassword());



    }

    /**
     * Testing the reporting methods in DAOImlp
     * ensures that getCostReport() do not returns a null , empty list
     * and that each field has a valid , non null type
     */
    @Test
    public void getCostReport(){
        List<CostReportDTO> reports = dao.getCostReport();
        assertNotNull("Cost repair list should be non null",reports);
        assertFalse("Cost repair list should not be empty", reports.isEmpty());

        for ( CostReportDTO r : reports){
            assertNotNull("report type should not be null", r.getReportType());
            assertNotNull("report date should not be null", r.getDate());
            assertTrue("Report cost should be >= 0 but was " + r.getCost(), r.getCost() >= 0);
            assertNotNull("report description  should not be null", r.getDescription());
        }
    }







}
