package businesslayer.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for observer pattern related to monitoring components usages, grabbed from DB. Has a list of observers that it can notify when changes are caught
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class Subject {
    private List<CounterObserver> counterList = new ArrayList<>();

    /**
     * add an observer to the list
     * @param observer CounterObserver
     */
    public void addObserver(CounterObserver observer){
        counterList.add(observer);
    }

    /**
     * remove an observer from the list
     * @param observer CounterObserver
     */
    public void removeObserver(CounterObserver observer){
        counterList.remove(observer);
    }

    /**
     * Notify all observers in the list of a change occuring.
     */
    public void notifyObserver(){
        for(CounterObserver counterObserver : counterList){
            counterObserver.update();
        }
    }
}
