package framework.observer;

import framework.models.Data;

public interface Observable {

    void attach(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}
