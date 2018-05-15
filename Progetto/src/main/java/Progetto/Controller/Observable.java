package Progetto.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    public List<Observer> observers;

    public Observable(){
        observers=new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObserver(){
        for(Observer observer: this.observers)
            observer.update();
    }

    /*public void notifyObserver(Object o){
        for(Observer observer: this.observers){
            observer.update(o);
        }
    }*/
}

