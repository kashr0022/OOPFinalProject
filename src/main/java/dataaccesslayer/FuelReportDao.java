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
        String sql = "SELECT \n"
                + "    fr.ReportID,\n"
                + "    fr.VehicleID,\n"
                + "    v.VehicleType,\n"
                + "    fr.StaffID,\n"
                + "    s.FirstName,\n"
                + "    s.LastName,\n"
                + "    fr.FuelType,\n"
                + "    fr.UsageAmt,\n"
                + "    CASE\n"
                + "        WHEN v.VehicleType = 'ElectricLightRail' THEN 'kWh/hr'\n"
                + "        WHEN v.VehicleType = 'DieselBus' THEN 'mpg'\n"
                + "        WHEN v.VehicleType = 'DieselElectricTrain' THEN 'mpg'\n"
                + "        ELSE 'units'\n"
                + "    END AS Unit,\n"
                + "    fr.DistanceTraveled,\n"
                + "    fr.Date,\n"
                + "    fr.Status\n"
                + "FROM FuelReport fr\n"
                + "JOIN Vehicles v ON fr.VehicleID = v.VehicleID\n"
                + "JOIN Staff s ON fr.StaffID = s.StaffID";

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
        }
        return reports;
    }
}
