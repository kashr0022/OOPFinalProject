package businesslayer;

import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

public class PTFMSBusinessLogic {
    private static PTFMSDao ptfmsDao = null;

    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }
    public boolean checkCred(String userIn, String passIn) {
        return ptfmsDao.checkCred(userIn, passIn);
    }
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        ptfmsDao.addStaffUser(staff, user);
    }

}
