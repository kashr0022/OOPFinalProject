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
 * business logic for ptfms application. holds all calls to the data layer (dao). middle class between presentation and data
 * @author: Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class PTFMSBusinessLogic {

    private static PTFMSDao ptfmsDao = null;

    /**
     * Public constructor
     * @author Lily S.
     */
    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }

    /**
     * checks login credentials if valid or not
     * @author Lily S.
     * @param userIn, passed in username set in presentation layer servlet input field
     * @param passIn, passed in password set in presentation layer servlet input field
     * @return boolean, if credentials valid
     */
    public boolean checkCred(String userIn, String passIn) {
        return ptfmsDao.checkCred(userIn, passIn);
    }

    /**
     * checks if a username has been taken, used during account registration step
     * @author Lily S.
     * @param user, UserDTO object
     * @return boolean, status if take or not
     */
    public boolean checkUserTaken(UsersDTO user) {
        return ptfmsDao.checkUserTaken(user);
    }

    /**
     * Check if a staff combo (first and last) is already taken in the db
     * @author Lily S.
     * @param staff, StaffDTO object
     * @return boolean, status if taken or not
     */
    public boolean checkStaffTaken(StaffDTO staff) {
        return ptfmsDao.checkStaffTaken(staff);
    }

    /**
     * Add a staff and user to database
     * @author Lily S.
     * @param staff, StaffDTO
     * @param user, UsersDTO
     */
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        ptfmsDao.addStaffUser(staff, user);
    }

    /**
     * Adds a vehicle entry to the db
     * @author Lily S.
     * @param vehicleDTO, VehicleDTO object
     */
    public void registerVehicle(VehicleDTO vehicleDTO) {

//      call simple factory idiom with builder
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

    /**
     * Check if vehicle entry exists with the same identifier in db
     * @author Lily S.
     * @param vehicleDTO, VehicleDTO object
     * @return boolean, value if taken or not
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

    /**
     * grabs all fuel reports
     * @author Lily S., Khairunnisa Ashri
     * @return List containing all fuel report DTOs
     */
    public List<FuelReportDTO> getFuelReport() {
        return ptfmsDao.getFuelReport();
    }

    /**
     * Utilizes observer pattern to create an alert counter system baseed on fuel stats
     * @author Lily S., Khairunnisa Ashri
     * @return int of alert counter
     */
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

    /**
     * get all cost reports
     * @author Lily S., Khairunnisa Ashri
     * @return List consisting of CostReportDTOs
     */
    public List<CostReportDTO> getCostReport() {
        return ptfmsDao.getCostReport();
    }

    /**
     * get all logs
     * @author lily S., Khairunnisa Ashri
     * @return List consisting of MaintenanceLogDTOs
     */
    public List<MaintenanceLogDTO> getAllLogs() {
        return ptfmsDao.getAllLogs();
    }

    /**
     * get all operator performances
     * @author Lily S., Khairunnisa Ashri
     * @return List consisting of Operator performance DTOs
     */
    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        return ptfmsDao.getOperatorPerformance();
    }

    /**
     * Grabs user in db via unique username
     * @author Lily S.
     * @param userIn, username of desired user
     * @return Found user in a UsersDTO object
     */
    public UsersDTO getUserByUsername(String userIn) {
        return ptfmsDao.getUserByUsername(userIn);
    }

    /**
     * Gets all components from db
     * @author Lily S.
     * @return List consisting of ComponentDTOs
     */
    public List<ComponentDTO> getAllComponents() {
        return ptfmsDao.getAllComponents();
    }

    /**
     * Utilizes observer pattern to create an alert counter system baseed on component hour usage
     * @author Lily S.
     * @return int, counter value from observer pattern
     */
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
     * Grabs component in DB via specific ID passed in
     * @author Lily S.
     * @param id, id of desired component
     * @return ComponentDTO, specified component in DTO form to store all characteristics
     */
    public ComponentDTO getComponentByID(int id) {
        return ptfmsDao.getComponentByID(id);
    }

    /**
     * Grabs vehicle in DB via specific ID passed in
     * @author Lily S.
     * @param id, id of desired vehicle
     * @return VehicleDTO, specified vehicle in DTO form to store all characteristics
     */
    public VehicleDTO getVehicleByID(int id) {
        return ptfmsDao.getVehicleByID(id);
    }

    /**
     * Get all staff from db
     * @author Lily S.
     * @return List consisting of each staff entry as a StaffDTO
     */
    public List<StaffDTO> getAllStaff() {
        return ptfmsDao.getAllStaff();
    }

    /**
     * Get all gps from db
     * @author Lily S.
     * @return List consisting of each gps entry as a GpsDTO
     */
    public List<GpsDTO> getAllGps() {
        return ptfmsDao.getAllGps();
    }

    /**
     * Add a maintenance entry to the database
     * @author Lily S.
     * @param maintenance, MaintenanceLogDTO that holds all needed characteristics for a db insert
     */
    public void addMaintenance(MaintenanceLogDTO maintenance) {
        ptfmsDao.addMaintenance(maintenance);
    }

    /**
     *
     * @author Khairunnisa Ashri
     * @param staffID
     * @return
     */
    public List<BreakLogDTO> getBreakLogsByStaffID(int staffID) {
        return ptfmsDao.getBreakLogsByStaffID(staffID);
    }

    /**
     *
     * @author Khairunnisa Ashri
     * @param staffID
     * @return
     */
    public StaffDTO getStaffByStaffID(int staffID) {
        PTFMSDao dao = new PTFMSDaoImpl();
        return dao.getStaffByID(staffID);
    }

    /**
     *
     * @author Khairunnisa Ashri
     * @param username
     * @return
     */
    public StaffDTO getStaffByUsername(String username) {
        return ptfmsDao.getStaffByUsername(username);
    }

    /**
     *
     * @author Khairunnisa Ashri
     * @param log
     */
    public void insertBreakLog(BreakLogDTO log) {
        ptfmsDao.insertBreakLog(log);
    }

}
