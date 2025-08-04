
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.reports.OperatorPerformanceDTO;

/**
 *
 * @author Khairunnisa Ashri
 */
public class OperatorPerformanceDao {

    public List<OperatorPerformanceDTO> getOperatorPerformance() throws SQLException {
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
        }
        return results;
    }
}
