/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.reports.MaintenanceLogDTO;

/**
 *
 * @author A
 */
public class MaintenanceLogDao {

    public List<MaintenanceLogDTO> getAllLogs() throws SQLException {
        List<MaintenanceLogDTO> logs = new ArrayList<>();

        String sql = "SELECT\n"
                + "    ml.LogID,\n"
                + "    ml.VehicleID,\n"
                + "    v.VehicleType AS Type,\n"
                + "    c.ComponentID AS ComponentID, \n"
                + "    c.ComponentName AS ComponentName,\n"
                + "    ml.UsageAmt AS UsageHours,\n"
                + "    CASE\n"
                + "        WHEN ml.Status = 'Completed' THEN 'All systems normal'\n"
                + "        WHEN ml.Status = 'In Progress' THEN 'Under maintenance'\n"
                + "        WHEN ml.Status = 'Pending' THEN 'Scheduled'\n"
                + "        ELSE 'Status unknown'\n"
                + "    END AS Diagnostics,\n"
                + "    ml.Date AS Timestamp,\n"
                + "    CASE\n"
                + "        WHEN ml.Status = 'Pending' THEN 'Schedule'\n"
                + "        ELSE ml.Status\n"
                + "    END AS Action\n"
                + "FROM\n"
                + "    MaintenanceLog ml\n"
                + "JOIN Vehicles v ON ml.VehicleID = v.VehicleID\n"
                + "JOIN Components c ON ml.ComponentID = c.ComponentID\n"
                + "ORDER BY ml.Date DESC;";

        try (Connection con = DataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

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

                logs.add(log);
            }
        }

        return logs;
    }
}
