package businesslayer.observer;

import transferobjects.reports.FuelReportDTO;

/**
 * Observer interface for receiving updates about fuel reports.
 * 
 * Implementations of this interface define actions to be taken
 * when a new {@link FuelReportDTO} is received.
 * 
 * @author Khairunnisa Ashri
 */
public interface FuelReportObserver {

    /**
     * Called to notify the observer of a new fuel report.
     * 
     * @param report the fuel report containing fuel consumption and related data
     */
    void update(FuelReportDTO report);
}
