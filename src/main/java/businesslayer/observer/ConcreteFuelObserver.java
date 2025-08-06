package businesslayer.observer;

import transferobjects.reports.FuelReportDTO;

/**
 * Concrete implementation of the {@link FuelReportObserver} interface
 * that monitors fuel reports and tracks the number of alerts for high fuel consumption.
 * 
 * Triggers an alert when fuel consumed exceeds 100 units
 * 
 * @author Khairunnisa Ashri
 */
public class ConcreteFuelObserver implements FuelReportObserver {
    /**
     * Counts the number of fuel consumption alerts detected.
     */
    private int alertCount = 0;

    /**
     * Called when a new fuel report is received.
     * Increments the alert count if the fuel consumed exceeds 100 units.
     * 
     * @param report The fuel report data transfer object containing fuel consumption details.
     */
    @Override
    public void update(FuelReportDTO report) {
        if (report.getFuelConsumed() > 100) {
            alertCount++;
            System.out.println("[ALERT] High fuel consumption: " + report.getFuelConsumed()
                    + " for Vehicle ID: " + report.getVehicleID());
        }
    }

    /**
     * Returns the number of high fuel consumption alerts detected so far.
     * 
     * @return the alert count
     */
    public int getAlertCount() {
        return alertCount;
    }
}
