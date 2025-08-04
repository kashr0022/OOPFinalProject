
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
 * @author Khairunnisa Ashri
 */
public class FuelReportDaoImpl {

    public List<FuelReportDTO> getFuelReport() throws SQLException {
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
        }
        return reports;
    }
}
