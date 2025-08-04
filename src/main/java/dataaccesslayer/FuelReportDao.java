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
import transferobjects.reports.FuelReportDTO;

/**
 *
 * @author A
 */
public class FuelReportDao {

    public List<FuelReportDTO> getFuelReport() throws SQLException {
        List<FuelReportDTO> reports = new ArrayList<>();
        String sql = "SELECT fr.ReportID, fr.VehicleID, v.VehicleType, fr.StaffID, s.FirstName, s.LastName, "
                + "fr.FuelType, fr.UsageAmt, fr.DistanceTraveled, fr.Date, fr.Status "
                + "FROM FuelReport fr "
                + "JOIN Vehicles v ON fr.VehicleID = v.VehicleID "
                + "JOIN Staff s ON fr.StaffID = s.StaffID";

        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FuelReportDTO report = new FuelReportDTO();
                report.setReportID(rs.getInt("reportID"));
                report.setStaffID(rs.getInt("staffID"));
                report.setVehicleID(rs.getInt("vehicleID"));
                report.setUsageAmt(rs.getDouble("usageAmt"));
                report.setDate(rs.getTimestamp("date").toLocalDateTime());
                report.setStatus(rs.getString("status"));
                report.setVehicleType(rs.getString("vehicleType")); 
                reports.add(report);
                reports.add(report);
            }
        }
        return reports;
    }
}
