package businesslayer;

import businesslayer.builder.vehicles.Vehicle;
import businesslayer.observer.ConcreteFuelObserver;
import businesslayer.observer.ConcreteMaintenanceCountObserver;
import businesslayer.observer.CounterObserver;
import businesslayer.observer.FuelReportSubject;
import businesslayer.observer.Subject;
import businesslayer.simplefactory.SimpleVehicleFactory;
import dataaccesslayer.PTFMSDao;
import dataaccesslayer.PTFMSDaoImpl;
import transferobjects.reports.*;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lily S.
 *
 * @version 1.0
 * @since JDK 21.0.4
 */
public class PTFMSBusinessLogic {

    private static PTFMSDao ptfmsDao = null;

    /**
     * @author Lily S.
     */
    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }

    /**
     * @author Lily S.
     * @param userIn
     * @param passIn
     * @return
     */
    public boolean checkCred(String userIn, String passIn) {
        return ptfmsDao.checkCred(userIn, passIn);
    }

    /**
     * @author Lily S.
     * @param user
     * @return
     */
    public boolean checkUserTaken(UsersDTO user) {
        return ptfmsDao.checkUserTaken(user);
    }

    /**
     * @author Lily S.
     * @param staff
     * @return
     */
    public boolean checkStaffTaken(StaffDTO staff) {
        return ptfmsDao.checkStaffTaken(staff);
    }

    /**
     * @author Lily S.
     * @param staff
     * @param user
     */
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        ptfmsDao.addStaffUser(staff, user);
    }

    /**
     * @author Lily S.
     * @param vehicleDTO
     */
    public int registerVehicle(VehicleDTO vehicleDTO) {

        Vehicle vehicle = SimpleVehicleFactory.createVehicle(
                vehicleDTO.getVehicleNumber(),
                vehicleDTO.getVehicleType(),
                vehicleDTO.getConsumptionRate(),
                vehicleDTO.getConsumptionUnit(),
                vehicleDTO.getMaxPassengers(),
                vehicleDTO.getActiveRoute()
        );

        // get generated id
        return ptfmsDao.registerVehicle(vehicle);
        
    }

    /**
     * @author Lily S.
     * @param vehicleDTO
     * @return
     */
    public boolean checkVehicleTaken(VehicleDTO vehicleDTO) {
        Vehicle vehicle = SimpleVehicleFactory.createVehicle(
                vehicleDTO.getVehicleNumber(),
                vehicleDTO.getVehicleType(),
                vehicleDTO.getConsumptionRate(),
                vehicleDTO.getConsumptionUnit(),
                vehicleDTO.getMaxPassengers(),
                vehicleDTO.getActiveRoute()
        );
        return ptfmsDao.checkVehicleTaken(vehicle);
    }

    public List<FuelReportDTO> getFuelReport() {
        return ptfmsDao.getFuelReport();
    }

    public int getFuelAlertCount() {
        FuelReportSubject sub = new FuelReportSubject();
        ConcreteFuelObserver fuelAlert = new ConcreteFuelObserver();
        sub.addObserver(fuelAlert);

        List<FuelReportDTO> allFuelReports = ptfmsDao.getFuelReport();

        for (FuelReportDTO report : allFuelReports) {
            if (report.getFuelConsumed() > 100.0) {
                sub.notifyObservers(report);
            }
        }

        return fuelAlert.getAlertCount(); // Return the count
    }

    public List<CostReportDTO> getCostReport() {
        return ptfmsDao.getCostReport();
    }

    public List<MaintenanceLogDTO> getAllLogs() {
        return ptfmsDao.getAllLogs();
    }

    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        return ptfmsDao.getOperatorPerformance();
    }

    ;

    /**
     * @author Lily S.
     * @param userIn
     * @return
     */
    public UsersDTO getUserByUsername(String userIn) {
        return ptfmsDao.getUserByUsername(userIn);
    }

    /**
     * @author Lily S.
     * @return
     */
    public List<ComponentDTO> getAllComponents() {
        return ptfmsDao.getAllComponents();
    }

    public int getMaintenanceAlertCount() {
        Subject sub = new Subject();
        CounterObserver maintenanceCount = new ConcreteMaintenanceCountObserver();
        sub.addObserver(maintenanceCount);
        List<ComponentDTO> allComponents = new ArrayList<>();
        allComponents = ptfmsDao.getAllComponents();
        for (ComponentDTO component : allComponents) {
            if (component.getHoursUsed() > 800) {
                sub.notifyObserver();
            }
        }
        return maintenanceCount.getCounter();
    }

    /**
     * @author Lily S.
     * @param id
     * @return
     */
    public ComponentDTO getComponentByID(int id) {
        return ptfmsDao.getComponentByID(id);
    }

    /**
     * @author Lily S.
     * @param id
     * @return
     */
    public VehicleDTO getVehicleByID(int id) {
        return ptfmsDao.getVehicleByID(id);
    }

    /**
     * @author Lily S.
     * @return
     */
    public List<StaffDTO> getAllStaff() {
        return ptfmsDao.getAllStaff();
    }

    /**
     * @author Lily S.
     * @return
     */
    public List<GpsDTO> getAllGps() {
        return ptfmsDao.getAllGps();
    }

    /**
     * @author Lily S.
     * @param maintenance
     */
    public void addMaintenance(MaintenanceLogDTO maintenance) {
        ptfmsDao.addMaintenance(maintenance);
    }

    /**
     *
     * @param staffID
     * @return
     */
    public List<BreakLogDTO> getBreakLogsByStaffID(int staffID) {
        return ptfmsDao.getBreakLogsByStaffID(staffID);
    }

    /**
     *
     * @param staffID
     * @return
     */
    public StaffDTO getStaffByStaffID(int staffID) {
        PTFMSDao dao = new PTFMSDaoImpl();
        return dao.getStaffByID(staffID);
    }

    /**
     *
     * @param username
     * @return
     */
    public StaffDTO getStaffByUsername(String username) {
        return ptfmsDao.getStaffByUsername(username);
    }

    /**
     *
     * @param log
     */
    public void insertBreakLog(BreakLogDTO log) {
        ptfmsDao.insertBreakLog(log);
    }

    public List<GpsDTO> getDetailedGps() {
        return ptfmsDao.getDetailedGps();
    }

    public void registerGps(GpsDTO g) {

        ptfmsDao.registerGps(g);
    }

    public List<ComponentDTO> getComponentsByVehicleId(int vehicleId) {
        return ptfmsDao.getComponentsByVehicleId(vehicleId);
    }
    
    

}
