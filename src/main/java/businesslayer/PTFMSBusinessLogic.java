package businesslayer;

import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;

public class PTFMSBusinessLogic {
    private static PTFMSDao ptfmsDao = null;

    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }
    public boolean checkCred(String userIn, String passIn) {
        return ptfmsDao.checkCred(userIn, passIn);
    }
}
