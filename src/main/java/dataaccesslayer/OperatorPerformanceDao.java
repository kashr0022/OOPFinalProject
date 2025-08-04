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
import transferobjects.reports.OperatorPerformanceDTO;

/**
 *
 * @author A
 */
public class OperatorPerformanceDao {

    public List<OperatorPerformanceDTO> getOperatorPerformance() throws SQLException {
        List<OperatorPerformanceDTO> results = new ArrayList<>();
        String sql = "SELECT \n"
                + "    s.StaffID,\n"
                + "    s.FirstName,\n"
                + "    s.LastName,\n"
                + "    -- Calculate on-time rate as a percentage (0 to 100)\n"
                + "    IFNULL(\n"
                + "      100.0 * SUM(CASE WHEN gps.EndTime <= gps.ScheduledEndTime THEN 1 ELSE 0 END) / COUNT(gps.GPSID), \n"
                + "      0\n"
                + "    ) AS OnTimeRate,\n"
                + "    \n"
                + "    -- Your existing metrics:\n"
                + "    IFNULL(AVG(TIMESTAMPDIFF(MINUTE, gps.StartTime, gps.EndTime)), 0) AS AvgRouteDuration,\n"
                + "    IFNULL(SUM(TIMESTAMPDIFF(HOUR, sl.StartTime, sl.EndTime)), 0) AS TotalHoursWorked\n"
                + "\n"
                + "FROM Staff s\n"
                + "LEFT JOIN GPS gps ON s.StaffID = gps.StaffID\n"
                + "LEFT JOIN StaffLog sl ON s.StaffID = sl.StaffID\n"
                + "WHERE s.Role = 'Operator'\n" 
                + "GROUP BY s.StaffID, s.FirstName, s.LastName";

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OperatorPerformanceDTO dto = new OperatorPerformanceDTO();
                dto.setStaffID(rs.getInt("StaffID"));
                dto.setFirstName(rs.getString("FirstName"));
                dto.setLastName(rs.getString("LastName"));
                dto.setOnTimeRate(rs.getDouble("OnTimeRate"));
                dto.setAvgRouteDuration(rs.getDouble("AvgRouteDuration"));
                dto.setTotalHoursWorked(rs.getDouble("TotalHoursWorked"));
                results.add(dto);
            }
        }
        return results;
    }
}
