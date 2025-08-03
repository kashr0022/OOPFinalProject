package dataaccesslayer;

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
}
