package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import model.Staff;
import transferobjects.reports.*;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;
import transferobjects.reports.BreakLogDTO;

import java.util.List;

/**
 * interface for DAOImpl, declares all the methods utilized in the web application, directly works with datasource to grab info (or add) from/to the PTFMS database
 * author: Lily S., Khairunnisa Ashri
 */
public interface PTFMSDao {

    boolean checkCred(String userIn, String passIn);
    void addStaffUser(StaffDTO staff, UsersDTO user);
    void addMaintenance(MaintenanceLogDTO maintenance);
    UsersDTO getUserByUsername(String userIn);
    boolean checkUserTaken(UsersDTO user);
    boolean checkStaffTaken(StaffDTO staff);
    void registerVehicle(Vehicle vehicle);
    boolean checkVehicleTaken(Vehicle vehicle);
    List<FuelReportDTO> getFuelReport();
    void updateFuelReport(FuelReportDTO report);
    List<CostReportDTO> getCostReport();
    List<MaintenanceLogDTO> getAllLogs();
    List<OperatorPerformanceDTO> getOperatorPerformance();
    List<ComponentDTO> getAllComponents();
    List<StaffDTO> getAllStaff();
    List<GpsDTO> getAllGps();
    ComponentDTO getComponentByID(int id);
    VehicleDTO getVehicleByID(int id);
    List<BreakLogDTO> getBreakLogsByStaffID(int StaffID);
    StaffDTO getStaffByID(int StaffID);
    StaffDTO getStaffByUsername(String username);
    void insertBreakLog(BreakLogDTO log);
}
