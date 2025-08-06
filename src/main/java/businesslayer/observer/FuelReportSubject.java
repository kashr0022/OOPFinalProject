package businesslayer.observer;

import java.util.ArrayList;
import java.util.List;
import transferobjects.reports.FuelReportDTO;

/**
 * Subject class in the Observer pattern that manages and notifies
 * registered {@link FuelReportObserver} instances about updates
 * in fuel reports.
 * 
 * Observers can be added to this subject and will be notified
 * whenever a new {@link FuelReportDTO} is available.
 * 
 * 
 * @author Khairunnisa Ashri
 */
public class FuelReportSubject {
    /**
     * List of registered observers to be notified of updates.
     */
    private final List<FuelReportObserver> observers = new ArrayList<>();

    /**
     * Registers a new observer to receive updates.
     * 
     * @param observer the {@link FuelReportObserver} to add
     */
    public void addObserver(FuelReportObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers with the provided fuel report.
     * 
     * @param report the {@link FuelReportDTO} containing the updated fuel information
     */
    public void notifyObservers(FuelReportDTO report) {
        for (FuelReportObserver o : observers) {
            o.update(report);
        }
    }
}
