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
        
        String sql = "SELECT * FROM MaintenanceLog";

        try (Connection con = DataSource.getConnection();  
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaintenanceLogDTO log = new MaintenanceLogDTO();
                log.setLogID(rs.getInt("LogID"));      
                log.setVehicleID(rs.getInt("VehicleID"));
                log.setComponentID(rs.getInt("ComponentID"));
                log.setUsageAmt(rs.getDouble("UsageAmt"));
                log.setStatus(rs.getString("Status"));
                log.setDate(rs.getTimestamp("Date").toLocalDateTime());

                logs.add(log);
            }
        }

        return logs;
    }
}


