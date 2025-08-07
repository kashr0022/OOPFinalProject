package dataaccesslayer;

import businesslayer.builder.vehicles.Vehicle;
import transferobjects.reports.*;
import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;
import transferobjects.vehicles.VehicleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of PTFMSDao interface, directly works with datasource to grab info (or add) from/to the PTFMS database
 * @author Lily S., Khairunnisa Ashri
 */
public class PTFMSDaoImpl implements PTFMSDao {

    /**
     * Checks login credentials if valid or not
     * @author Lily S., Khairunnisa Ashri
     * @param userIn, passed in username set in presentation layer servlet input field
     * @param passIn, passed in password set in presentation layer servlet input field
     * @return boolean, if credentials valid
     */
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

    /**
     * Add a staff and user to database
     * @author Lily S.
     * @param staff, StaffDTO
     * @param user, UsersDTO
     */
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

            String userQuery = "INSERT INTO Users (Username, Password, Role, StaffID) VALUES (?, ?, ?, LAST_INSERT_ID())";
            try (PreparedStatement userStmt = connection.prepareStatement(userQuery)) {
                userStmt.setString(1, user.getUsername());
                userStmt.setString(2, user.getPassword());
                userStmt.setString(3, user.getRole());
                userStmt.executeUpdate();
            }

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Add a maintenance entry to the database
     * @author Lily S.
     * @param maintenance, MaintenanceLogDTO that holds all needed characteristics for a db insert
     */
    @Override
    public void addMaintenance(MaintenanceLogDTO maintenance) {
        Connection connection = DataSource.getConnection();

        try {
            String query = "INSERT INTO MaintenanceLog (StaffId, GPSID, VehicleID, ComponentID, UsageAmt, Status, Notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement prepState = connection.prepareStatement(query)) {
                prepState.setInt(1, maintenance.getStaffID());
                prepState.setInt(2, maintenance.getGpsID());
                prepState.setInt(3, maintenance.getVehicleID());
                prepState.setInt(4, maintenance.getComponentID());
                prepState.setDouble(5, maintenance.getUsageAmt());
                prepState.setString(6, maintenance.getStatus());
                prepState.setString(7, maintenance.getNotes());
                prepState.executeUpdate();
            }

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "Exception occurred relating to maintenance record add to db.", e);
        }
    }

    /**
     * Grabs user in db via unique username
     * @author Lily S.
     * @param userIn, username of desired user
     * @return Found user in a UsersDTO object
     */
    @Override
    public UsersDTO getUserByUsername(String userIn) {
        Connection connection = null;
        String query = "SELECT * FROM Users WHERE Username = ?";
        UsersDTO userDTO = new UsersDTO();
        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, userIn);

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    userDTO.setUsername(resultSet.getString("Username"));
                    userDTO.setPassword(resultSet.getString("Password"));
                    userDTO.setRole(resultSet.getString("Role"));
                    return userDTO;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        }

        return userDTO;
    }

    /**
     * Checks if a username has been taken, used during account registration step
     * @author Lily S.
     * @param user, UserDTO object
     * @return boolean, status if take or not
     */
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

    /**
     * Check if a staff combo (first and last) is already taken in the db
     * @author Lily S.
     * @param staff, StaffDTO object
     * @return boolean, status if taken or not
     */
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

    /**
     * Adds a vehicle entry to the db
     * @author Lily S.
     * @param vehicle, Vehicle object
     */
    @Override
    public void registerVehicle(Vehicle vehicle) {
        Connection connection = null;
        String query = "INSERT INTO Vehicles (VehicleNumber, VehicleType, ConsumptionRate, ConsumptionUnit, MaxPassengers, ActiveRoute)\n"
                + "VALUES (?, ?, ?, ?, ?, ?)";

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

    /**
     * Check if vehicle entry exists with the same identifier in db
     * @author Lily S.
     * @param vehicle, Vehicle object
     * @return boolean, value if taken or not
     */
    @Override
    public boolean checkVehicleTaken(Vehicle vehicle) {
        Connection connection = null;
        String query = "SELECT * FROM Vehicles WHERE VehicleNumber = ?";

        connection = DataSource.getConnection();
        try (
                PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, vehicle.getVehicleNumber());

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE,
                    "Exception caught related to vehicle exist check functionality. (" + vehicle.getVehicleNumber() + ").", ex);
            return false;
        }
        return false;
    }

    /**
     * Grabs all fuel reports
     * @author Khairunnisa Ashri, Lily S.
     * @return List containing all fuel report DTOs
     */
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

    /**
     * Update fuel report
     * @author Khairunnisa Ashri
     * @param report, FuelReportDTO object to update
     */
    @Override
    public void updateFuelReport(FuelReportDTO report) {
        String sql = """
        UPDATE FuelReport SET
            VehicleID = ?,
            StaffID = ?,
            FuelType = ?,
            UsageAmt = ?,
            DistanceTraveled = ?,
            Date = ?,
            Status = ?
        WHERE ReportID = ?
        """;

        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, report.getVehicleID());
            ps.setInt(2, report.getStaffID());
            ps.setString(3, report.getFuelType());
            ps.setDouble(4, report.getFuelConsumed());
            ps.setDouble(5, report.getDistanceTraveled());
            ps.setTimestamp(6, Timestamp.valueOf(report.getDate()));
            ps.setString(7, report.getStatus());
            ps.setInt(8, report.getReportID());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Updating fuel report failed, no rows affected.");
            }

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occurred when updating fuel report.", e);
        }
    }

    /**
     * Get all cost reports
     * @author Khairunnisa Ashri, Lily S.
     * @return List consisting of CostReportDTOs
     */
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
        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(fuel); ResultSet rs = ps.executeQuery()) {

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
        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(maintenance); ResultSet rs = ps.executeQuery()) {

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

    /**
     * Get all logs
     * @author Lily S., Khairunnisa Ashri
     * @return List consisting of MaintenanceLogDTOs
     */
    public List<MaintenanceLogDTO> getAllLogs() {
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
                         (ml.UsageAmt * c.HoursUsed) AS Cost,
                             ml.Notes as Notes
                     FROM
                         MaintenanceLog ml
                     JOIN Vehicles v ON ml.VehicleID = v.VehicleID
                     JOIN Components c ON ml.ComponentID = c.ComponentID
                     ORDER BY ml.Date DESC;
                     """;

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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
                log.setNotes(rs.getString("Notes"));
                logs.add(log);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting all logs.", e);
        }

        return logs;
    }

    /**
     * Get all operator performances
     * @author Lily S., Khairunnisa Ashri
     * @return List consisting of Operator performance DTOs
     */
    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        List<OperatorPerformanceDTO> results = new ArrayList<>();
        String sql = """
                     SELECT 
                         s.StaffID,
                         s.FirstName,
                         s.LastName,
                         IFNULL(
                             100.0 * SUM(CASE WHEN gps.EndTime <= gps.ScheduledEndTime THEN 1 ELSE 0 END) / COUNT(gps.GPSID), 
                             0
                         ) AS OnTimeRate,
                         IFNULL(AVG(TIMESTAMPDIFF(MINUTE, gps.StartTime, gps.EndTime)), 0) AS AvgRouteDuration,
                         IFNULL(SUM(TIMESTAMPDIFF(HOUR, sl.StartTime, sl.EndTime)), 0) AS TotalHoursWorked,
                         
                         -- status from BreakLog
                         (
                             SELECT 
                                 CASE bl.Action
                                     WHEN 'BREAK_START' THEN 'On Break'
                                     WHEN 'BREAK_END' THEN 'Active'
                                     WHEN 'CLOCK_OUT' THEN 'Clocked Out'
                                     WHEN 'CLOCK_IN' THEN 'Active'
                                     ELSE 'Unknown'
                                 END
                             FROM BreakLog bl
                             WHERE bl.StaffID = s.StaffID
                             ORDER BY bl.Timestamp DESC
                             LIMIT 1
                         ) AS Status
                     
                     FROM Staff s
                     LEFT JOIN GPS gps ON s.StaffID = gps.StaffID
                     LEFT JOIN StaffLog sl ON s.StaffID = sl.StaffID
                     WHERE s.Role = 'Operator'
                     GROUP BY s.StaffID, s.FirstName, s.LastName;;
                     """;

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OperatorPerformanceDTO op = new OperatorPerformanceDTO();
                op.setStaffID(rs.getInt("StaffID"));
                op.setFirstName(rs.getString("FirstName"));
                op.setLastName(rs.getString("LastName"));
                op.setOnTimeRate(rs.getDouble("OnTimeRate"));
                op.setAvgRouteDuration(rs.getDouble("AvgRouteDuration"));
                op.setTotalHoursWorked(rs.getDouble("TotalHoursWorked"));
                op.setStatus(rs.getString("Status"));
                results.add(op);
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQL Exception occured when getting operator performances.", e);
        }
        return results;
    }

    /**
     * Gets all components from db
     * @author Lily S.
     * @return List consisting of ComponentDTOs
     */
    @Override
    public List<ComponentDTO> getAllComponents() {
        Connection connection = null;
        List<ComponentDTO> componentList = new ArrayList<>();
        String query = "SELECT v.VehicleNumber, v.VehicleType, c.ComponentID, c.ComponentName, c.ComponentType, c.HoursUsed, c.VehicleID FROM Components c JOIN Vehicles v ON c.VehicleID = v.VehicleID ORDER BY c.HoursUsed DESC;";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {
            try (ResultSet resultSet = prepState.executeQuery()) {
                while (resultSet.next()) {
                    ComponentDTO component = new ComponentDTO();
//                  Grab all fields from each row entry and assign to dto obj
                    component.setVehicleId(resultSet.getInt("VehicleID"));
                    component.setVehicleNum(resultSet.getString("VehicleNumber"));
                    component.setVehicleType(resultSet.getString("VehicleType"));
                    component.setComponentId(resultSet.getInt("ComponentID"));
                    component.setComponentName(resultSet.getString("ComponentName"));
                    component.setComponentType(resultSet.getString("ComponentType"));
                    component.setHoursUsed(resultSet.getInt("HoursUsed"));

                    componentList.add(component);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return componentList;
    }

    /**
     * Get all staff from db
     * @author Lily S.
     * @return List consisting of each staff entry as a StaffDTO
     */
    @Override
    public List<StaffDTO> getAllStaff() {
        Connection connection = null;
        List<StaffDTO> staffList = new ArrayList<>();
        String query = "SELECT StaffID, FirstName, LastName, Email, Role FROM Staff ORDER BY StaffID DESC;";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {
            try (ResultSet resultSet = prepState.executeQuery()) {
                while (resultSet.next()) {
                    StaffDTO staff = new StaffDTO();

//                  Grab all fields from each row entry and assign to dto obj
                    staff.setStaffId(resultSet.getInt("StaffID"));
                    staff.setRole(resultSet.getString("Role"));
                    staff.setFirstName(resultSet.getString("FirstName"));
                    staff.setLastName(resultSet.getString("LastName"));
                    staff.setEmail(resultSet.getString("Email"));

                    staffList.add(staff);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "Occurred during getAllStaff() DAO method.", ex);
        }

        return staffList;
    }

    /**
     * Get all gps from db
     * @author Lily S.
     * @return List consisting of each gps entry as a GpsDTO
     */
    @Override
    public List<GpsDTO> getAllGps() {
        Connection connection = null;
        List<GpsDTO> gpsList = new ArrayList<>();
        String query = "SELECT GPSID, StaffID, VehicleID, StartingLocation, StartTime, EndTime, EndingLocation, Notes FROM GPS ORDER BY GPSID DESC;";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {
            try (ResultSet resultSet = prepState.executeQuery()) {
                while (resultSet.next()) {
                    GpsDTO gps = new GpsDTO();

//                  Grab all fields from each row entry and assign to dto obj
                    gps.setGpsID(resultSet.getInt("GPSID"));
                    gps.setStaffID(resultSet.getInt("StaffID"));
                    gps.setVehicleID(resultSet.getInt("VehicleID"));
                    gps.setStartingLocation(resultSet.getString("StartingLocation"));
                    gps.setStartTime(resultSet.getTimestamp("StartTime").toLocalDateTime());
                    gps.setEndTime(resultSet.getTimestamp("EndTime").toLocalDateTime());
                    gps.setEndingLocation(resultSet.getString("EndingLocation"));
                    gps.setNotes(resultSet.getString("Notes"));

                    gpsList.add(gps);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "Occurred during getAllStaff() DAO method.", ex);
        }

        return gpsList;
    }

    /**
     * Grabs component in DB via specific ID passed in
     * @author Lily S.
     * @param id, id of desired component
     * @return ComponentDTO, specified component in DTO form to store all characteristics
     */
    @Override
    public ComponentDTO getComponentByID(int id) {
        Connection connection = null;
        String query = "SELECT * FROM Components WHERE ComponentID = ?";
        ComponentDTO componentDTO = new ComponentDTO();
        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, String.valueOf(id));

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    componentDTO.setComponentId(resultSet.getInt("ComponentID"));
                    componentDTO.setVehicleId(resultSet.getInt("VehicleID"));
                    componentDTO.setComponentName(resultSet.getString("ComponentName"));
                    componentDTO.setComponentType(resultSet.getString("ComponentType"));
                    componentDTO.setHoursUsed(resultSet.getInt("HoursUsed"));
                    return componentDTO;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQLException caught when trying to find component by ID.", ex);

        }

        return componentDTO;
    }

    /**
     * Grabs vehicle in DB via specific ID passed in
     * @author Lily S.
     * @param id, id of desired vehicle
     * @return VehicleDTO, specified vehicle in DTO form to store all characteristics
     */
    @Override
    public VehicleDTO getVehicleByID(int id) {
        Connection connection = null;
        String query = "SELECT * FROM Vehicles WHERE VehicleID = ?";
        VehicleDTO vehicleDTO = new VehicleDTO();
        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {

            prepState.setString(1, String.valueOf(id));

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    vehicleDTO.setVehicleNumber(resultSet.getString("VehicleNumber"));
                    vehicleDTO.setVehicleType(resultSet.getString("VehicleType"));
                    vehicleDTO.setConsumptionRate(resultSet.getDouble("ConsumptionRate"));
                    vehicleDTO.setConsumptionUnit(resultSet.getString("ConsumptionUnit"));
                    vehicleDTO.setMaxPassengers(resultSet.getInt("MaxPassengers"));
                    vehicleDTO.setActiveRoute(resultSet.getString("ActiveRoute"));
                    return vehicleDTO;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQLException caught when trying to find vehicle by ID.", ex);

        }

        return vehicleDTO;
    }

    /**
     * Get break logs by staff ID
     * @author Khairunnisa Ashri
     * @param staffID, staff ID to filter break logs
     * @return List of BreakLogDTOs for the specified staff member
     */
    @Override
    public List<BreakLogDTO> getBreakLogsByStaffID(int staffID) {
        List<BreakLogDTO> logs = new ArrayList<>();
        String sql = """
                     SELECT bl.Action, bl.Timestamp
                             FROM BreakLog bl
                             WHERE bl.StaffID = ?
                             ORDER BY bl.Timestamp ASC
                     """;
        try (Connection con = DataSource.getConnection(); PreparedStatement ps
                = con.prepareStatement(sql)) {
            ps.setInt(1, staffID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BreakLogDTO log = new BreakLogDTO();
                    log.setAction(rs.getString("Action"));
                    log.setTimestamp(rs.getTimestamp("Timestamp").toLocalDateTime());
                    logs.add(log);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQLException caught when trying to find staff by ID.", e);
        }
        return logs;
    }

    /**
     * Get staff by staff ID
     * @author Khairunnisa Ashri
     * @param staffID, staff ID to retrieve
     * @return StaffDTO object for the specified staff ID
     */
    @Override
    public StaffDTO getStaffByID(int staffID) {
        String sql = "SELECT FirstName, LastName FROM Staff WHERE StaffID = ?";
        try (Connection con = DataSource.getConnection(); PreparedStatement ps
                = con.prepareStatement(sql)) {
            ps.setInt(1, staffID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StaffDTO staff = new StaffDTO();
                    staff.setFirstName(rs.getString("FirstName"));
                    staff.setLastName(rs.getString("LastName"));
                    return staff;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "SQLException caught when fetching staff by ID.", e);
        }
        return null;
    }

    /**
     * Insert break log entry
     * @author Khairunnisa Ashri
     * @param log, BreakLogDTO containing break log information to insert
     */
    @Override
    public void insertBreakLog(BreakLogDTO log) {
        String sql = "INSERT INTO BreakLog (StaffID, Action, Timestamp) VALUES (?, ?, ?)";
        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, log.getStaffID());
            ps.setString(2, log.getAction());
            ps.setTimestamp(3, Timestamp.valueOf(log.getTimestamp()));
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "Failed to insert break log.", e);
        }
    }

    /**
     * Get staff by username
     * @author Khairunnisa Ashri
     * @param username, username to retrieve staff information
     * @return StaffDTO object for the specified username
     */
    @Override
    public StaffDTO getStaffByUsername(String username) {
        StaffDTO staff = null;
        String sql = "SELECT s.StaffID, s.FirstName, s.LastName, s.Email "
                + "FROM Staff s INNER JOIN Users u ON s.StaffID = u.StaffID "
                + "WHERE u.Username = ?";

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    staff = new StaffDTO();
                    staff.setStaffId(rs.getInt("StaffID"));
                    staff.setFirstName(rs.getString("FirstName"));
                    staff.setLastName(rs.getString("LastName"));
                    staff.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, "Error fetching staff by username.", e);
        }
        return staff;
    }

}