package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import transferobjects.reports.CostReportDTO;
import transferobjects.reports.FuelReportDTO;
import transferobjects.reports.MaintenanceLogDTO;
import transferobjects.reports.OperatorPerformanceDTO;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PTFMSDaoImpl implements PTFMSDao {

    @Override
    public boolean checkCred(String userIn, String passIn) {
        Connection connection = null;
        String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {
            prepState.setString(1, userIn);
            prepState.setString(2, passIn);

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        Connection connection = DataSource.getConnection();

        try {
            String staffQuery = "INSERT INTO Staff (FirstName, LastName, Email, Role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement staffStmt = connection.prepareStatement(staffQuery)) {
                staffStmt.setString(1, staff.getFirstName());
                staffStmt.setString(2, staff.getLastName());
                staffStmt.setString(3, staff.getEmail());
                staffStmt.setString(4, staff.getRole());
                staffStmt.executeUpdate();
            }

            String userQuery = "INSERT INTO Users (Username, Password, StaffID) VALUES (?, ?, LAST_INSERT_ID())";
            try (PreparedStatement userStmt = connection.prepareStatement(userQuery)) {
                userStmt.setString(1, user.getUsername());
                userStmt.setString(2, user.getPassword());
                userStmt.executeUpdate();
            }

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public boolean checkUserTaken(UsersDTO user) {
        Connection connection = null;
        String query = "SELECT * FROM Users WHERE Username = ?";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, user.getUsername());

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;
    }

    @Override
    public boolean checkStaffTaken(StaffDTO staff) {
        Connection connection = null;
        String query = "SELECT * FROM Staff WHERE FirstName = ? AND LastName = ?";

        connection = DataSource.getConnection();
        try (
             PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, staff.getFirstName());
            prepState.setString(2, staff.getLastName());

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE,
                    "Exception caught related to staff exist check functionality. (" + staff.getFirstName() + " " + staff.getLastName() + ").", ex);
            return false;
        }
        return false;
    }

    @Override
    public void registerVehicle(Vehicle vehicle) {
        Connection connection = null;
        String query = "INSERT INTO Vehicles (VehicleNumber, VehicleType, ConsumptionRate, ConsumptionUnit, MaxPassengers, ActiveRoute)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        connection = DataSource.getConnection();
        try (PreparedStatement userStmt = connection.prepareStatement(query)) {
            userStmt.setString(1, vehicle.getVehicleNumber());
            userStmt.setString(2, vehicle.getVehicleType());
            userStmt.setDouble(3, vehicle.getConsumptionRate());
            userStmt.setString(4, vehicle.getConsumptionUnit());
            userStmt.setDouble(5, vehicle.getMaxPassengers());
            userStmt.setString(6, vehicle.getActiveRoute());
            userStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when adding new vehicle to db.", e);
        }
    }

    public List<FuelReportDTO> getFuelReport() {
        List<FuelReportDTO> reports = new ArrayList<>();
        String sql = """
                     SELECT
                         fr.ReportID,
                         fr.VehicleID,
                         v.VehicleType,
                         fr.StaffID,
                         s.FirstName,
                         s.LastName,
                         fr.FuelType,
                         fr.UsageAmt,
                         CASE
                             WHEN v.VehicleType = 'ElectricLightRail' THEN 'kWh/hr'
                             WHEN v.VehicleType = 'DieselBus' THEN 'mpg'
                             WHEN v.VehicleType = 'DieselElectricTrain' THEN 'mpg'
                             ELSE 'units'
                         END AS Unit,
                         fr.DistanceTraveled,
                         fr.Date,
                         fr.Status
                     FROM FuelReport fr
                     JOIN Vehicles v ON fr.VehicleID = v.VehicleID
                     JOIN Staff s ON fr.StaffID = s.StaffID;
                     """;

        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FuelReportDTO report = new FuelReportDTO();
                report.setReportID(rs.getInt("ReportID"));
                report.setStaffID(rs.getInt("StaffID"));
                report.setVehicleID(rs.getInt("VehicleID"));
                report.setFuelConsumed(rs.getDouble("UsageAmt"));
                report.setDistanceTraveled(rs.getDouble("DistanceTraveled"));
                report.setDate(rs.getTimestamp("Date").toLocalDateTime());
                report.setStatus(rs.getString("Status"));
                report.setVehicleType(rs.getString("VehicleType"));
                report.setFuelType(rs.getString("FuelType"));
                report.setFuelEfficiencyUnit(rs.getString("Unit"));

                // calculate fuel efficiency
                double fuelConsumed = report.getFuelConsumed();
                double distance = report.getDistanceTraveled();
                if (fuelConsumed > 0) {
                    report.setFuelEfficiency(distance / fuelConsumed);
                } else {
                    report.setFuelEfficiency(0);
                }

                reports.add(report);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting fuel report.", e);
        }
        return reports;
    }

    public List<CostReportDTO> getCostReport() {
        List<CostReportDTO> reportList = new ArrayList<>();
        // fuel cost query
        String fuel = """
            SELECT fr.VehicleID, v.VehicleType, 'Fuel' AS ReportType,
                   CONCAT(fr.UsageAmt, ' ', fr.FuelType, ' @ $', f.RatePerUnit) AS Description,
                   (fr.UsageAmt * f.RatePerUnit) AS Cost,
                   fr.Date
            FROM FuelReport fr
            JOIN Vehicles v ON fr.VehicleID = v.VehicleID
            JOIN FuelRates f ON fr.FuelType = f.FuelType;
        """;

        // maintenance cost
        String maintenance = """
            SELECT ml.VehicleID, v.VehicleType, 'Maintenance' AS ReportType,
                   CONCAT(c.ComponentName, ' (', ml.UsageAmt, ' hrs @ $', c.RatePerHour, ')') AS Description,
                   (ml.UsageAmt * c.RatePerHour) AS Cost,
                   ml.Date
            FROM MaintenanceLog ml
            JOIN Vehicles v ON ml.VehicleID = v.VehicleID
            JOIN Components c ON ml.ComponentID = c.ComponentID
        """;

        // fuel
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(fuel);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CostReportDTO fcost = new CostReportDTO();
                fcost.setVehicleID(rs.getInt("VehicleID"));
                fcost.setVehicleType(rs.getString("VehicleType"));
                fcost.setReportType(rs.getString("ReportType"));
                fcost.setDescription(rs.getString("Description"));
                fcost.setCost(rs.getDouble("Cost"));
                fcost.setDate(rs.getTimestamp("Date").toLocalDateTime());
                reportList.add(fcost);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting cost report.", e);
        }

        // maintenance
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(maintenance);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CostReportDTO mcost = new CostReportDTO();
                mcost.setVehicleID(rs.getInt("VehicleID"));
                mcost.setVehicleType(rs.getString("VehicleType"));
                mcost.setReportType(rs.getString("ReportType"));
                mcost.setDescription(rs.getString("Description"));
                mcost.setCost(rs.getDouble("Cost"));
                mcost.setDate(rs.getTimestamp("Date").toLocalDateTime());
                reportList.add(mcost);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting cost report.", e);
        }

        // sort by date ascending
        reportList.sort((b, a) -> b.getDate().compareTo(a.getDate()));

        return reportList;
    }

    public List<MaintenanceLogDTO> getAllLogs(){
        List<MaintenanceLogDTO> logs = new ArrayList<>();

        String sql = """
                     SELECT
                         ml.LogID,
                         ml.VehicleID,
                         v.VehicleType AS Type,
                         c.ComponentID AS ComponentID,
                         c.ComponentName AS ComponentName,
                         ml.UsageAmt AS UsageHours,
                         CASE
                             WHEN ml.Status = 'Completed' THEN 'All systems normal'
                             WHEN ml.Status = 'In Progress' THEN 'Under maintenance'
                             WHEN ml.Status = 'Pending' THEN 'Scheduled'
                             ELSE 'Status unknown'
                         END AS Diagnostics,
                         ml.Date AS Timestamp,
                         CASE
                             WHEN ml.Status = 'Pending' THEN 'Schedule'
                             ELSE ml.Status
                         END AS Action,
                         (ml.UsageAmt * c.HoursUsed) AS Cost
                     FROM
                         MaintenanceLog ml
                     JOIN Vehicles v ON ml.VehicleID = v.VehicleID
                     JOIN Components c ON ml.ComponentID = c.ComponentID
                     ORDER BY ml.Date DESC;
                     """;

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MaintenanceLogDTO log = new MaintenanceLogDTO();
                log.setLogID(rs.getInt("LogID"));
                log.setVehicleID(rs.getInt("VehicleID"));
                log.setVehicleType(rs.getString("Type"));
                log.setComponentID(rs.getInt("ComponentID"));
                log.setComponentName(rs.getString("ComponentName"));
                log.setUsageAmt(rs.getDouble("UsageHours"));
                log.setDiagnostics(rs.getString("Diagnostics"));
                log.setStatus(rs.getString("Action"));
                log.setDate(rs.getTimestamp("Timestamp").toLocalDateTime());
                log.setCost(rs.getDouble("Cost"));
                logs.add(log);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting all logs.", e);
        }

        return logs;
    }

    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        List<OperatorPerformanceDTO> results = new ArrayList<>();
        String sql = """
                     SELECT 
                         s.StaffID,
                         s.FirstName,
                         s.LastName,
                         -- calculate on-time rate as a percentage (0 to 100)
                         IFNULL(
                           100.0 * SUM(CASE WHEN gps.EndTime <= gps.ScheduledEndTime THEN 1 ELSE 0 END) / COUNT(gps.GPSID), 
                           0
                         ) AS OnTimeRate,
                         IFNULL(AVG(TIMESTAMPDIFF(MINUTE, gps.StartTime, gps.EndTime)), 0) AS AvgRouteDuration,
                         IFNULL(SUM(TIMESTAMPDIFF(HOUR, sl.StartTime, sl.EndTime)), 0) AS TotalHoursWorked
                     
                     FROM Staff s
                     LEFT JOIN GPS gps ON s.StaffID = gps.StaffID
                     LEFT JOIN StaffLog sl ON s.StaffID = sl.StaffID
                     WHERE s.Role = 'Operator'
                     GROUP BY s.StaffID, s.FirstName, s.LastName;
                     """;

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OperatorPerformanceDTO op = new OperatorPerformanceDTO();
                op.setStaffID(rs.getInt("StaffID"));
                op.setFirstName(rs.getString("FirstName"));
                op.setLastName(rs.getString("LastName"));
                op.setOnTimeRate(rs.getDouble("OnTimeRate"));
                op.setAvgRouteDuration(rs.getDouble("AvgRouteDuration"));
                op.setTotalHoursWorked(rs.getDouble("TotalHoursWorked"));
                results.add(op);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting operator performances.", e);
        }
        return results;
    }

}
