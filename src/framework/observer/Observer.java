package framework.observer;

import framework.models.Data;

public interface Observer {
    void update(Observable observable, Data data);
}
