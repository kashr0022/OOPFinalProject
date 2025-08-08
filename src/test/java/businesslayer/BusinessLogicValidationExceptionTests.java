package businesslayer;

import org.junit.Test;
import transferobjects.reports.MaintenanceLogDTO;

import java.time.LocalDateTime;

/**
 * Verifies that addMaintenance throws ValidationException
 * for each invalid‐input scenario.
 */
public class BusinessLogicValidationExceptionTests {

    private final PTFMSBusinessLogic logic = new PTFMSBusinessLogic();
    /**
    // 1) Null DTO  ->  this  exception was not the one created  via validationexception  , but java exception was thrown instead
    @Test(expected = ValidationException.class)
    public void nullMaintenance_throws() {
        logic.addMaintenance(null);
    }
    */
    // 2) Invalid staff ID
    @Test(expected = ValidationException.class)
    public void invalidStaffId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setStaffID(0);
        logic.addMaintenance(m);
    }

    // 3) Invalid vehicle ID
    @Test(expected = ValidationException.class)
    public void invalidVehicleId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setVehicleID(0);
        logic.addMaintenance(m);
    }

    // 4) Invalid component ID
    @Test(expected = ValidationException.class)
    public void invalidComponentId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setComponentID(0);
        logic.addMaintenance(m);
    }

    // 5) Negative usage amount
    @Test(expected = ValidationException.class)
    public void negativeUsageAmt_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setUsageAmt(-5.0);
        logic.addMaintenance(m);
    }

    // 6) Null date
    @Test(expected = ValidationException.class)
    public void nullDate_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setDate(null);
        logic.addMaintenance(m);
    }

    // 7) Blank status
    @Test(expected = ValidationException.class)
    public void blankStatus_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setStatus("  ");
        logic.addMaintenance(m);
    }

    // 8) Blank notes
    @Test(expected = ValidationException.class)
    public void blankNotes_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setNotes("");
        logic.addMaintenance(m);
    }

    // 9) Negative cost
    @Test(expected = ValidationException.class)
    public void negativeCost_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setCost(-100.0);
        logic.addMaintenance(m);
    }

    /**
     * Creating a baseline maintenance log
     */
    private MaintenanceLogDTO makeBaseLog() {
        MaintenanceLogDTO m = new MaintenanceLogDTO();
        m.setStaffID(1);
        m.setVehicleID(1);
        m.setComponentID(1);
        m.setUsageAmt(1.0);
        m.setDate(LocalDateTime.now());
        m.setStatus("Pending");
        m.setNotes("Valid notes");
        m.setCost(50.0);
        return m;
    }
}
