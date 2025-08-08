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
 * Business logic for ptfms application. holds all calls to the data layer (dao). middle class between presentation and data
 * 
 * @author Lily S.
 * @author Khairunnisa Ashri
 * @version 1.0
 * @since JDK 21.0.4
 */
public class PTFMSBusinessLogic {

    private static PTFMSDao ptfmsDao = null;

    /**
     * Public constructor
     *
     * @author Lily S.
     */
    public PTFMSBusinessLogic() {
        this.ptfmsDao = new PTFMSDaoImpl();
    }

    /**
     * checks login credentials if valid or not
     * 
     * @author Lily S.
     * @param userIn, passed in username set in presentation layer servlet input
     * field
     * @param passIn, passed in password set in presentation layer servlet input
     * field
     * @return boolean, if credentials valid
     */
    public boolean checkCred(String userIn, String passIn) {
        if (userIn == null || userIn.isBlank()) {
            throw new ValidationException("Username cannot be empty ");
        }
        if (passIn == null || passIn.length() < 4) {
            throw new ValidationException("password cannot be empty or less than 4 characters");
        }

        return ptfmsDao.checkCred(userIn, passIn);
    }

    /**
     * checks if a username has been taken, used during account registration step
     *
     * @author Lily S.
     * @param user, UserDTO object
     * @return boolean, status if take or not
     */
    public boolean checkUserTaken(UsersDTO user) {
        return ptfmsDao.checkUserTaken(user);
    }

    /**
     * Check if a staff combo (first and last) is already taken in the db
     * 
     * @author Lily S.
     * @param staff, StaffDTO object
     * @return boolean, status if taken or not
     */
    public boolean checkStaffTaken(StaffDTO staff) {
        return ptfmsDao.checkStaffTaken(staff);
    }

