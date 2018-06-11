package it.polimi.ing.sw.network.protocol;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.SchemeCard;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;


import java.util.ArrayList;
import java.util.List;

public abstract class Observable { //eliminabile, chiamo direttamente dal model e bona
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

    public void notifyChoiseScheme (ArrayList<SchemeCard> schemes, Player player) {
        for(Observer observer: this.observers)
            observer.updateChoiseScheme(schemes, player);
    }

    public void notifyNewRound (Player firstPlayer, DraftPool draftPool) {
        for(Observer observer: this.observers)
            observer.updateNewRound(firstPlayer, draftPool);
    }

    public void notifyRanking (ArrayList<Player> ranking) {
        for(Observer observer: this.observers)
            observer.updateRanking(ranking);
    }

    public void notifyNextPlayer (Player player) {
        for(Observer observer: this.observers)
            observer.updateNextPlayer(player);
    }

    public void notifyPublicObjectivesChoosen (ArrayList<ObjectiveCard> publicObjectives) {
        for(Observer observer: this.observers)
            observer.updatePublicObjectivesChoosen(publicObjectives);
    }

    public void notifyUsedDice (boolean ok) {
        for(Observer observer: this.observers)
            observer.updateUsedDice(ok);
    }

    public void notifyMessage(String s){
        for(Observer observer: this.observers){
            observer.showMessage(s);
        }
    }
}

