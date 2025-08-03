package dataaccesslayer;

import transferobjects.staff.StaffDTO;
import transferobjects.users.UsersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PTFMSDaoImpl implements PTFMSDao {

    @Override
    public boolean checkCred(String userIn, String passIn) {
        Connection connection = null;
        String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";

        connection = DataSource.getConnection();

        try (PreparedStatement prepState = connection.prepareStatement(query)) {
            prepState.setString(1, userIn);
            prepState.setString(2, passIn);

            try (ResultSet resultSet = prepState.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public void addStaffUser(StaffDTO staff, UsersDTO user) {
        Connection connection = DataSource.getConnection();

        try {
            String staffQuery = "INSERT INTO Staff (FirstName, LastName, Email, Role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement staffStmt = connection.prepareStatement(staffQuery)) {
                staffStmt.setString(1, staff.getFirstName());
                staffStmt.setString(2, staff.getLastName());
                staffStmt.setString(3, staff.getEmail());
                staffStmt.setString(4, staff.getRole());
                staffStmt.executeUpdate();
            }

            String userQuery = "INSERT INTO Users (Username, Password, StaffID) VALUES (?, ?, LAST_INSERT_ID())";
            try (PreparedStatement userStmt = connection.prepareStatement(userQuery)) {
                userStmt.setString(1, user.getUsername());
                userStmt.setString(2, user.getPassword());
                userStmt.executeUpdate();
            }

        } catch (SQLException e) {
            Logger.getLogger(PTFMSDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
