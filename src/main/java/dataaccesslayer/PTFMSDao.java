package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.MaintenanceLogDTO;
import transferobjects.reports.OperatorPerformanceDTO;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

import java.util.List;

public interface PTFMSDao {
    boolean checkCred(String userIn, String passIn);
    void addStaffUser(StaffDTO staff, UsersDTO user);
    boolean checkUserTaken(UsersDTO user);
    boolean checkStaffTaken(StaffDTO staff);
    void registerVehicle(Vehicle vehicle);
    List<FuelReportDTO> getFuelReport();
    List<CostReportDTO> getCostReport();
    List<MaintenanceLogDTO> getAllLogs();
    List<OperatorPerformanceDTO> getOperatorPerformance();
}
