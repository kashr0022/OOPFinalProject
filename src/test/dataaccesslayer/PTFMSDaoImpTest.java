package dataaccesslayer;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;

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




}
