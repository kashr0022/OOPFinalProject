package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import transferobjects.reports.*;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;
import transferobjects.reports.BreakLogDTO;
import transferobjects.reports.GpsDTO;

import java.util.List;

/**
 * Interface for DAOImpl, declares all the methods utilized in the web application, directly works with datasource to grab info (or add) from/to the PTFMS database
 * @author Lily S.
 * @author Khairunnisa Ashri
 */
public interface PTFMSDao {

    /**
     * Interface method that checks login credentials if valid or not
     * 
     * @author Lily S.
     * @param userIn, passed in username set
     * @param passIn, passed in password set
     * @return boolean, if credentials valid
     */
    boolean checkCred(String userIn, String passIn);

    /**
     * Interface method to add a staff and user to database
     * 
     * @author Lily S.
     * @param staff, StaffDTO
     * @param user, UsersDTO
     */
    void addStaffUser(StaffDTO staff, UsersDTO user);

    /**
     * Interface method to add a maintenance entry to the database
     * 
     * @author Lily S.
     * @param maintenance, MaintenanceLogDTO that holds all needed characteristics for a db insert
     */
    void addMaintenance(MaintenanceLogDTO maintenance);

    /**
     * Interface method that grabs user in db via unique username
     * 
     * @author Lily S.
     * @param userIn, username of desired user
     * @return Found user in a UsersDTO object
     */
    UsersDTO getUserByUsername(String userIn);

    /**
     * Interface method that checks if a username has been taken, used during account registration step
     * 
     * @author Lily S.
     * @param user, UserDTO object
     * @return boolean, status if take or not
     */
    boolean checkUserTaken(UsersDTO user);

    /**
     * Interface method to check if a staff combo (first and last) is already taken in the db
     * 
     * @author Lily S.
     * @param staff, StaffDTO object
     * @return boolean, status if taken or not
     */
    boolean checkStaffTaken(StaffDTO staff);

    /**
     * Interface method that adds a vehicle entry to the db
     * 
     * @author Lily S.
     * @param vehicle, Vehicle object
     */
     int registerVehicle(Vehicle vehicle);

    /**
     * Interface method to check if vehicle entry exists with the same identifier in db
     * 
     * @author Lily S.
     * @param vehicle, Vehicle object
     * @return boolean, value if taken or not
     */
    boolean checkVehicleTaken(Vehicle vehicle);

    /**
     * Interface method that grabs all fuel reports
     * 
     * @author Khairunnisa Ashri
     * @author Lily S.
     * @return List containing all fuel report DTOs
     */
    List<FuelReportDTO> getFuelReport();

    /**
     * Interface method to update fuel report
     * 
     * @author Khairunnisa Ashri
     * @param report, FuelReportDTO object to update
     */
    void updateFuelReport(FuelReportDTO report);

    /**
     * Interface method to get all cost reports
     * 
     * @author Khairunnisa Ashri
     * @return List consisting of CostReportDTOs
     */
    List<CostReportDTO> getCostReport();

    /**
     * Interface method to get all logs
     * 
     * @author Khairunnisa Ashri
     * @return List consisting of MaintenanceLogDTOs
     */
    List<MaintenanceLogDTO> getAllLogs();

    /**
     * Interface method to get all operator performances
     * 
     * @author Khairunnisa Ashri
     * @return List consisting of Operator performance DTOs
     */
    List<OperatorPerformanceDTO> getOperatorPerformance();

    /**
     * Interface method that gets all components from db
     * 
     * @author Lily S.
     * @return List consisting of ComponentDTOs
     */
    List<ComponentDTO> getAllComponents();

    /**
     * Interface method to get all staff from db
     * 
     * @author Lily S.
     * @return List consisting of each staff entry as a StaffDTO
     */
    List<StaffDTO> getAllStaff();

    /**
     * Interface method to get all gps from db
     * 
     * @author Lily S.
     * @return List consisting of each gps entry as a GpsDTO
     */
    List<GpsDTO> getAllGps();

    /**
     * Interface method that grabs component in DB via specific ID passed in
     * 
     * @author Lily S.
     * @param id, id of desired component
     * @return ComponentDTO, specified component in DTO form to store all characteristics
     */
    ComponentDTO getComponentByID(int id);

    /**
     * Interface method that grabs vehicle in DB via specific ID passed in
     * 
     * @author Lily S.
     * @param id, id of desired vehicle
     * @return VehicleDTO, specified vehicle in DTO form to store all characteristics
     */
    VehicleDTO getVehicleByID(int id);

    /**
     * Interface method to get break logs by staff ID
     * 
     * @author Khairunnisa Ashri
     * @param StaffID, staff ID to filter break logs
     * @return List of BreakLogDTOs for the specified staff member
     */
    List<BreakLogDTO> getBreakLogsByStaffID(int StaffID);

    /**
     * Interface method to get staff by staff ID
     * 
     * @author Khairunnisa Ashri
     * @param StaffID, staff ID to retrieve
     * @return StaffDTO object for the specified staff ID
     */
    StaffDTO getStaffByID(int StaffID);

    /**
     * Interface method to get staff by username
     * 
     * @author Khairunnisa Ashri
     * @param username, username to retrieve staff information
     * @return StaffDTO object for the specified username
     */
    StaffDTO getStaffByUsername(String username);

    /**
     * Interface method to insert break log entry
     * 
     * @author Khairunnisa Ashri
     * @param log, BreakLogDTO containing break log information to insert
     */
    void insertBreakLog(BreakLogDTO log);
    
    /**
     *
     * @param vehicleId
     * @return
     */
    List<ComponentDTO> getComponentsByVehicleId(int vehicleId);
    
    List<GpsDTO> getDetailedGps();
    
    void registerGps(GpsDTO g);
}

    