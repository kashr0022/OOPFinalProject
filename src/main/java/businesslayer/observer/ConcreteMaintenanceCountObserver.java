package businesslayer.observer;

/**
 * Concrete observer meant to track component dto stats and increment counter when it comes across a usage amt that is above standards
 * @author Lily S.
 * @version 1.0
 * @since JDK 21.0.4
 */
public class ConcreteMaintenanceCountObserver implements CounterObserver {
    private int counter = 0;


    @Override
    /**
     * increment counter by one
     */
    public void update() {
        counter++;
    }

    /**
     * getter for counter value
     * @return counter value
     */
    public int getCounter() {
        return counter;
    }
}
