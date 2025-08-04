
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
 * @author Khairunnisa Ashri
 */
public class MaintenanceLogDao {

    public List<MaintenanceLogDTO> getAllLogs() throws SQLException {
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
        }

        return logs;
    }
}