    /**
     * Add a staff and user to database
     * 
     * @author Lily S.
     * @param staff, StaffDTO
     * @param user, UsersDTO
     */
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        // staff validation
        if (staff == null) {
            throw new ValidationException("Staff must not be null");
        }
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
        if (user == null) {
            throw new ValidationException("User must not be null");
        }
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
     * Adds a vehicle entry to the db
     *
     * @author Lily S.
     * @param vehicleDTO, VehicleDTO object
     */
    public void registerVehicle(VehicleDTO vehicleDTO) {

        //validation
        if (vehicleDTO.getConsumptionRate() < 0) {
            throw new ValidationException("Consumption rate cannot be less than 0");
        }
        if (vehicleDTO.getVehicleNumber() == null || vehicleDTO.getVehicleNumber().isBlank()) {
            throw new ValidationException("Vehicle number cannot be empty");
        }
        String type = vehicleDTO.getVehicleType();
        if (!List.of("DieselBus", "DieselElectricTrain", "ElectricLightRail").contains(type)) {
            throw new ValidationException("Vehicle type is not supported : " + type);
        }
        if (vehicleDTO.getMaxPassengers() <= 0) {
            throw new ValidationException("Max Passengers cannot be less than 0");
        }
        if (vehicleDTO.getConsumptionUnit() == null || vehicleDTO.getConsumptionUnit().isBlank()) {
            throw new ValidationException("Consumption unit cannot be empty");
        }
        if (vehicleDTO.getActiveRoute() == null || vehicleDTO.getActiveRoute().isBlank()) {
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
        validateVehicleType(vehicle.getVehicleType());
        ptfmsDao.registerVehicle(vehicle);
    }

    /**
     * Validates that the given vehicle type is an allowed type
     * 
     * @author Khairunnisa Ashri
     * @param vehicleType
     * @throws IllegalArgumentException 
     */
    public void validateVehicleType(String vehicleType) {
        List<String> allowedTypes = List.of("DieselBus", "DieselElectricTrain", "ElectricLightRail");
        if (!allowedTypes.contains(vehicleType)) {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }

    /**
     * Check if vehicle entry exists with the same identifier in db
     * 
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
     * Retrieves all fuel reports from the database
     *
     * @author Khairunnisa Ashri
     * @return List containing all fuel report DTOs
     */
    public List<FuelReportDTO> getFuelReport() {
        return ptfmsDao.getFuelReport();
    }

    /**
     * Utilizes observer pattern to create an alert counter system based on fuel stats
     * 
     * @author Khairunnisa Ashri
     * @author Lily S.
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
     * Retrieves all available cost reports from the database
     *
     * @author Khairunnisa Ashri
     * @return List consisting of CostReportDTOs
     */
    public List<CostReportDTO> getCostReport() {
        return ptfmsDao.getCostReport();
    }

    /**
     * Retrieves all available cost reports from the database
     *
     * @author Khairunnisa Ashri
     * @return List consisting of MaintenanceLogDTOs
     */
    public List<MaintenanceLogDTO> getAllLogs() {
        return ptfmsDao.getAllLogs();
    }

    /**
     * Retrieves all operator performance records from the database
     *
     * @author Khairunnisa Ashri
     * @return List consisting of Operator performance DTOs
     */
    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        return ptfmsDao.getOperatorPerformance();
    }

    /**
     * Grabs user in db via unique username
     *
     * @author Lily S.
     * @param userIn, username of desired user
     * @return Found user in a UsersDTO object
     */
    public UsersDTO getUserByUsername(String userIn) {
        if (userIn == null || userIn.isBlank()) {
            throw new ValidationException("Username cannot be empty");
        }
        return ptfmsDao.getUserByUsername(userIn);
    }

    /**
     * Gets all components from db
     *
     * @author Lily S.
     * @return List consisting of ComponentDTOs
     */
    public List<ComponentDTO> getAllComponents() {
        return ptfmsDao.getAllComponents();
    }

    /**
     * Utilizes observer pattern to create an alert counter system baseed on component hour usage
     * 
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
     *
     * @author Lily S.
     * @param id, id of desired component
     * @return ComponentDTO, specified component in DTO form to store all
     * characteristics
     */
    public ComponentDTO getComponentByID(int id) {
        return ptfmsDao.getComponentByID(id);
    }

    /**
     * Grabs vehicle in DB via specific ID passed in
     *
     * @author Lily S.
     * @param id, id of desired vehicle
     * @return VehicleDTO, specified vehicle in DTO form to store all
     * characteristics
     */
    public VehicleDTO getVehicleByID(int id) {
        return ptfmsDao.getVehicleByID(id);
    }

    /**
     * Get all staff from db
     *
     * @author Lily S.
     * @return List consisting of each staff entry as a StaffDTO
     */
    public List<StaffDTO> getAllStaff() {
        return ptfmsDao.getAllStaff();
    }

    /**
     * Get all gps from db
     *
     * @author Lily S.
     * @return List consisting of each gps entry as a GpsDTO
     */
    public List<GpsDTO> getAllGps() {
        return ptfmsDao.getAllGps();
    }

    /**
     * Add a maintenance entry to the database
     *
     * @author Lily S.
     * @param maintenance, MaintenanceLogDTO that holds all needed
     * characteristics for a db insert
     */
    public void addMaintenance(MaintenanceLogDTO maintenance) {
        if (maintenance.getComponentName() == null || maintenance.getComponentName().isBlank()) {
            throw new ValidationException("Component name cannot be empty");
        }
        if (maintenance.getNotes() == null || maintenance.getNotes().isBlank()) {
            throw new ValidationException("Notes of maintenance must be filled");
        }
        if (maintenance.getVehicleID() <= 0) {
            throw new ValidationException("Invalid Vehicle ID");
        }
        if (maintenance.getStaffID() < 0) {
            throw new ValidationException("Invalid Staff ID");
        }
        if (maintenance.getGpsID() < 0) {
            throw new ValidationException("Invalid GPS ID");
        }
        if (maintenance.getStatus() == null || maintenance.getStatus().isBlank()) {
            throw new ValidationException("Status of maintenance must be filled");
        }
        if (maintenance == null) {
            throw new ValidationException("Maintenance cannot be empty");
        }
        ptfmsDao.addMaintenance(maintenance);
    }

    /**
     * Retrieves all break logs associated with a specific staff member
     *
     * @author Khairunnisa Ashri
     * @param staffID, the unique identifier of the staff member
     * @return List of BreakLogDTO instances representing the staff member's
     * break log
     * 
     * @author Khairunnisa Ashri
     * @param staffID, the unique identifier of the staff member
     * @return List of BreakLogDTO instances representing the staff member's break log
     */
    public List<BreakLogDTO> getBreakLogsByStaffID(int staffID) {
        return ptfmsDao.getBreakLogsByStaffID(staffID);
    }

    /**
     * Retrieves a staff member's details by their unique staff ID
     *
     * @author Khairunnisa Ashri
     * @param staffID, the unique identifier of the staff member to retrieve
     * @return staffDTO containing the details of the specified staff member
     */
    public StaffDTO getStaffByStaffID(int staffID) {
        PTFMSDao dao = new PTFMSDaoImpl();
        return dao.getStaffByID(staffID);
    }

    /**
     * Retrieves a staff member's details based on their username
     *
     * @author Khairunnisa Ashri
     * @param username of the staff member to retrieve
     * @return a StaffDTO containing the staff member's details
     */
    public StaffDTO getStaffByUsername(String username) {
        return ptfmsDao.getStaffByUsername(username);
    }

    /**
     * Inserts a new break log record into the database
     *
     * @author Khairunnisa Ashri
     * @param log the BreakLogDTO object containing the details of the break log
     * to insert
     * 
     * @author Khairunnisa Ashri
     * @param log the BreakLogDTO object containing the details of the break log to insert
     */
    public void insertBreakLog(BreakLogDTO log) {
        ptfmsDao.insertBreakLog(log);
    }
    
    /**
     * Retrieves all components associated with a specific vehicle
     * 
     * @param vehicleId, the unique identifier of the vehicle whose components are to be retrieved
     * @return list of ComponentDTO objects representing the vehicle's components
     */
    public List<ComponentDTO> getComponentsByVehicleId(int vehicleId) {
        return ptfmsDao.getComponentsByVehicleId(vehicleId);
    }

    /**
     * Retrieves all components associated with a specific vehicle
     *
     * @param vehicleId, the unique identifier of the vehicle whose components
     * are to be retrieved
     * @return list of ComponentDTO objects representing the vehicle's
     * components
     */
    public List<ComponentDTO> getComponentsByVehicleId(int vehicleId) {
        return ptfmsDao.getComponentsByVehicleId(vehicleId);
    }

    public List<GpsDTO> getDetailedGps() {
        return ptfmsDao.getDetailedGps();
    }

    public void registerGps(GpsDTO g) {
        
        if (g.getStaffID() <=0){
            throw new ValidationException("Invalid Staff ID");
        }
        if (g.getVehicleID() <= 0) {
            throw new ValidationException("Invalid Vehicle ID");
        }
        if (g.getStartTime() == null) {
            throw new ValidationException("Start Time Must be Filled ");
        }
        if (g.getStartingLocation() == null || g.getStartingLocation().isBlank()) {
            throw new ValidationException("Start Location Must be Filled ");
        }
        if (g.getEndingLocation() == null || g.getEndingLocation().isBlank()) {
            throw new ValidationException("End Location Must be Filled ");
        }
        ptfmsDao.registerGps(g);
    }
}
