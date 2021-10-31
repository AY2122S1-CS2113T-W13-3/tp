package seedu.duke.task.taskmanager;

import java.util.ArrayList;
import java.util.List;

//@@author SeanRobertDH
interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    default List<Observer> getObservers() {
        return observers;
    }


    default void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
