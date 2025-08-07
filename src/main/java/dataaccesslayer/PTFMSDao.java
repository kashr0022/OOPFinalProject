package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import transferobjects.reports.*;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;
import transferobjects.reports.BreakLogDTO;

import java.util.List;

/**
 * interface for DAOImpl, declares all the methods utilized in the web application, directly works with datasource to grab info (or add) from/to the PTFMS database
 * @author Lily S., Khairunnisa Ashri
 */
public interface PTFMSDao {

    /**
     *
     * @author Lily S.
     * @param userIn
     * @param passIn
     * @return
     */
    boolean checkCred(String userIn, String passIn);

    /**
     * @author Lily S.
     * @param staff
     * @param user
     */
    void addStaffUser(StaffDTO staff, UsersDTO user);

    /**
     *
     * @author Lily S.
     * @param maintenance
     */
    void addMaintenance(MaintenanceLogDTO maintenance);

    /**
     * @author Lily S.
     * @param userIn
     * @return
     */
    UsersDTO getUserByUsername(String userIn);

    /**
     * @author Lily S.
     * @param user
     * @return
     */
    boolean checkUserTaken(UsersDTO user);

    /**
     * @author Lily S.
     * @param staff
     * @return
     */
    boolean checkStaffTaken(StaffDTO staff);

    /**
     * @author Lily S.
     * @param vehicle
     */
    void registerVehicle(Vehicle vehicle);

    /**
     * @author Lily S.
     * @param vehicle
     * @return
     */
    boolean checkVehicleTaken(Vehicle vehicle);

    /**
     * @author Khairunnisa Ashri, Lily S.
     * @return
     */
    List<FuelReportDTO> getFuelReport();

    /**
     * @author Khairunnisa Ashri
     * @param report
     */
    void updateFuelReport(FuelReportDTO report);

    /**
     * @author Khairunnisa Ashri, Lily S.
     * @return
     */
    List<CostReportDTO> getCostReport();

    /**
     * @author Khairunnisa Ashri, Lily S.
     * @return
     */
    List<MaintenanceLogDTO> getAllLogs();

    /**
     * @author Khairunnisa Ashri, Lily S.
     * @return
     */
    List<OperatorPerformanceDTO> getOperatorPerformance();

    /**
     * @author Lily S.
     * @return
     */
    List<ComponentDTO> getAllComponents();

    /**
     * @author Lily S.
     * @return
     */
    List<StaffDTO> getAllStaff();

    /**
     * @author Lily S.
     * @return
     */
    List<GpsDTO> getAllGps();

    /**
     *
     * @author Lily S.
     * @param id
     * @return
     */
    ComponentDTO getComponentByID(int id);

    /**
     * @author Lily S.
     * @param id
     * @return
     */
    VehicleDTO getVehicleByID(int id);

    /**
     * @author Khairunnisa Ashri
     * @param StaffID
     * @return
     */
    List<BreakLogDTO> getBreakLogsByStaffID(int StaffID);

    /**
     * @author Khairunnisa Ashri
     * @param StaffID
     * @return
     */
    StaffDTO getStaffByID(int StaffID);

    /**
     * @author Khairunnisa Ashri
     * @param username
     * @return
     */
    StaffDTO getStaffByUsername(String username);

    /**
     * @author Khairunnisa Ashri
     * @param log
     */
    void insertBreakLog(BreakLogDTO log);
}
