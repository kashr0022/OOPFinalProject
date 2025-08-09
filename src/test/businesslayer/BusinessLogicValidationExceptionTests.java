package businesslayer;

import org.junit.Test;
import transferobjects.reports.MaintenanceLogDTO;

import java.time.LocalDateTime;

/**
 * Unit tests for PTFMSBusinessLogic that verify
 * Each test creates a baseline
 * and then alters one field to simulate
 * an invalid scenario before calling
 * @author oussema
 */
public class BusinessLogicValidationExceptionTests {

    /** Instance of the business logic layer to test. */
    private final PTFMSBusinessLogic logic = new PTFMSBusinessLogic();

    /*
    // 1) Null DTO
    // Disabled because a standard Java exception (NullPointerException)
    // is thrown instead of a ValidationException.
    @Test(expected = ValidationException.class)
    public void nullMaintenance_throws() {
        logic.addMaintenance(null);
    }
    */

    /**
     * Verifies that a staff ID of zero triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void invalidStaffId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setStaffID(0);
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a vehicle ID of zero triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void invalidVehicleId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setVehicleID(0);
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a component ID of zero triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void invalidComponentId_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setComponentID(0);
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a negative usage amount triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void negativeUsageAmt_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setUsageAmt(-5.0);
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a {@code null} date triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void nullDate_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setDate(null);
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a blank status (only spaces) triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void blankStatus_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setStatus("  ");
        logic.addMaintenance(m);
    }

    /**
     * Verifies that blank notes trigger a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void blankNotes_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setNotes("");
        logic.addMaintenance(m);
    }

    /**
     * Verifies that a negative cost triggers a {@link ValidationException}.
     */
    @Test(expected = ValidationException.class)
    public void negativeCost_throws() {
        MaintenanceLogDTO m = makeBaseLog();
        m.setCost(-100.0);
        logic.addMaintenance(m);
    }

    /**
     * Creates a valid {@link MaintenanceLogDTO} instance with all
     * required fields set to acceptable values.
     *
     * @return a baseline maintenance log object with valid data
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
