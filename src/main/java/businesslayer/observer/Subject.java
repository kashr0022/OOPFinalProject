package businesslayer.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lily S.
 */
public class Subject {
    private List<CounterObserver> counterList = new ArrayList<>();

    public void addObserver(CounterObserver observer){
        counterList.add(observer);
    }
    public void removeObserver(CounterObserver observer){
        counterList.remove(observer);
    }
    public void notifyObserver(){
        for(CounterObserver counterObserver : counterList){
            counterObserver.update();
        }
    }
}
