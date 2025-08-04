package businesslayer;

import businesslayer.builder.vehicles.Vehicle;
import businesslayer.simplefactory.SimpleVehicleFactory;
import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;

public class PTFMSBusinessLogic {
    private static PTFMSDao ptfmsDao = null;

    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }
    public boolean checkCred(String userIn, String passIn) {
        return ptfmsDao.checkCred(userIn, passIn);
    }
    public boolean checkUserTaken(UsersDTO user) {
        return ptfmsDao.checkUserTaken(user);
    }
    public boolean checkStaffTaken(StaffDTO staff) {
        return ptfmsDao.checkStaffTaken(staff);
    }
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        ptfmsDao.addStaffUser(staff, user);
    }


    public void registerVehicle(VehicleDTO vehicleDTO) {

        Vehicle vehicle = SimpleVehicleFactory.createVehicle(
                vehicleDTO.getVehicleNumber(),
                vehicleDTO.getVehicleType(),
                vehicleDTO.getConsumptionRate(),
                vehicleDTO.getConsumptionUnit(),
                vehicleDTO.getMaxPassengers(),
                vehicleDTO.getActiveRoute()
        );

        ptfmsDao.registerVehicle(vehicle);
    }
}
