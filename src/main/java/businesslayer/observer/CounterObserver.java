package businesslayer.observer;

/**
 * Observer interface for monitoring component stats and updating counters according to component data
 * 
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public interface CounterObserver {
    /**
     * Interface method meant to update counter values
     */
    void update();

    /**
     * Interface method to get current counter value
     * @return int, counter value
     */
    int getCounter();
}
