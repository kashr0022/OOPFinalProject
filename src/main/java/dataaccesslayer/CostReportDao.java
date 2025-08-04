package dataaccesslayer;

/**
 *
 * @author Khairunnisa Ashri
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.reports.CostReportDTO;

public class CostReportDao {
    public List<CostReportDTO> getCostReport() throws SQLException {
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
        }

        // sort by date ascending
        reportList.sort((b, a) -> b.getDate().compareTo(a.getDate()));

        return reportList;
    }
}

