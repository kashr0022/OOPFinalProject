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
                + "    \n"
                + "    -- Average route duration in minutes (null-safe)\n"
                + "    IFNULL(AVG(TIMESTAMPDIFF(MINUTE, gps.StartTime, gps.EndTime)), 0) AS AvgRouteDurationMins,\n"
                + "\n"
                + "    -- Total hours worked\n"
                + "    IFNULL(SUM(TIMESTAMPDIFF(HOUR, sl.StartTime, sl.EndTime)), 0) AS TotalHoursWorked\n"
                + "\n"
                + "FROM Staff s\n"
                + "\n"
                + "-- GPS Join for route tracking\n"
                + "LEFT JOIN GPS gps ON s.StaffID = gps.StaffID\n"
                + "\n"
                + "-- StaffLog Join for shift hours\n"
                + "LEFT JOIN StaffLog sl ON s.StaffID = sl.StaffID\n"
                + "\n"
                + "WHERE s.Role = 'Operator'\n"
                + "\n"
                + "GROUP BY s.StaffID, s.FirstName, s.LastName;\n";

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OperatorPerformanceDTO dto = new OperatorPerformanceDTO();
                dto.setStaffID(rs.getInt("StaffID"));
                dto.setFirstName(rs.getString("FirstName"));
                dto.setLastName(rs.getString("LastName"));
//                dto.setOnTimeRate(rs.getDouble("OnTimeRate"));
//                dto.setAvgRouteDuration(rs.getDouble("AvgRouteDuration"));
                dto.setTotalHoursWorked(rs.getDouble("TotalHoursWorked"));
                results.add(dto);
            }
        }
        return results;
    }
}
