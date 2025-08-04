/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Operator;
import model.Role;
import model.Staff;
import model.TransitManager;
/**
 *
 * @author A
 */
public class StaffDao {

    private final Connection conn;

    public StaffDao(Connection conn) {
        this.conn = conn;
    }

    public boolean registerStaff(Staff staff, String username, String password) throws SQLException {
        String staffSQL = "INSERT INTO Staff (FirstName, LastName, Email, Role) VALUES (?, ?, ?, ?)";
        String userSQL = "INSERT INTO Users (Username, Password, StaffID) VALUES (?, ?, ?)";

        try (
            PreparedStatement staffStmt = conn.prepareStatement(staffSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement userStmt = conn.prepareStatement(userSQL)
        ) {
            staffStmt.setString(1, staff.getFirstName());
            staffStmt.setString(2, staff.getLastName());
            staffStmt.setString(3, staff.getEmail());
            staffStmt.setString(4, staff.getRole().name()); // Store as string (e.g., "OPERATOR")
            staffStmt.executeUpdate();

            ResultSet keys = staffStmt.getGeneratedKeys();
            if (keys.next()) {
                int staffId = keys.getInt(1);
                userStmt.setString(1, username);
                userStmt.setString(2, password);
                userStmt.setInt(3, staffId);
                userStmt.executeUpdate();
                return true;
            }
        }

        return false;
    }

    public Staff getStaffById(int staffId) throws SQLException {
        String sql = "SELECT * FROM Staff WHERE StaffID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, staffId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return buildStaff(rs);
            }
        }
        return null;
    }

    public Staff authenticate(String username, String password) throws SQLException {
        String sql = """
            SELECT s.*
            FROM Users u
            JOIN Staff s ON u.StaffID = s.StaffID
            WHERE u.Username = ? AND u.Password = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return buildStaff(rs);
            }
        }

        return null;
    }

    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                staffList.add(buildStaff(rs));
            }
        }

        return staffList;
    }

    private Staff buildStaff(ResultSet rs) throws SQLException {
        int staffId = rs.getInt("StaffID");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String email = rs.getString("Email");
        Role role = Role.valueOf(rs.getString("Role")); // converts "OPERATOR" to Role.OPERATOR

        Staff staff;
        if (role == Role.OPERATOR) {
            staff = new Operator();
        } else if (role == Role.TRANSIT_MANAGER) {
            staff = new TransitManager();
        } else {
            throw new IllegalArgumentException("Invalid role in DB: " + role);
        }

        staff.setStaffId(staffId);
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setEmail(email);
        staff.setRole(role);

        return staff;
    }
}



