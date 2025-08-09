package businesslayer.observer;

import java.util.ArrayList;
import java.util.List;
import transferobjects.reports.FuelReportDTO;

/**
 * Subject class in the Observer pattern that manages and notifies registered instances about updates in fuel reports
 * 
 * @author Khairunnisa Ashri
 * @version 1.0
 * @since JDK 21.0.4
 */
public class FuelReportSubject {
    
    /**
     * List of registered observers to be notified of updates
     */
    private final  List<FuelReportObserver> observers = new ArrayList<>();

    /**
     * Registers a new observer to receive updates
     * 
     * @param observer to add
     */
    public void addObserver(FuelReportObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers with the provided fuel report
     * 
     * @param report containing the updated fuel information
     */
    public void notifyObservers(FuelReportDTO report) {
        for (FuelReportObserver o : observers) {
            o.update(report);
        }
    }
}
