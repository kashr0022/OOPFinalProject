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
        if (userIn == null || userIn.isBlank() ){
            throw new ValidationException("Username cannot be empty ");}
        if (passIn ==null || passIn.length() <4){
            throw new ValidationException("password cannot be empty or less than 4 characters");
        }

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
        // staff validation
        if (staff == null) { throw new ValidationException("Staff must not be null");}
        if (staff.getFirstName() == null || staff.getFirstName().isBlank()) {
            throw new ValidationException("Staff first name is required");
        }
        if (staff.getLastName() == null || staff.getLastName().isBlank()) {
            throw new ValidationException("Staff last name is required");
        }
        if (staff.getEmail() == null || staff.getEmail().isBlank()) {
            throw new ValidationException("Staff email is required");
        }
        if (!staff.getEmail().contains("@")) {
            throw new ValidationException("Staff email must contain '@'");
        }
        // user validation
        if (user == null) {throw new ValidationException("User must not be null");}
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ValidationException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new ValidationException("Password must be at least 4 characters");
        }
        if (user.getRole() == null || user.getRole().isBlank()) {
            throw new ValidationException("User role is required");
        }
        ptfmsDao.addStaffUser(staff, user);
    }

    /**
     * @author Lily S.
     * @param vehicleDTO
     */
    public int registerVehicle(VehicleDTO vehicleDTO) {

        //validation
        if (vehicleDTO.getConsumptionRate() < 0 ){
            throw new ValidationException("Consumption rate cannot be less than 0");
        }
        if (vehicleDTO.getVehicleNumber() == null || vehicleDTO.getVehicleNumber().isBlank()){
            throw new ValidationException("Vehicle number cannot be empty");
        }
        String type = vehicleDTO.getVehicleType();
        if (!List.of("DieselBus","DieselElectricTrain","ElectricalLightRail").contains(type)){
            throw new ValidationException("Vehicle type is not supported : " + type);
        }
        if(vehicleDTO.getMaxPassengers() <= 0){
            throw new ValidationException("Max Passengers cannot be less than 0");
        }
        if(vehicleDTO.getConsumptionUnit() ==null || vehicleDTO.getConsumptionUnit().isBlank()){
            throw new ValidationException("Consumption unit cannot be empty");
        }
        if (vehicleDTO.getActiveRoute() ==null || vehicleDTO.getActiveRoute().isBlank()){
            throw new ValidationException("Cannot Register a vehicle with blank active Route ");
        }

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
        if (userIn == null || userIn.isBlank() ){
            throw new ValidationException("Username cannot be empty");
        }
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
        if (maintenance.getComponentName()==null || maintenance.getComponentName().isBlank()){
            throw new ValidationException("Component name cannot be empty");
        }
        if(maintenance.getNotes()==null || maintenance.getNotes().isBlank()){
            throw new ValidationException("Notes of maintenance must be filled");
        }
        if(maintenance.getVehicleID()<=0 ){
            throw new ValidationException("Invalid Vehicle ID");
        }
        if(maintenance.getStaffID()<0 ){
            throw new ValidationException("Invalid Staff ID");
        }
        if(maintenance.getGpsID()<0 ){
            throw new ValidationException("Invalid GPS ID");
        }
        if(maintenance.getCost()<=0 ){
            throw new ValidationException("Invalid Cost");
        }
        if(maintenance.getStatus()==null || maintenance.getStatus().isBlank()){
            throw new ValidationException("Status of maintenance must be filled");
        }
        if(maintenance == null){
            throw new ValidationException("Maintenance cannot be empty");
        }
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
        if (g.getGpsID() <= 0) {
            throw new ValidationException("Invalid GPS ID");
        }
        if (g.getStaffID() <=0){
            throw new ValidationException("Invalid Staff ID");
        }
        if (g.getVehicleID() <=0){
            throw new ValidationException("Invalid Vehicle ID");
        }
        if (g.getStartTime() ==null ){
            throw new ValidationException("Start Time Must be Filled ");
        }
        if (g.getStartingLocation() ==null || g.getStartingLocation().isBlank()){
            throw new ValidationException("Start Location Must be Filled ");
        }
        if (g.getEndingLocation() ==null || g.getEndingLocation().isBlank()){
            throw new ValidationException("End Location Must be Filled ");
        }

        ptfmsDao.registerGps(g);
    }

    public List<ComponentDTO> getComponentsByVehicleId(int vehicleId) {
        return ptfmsDao.getComponentsByVehicleId(vehicleId);
    }
    
    

}
