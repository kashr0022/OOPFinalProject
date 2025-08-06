package businesslayer.observer;

/**
 * @author Lily S.
 */
public class ConcreteMaintenanceCountObserver implements CounterObserver {
    private int counter = 0;

    @Override
    public void update() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
