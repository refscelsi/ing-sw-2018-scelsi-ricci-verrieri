package Progetto.Controller;

import Progetto.Model.ObjectiveCard.ObjectiveCard;
import Progetto.Model.State;
import Progetto.Model.*;

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

    /*public void notifyObserver(State gameState){
        for(Observer observer: this.observers)
            observer.update();
    }*/

    public void notifyObserver (ArrayList<SchemeCard> schemes, Player player) {
        for(Observer observer: this.observers)
            observer.update(schemes, player);
    }

    public void notifyObserver (Player firstPlayer, DraftPool draftPool) {
        for(Observer observer: this.observers)
            observer.update(firstPlayer, draftPool);
    }

    public void notifyObserver (ArrayList<Player> ranking) {
        for(Observer observer: this.observers)
            observer.update(ranking);
    }

    public void notifyObserver (Player player) {
        for(Observer observer: this.observers)
            observer.update(player);
    }

    public void notifyObserver1 (ArrayList<ObjectiveCard> publicObjectives) {
        for(Observer observer: this.observers)
            observer.update1(publicObjectives);
    }

    public void notifyObserver (Dice dice) {
        for(Observer observer: this.observers)
            observer.update(dice);
    }

    public void notifyObserver(String s){
        for(Observer observer: this.observers){
            observer.notifyMessage(s);
        }
    }
}

