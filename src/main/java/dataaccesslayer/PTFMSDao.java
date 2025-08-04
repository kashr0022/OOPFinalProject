package dataaccesslayer;

import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

public interface PTFMSDao {
    boolean checkCred(String userIn, String passIn);
    void addStaffUser(StaffDTO staff, UsersDTO user);
    boolean checkUserTaken(UsersDTO user);
    boolean checkStaffTaken(StaffDTO staff);
}
