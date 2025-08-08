package businesslayer.observer;

import transferobjects.reports.FuelReportDTO;

/**
 * Observer interface for receiving updates about fuel reports
 * 
 * @author Khairunnisa Ashri
 * @version 1.0
 * @since JDK 21.0.4
 */
public interface FuelReportObserver {

    /**
     * Called to notify the observer of a new fuel report
     * 
     * @param report the fuel report containing fuel consumption and related data
     */
    void update(FuelReportDTO report);
}
