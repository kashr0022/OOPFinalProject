
package dataaccesslayer;

import dataaccesslayer.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.staff.OperatorPerformanceDTO;

/**
 *
 * @author Khairunnisa Ashri
 */
public class OperatorDao {
    public List<OperatorPerformanceDTO> getOperatorPerformance() throws SQLException {
        List<OperatorPerformanceDTO> list = new ArrayList<>();
        String sql =
                "SELECT CONCAT(s.FirstName, ' ', s.LastName) AS OperatorName,"
                + "ROUND(SUM(TIMESTAMPDIFF(MINUTE, l.StartTime, l.EndTime)) / 60, 2) AS HourseWorked"
                + "FROM Staff s"
                + "JOIN StaffLog l ON s.StaffID = lStaff.ID"
                + "WHERE s.Role = 'Operator'"
                + "GROUP BY s.StaffID, s.FirstName, s.LastName;";
        
        try (Connection con = DataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                String name = rs.getString("OperatorName");
                double hours = rs.getDouble("HoursWorked");
                list.add(new OperatorPerformanceDTO(name, hours));
            }
        }
        return list;
    }
}
