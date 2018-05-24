package Progetto.Model;

import Progetto.Model.ObjectiveCard.*;
import Progetto.Model.Exceptions.NotValidException;

public class Player {
    private final String nickname;
    private int orderInRound;    // ordine che viene assegnato al giocatore nel primo round. Questo numero identifica il giocatore ed è molto più comodo gestire tutta la parte di passaggio da un giocatore all'altro utilizzando questo
    private int numOfToken;
    private int score;
    //private boolean inGame;      credo non sia necessario visto che dobbiamo gestire solo una partita alla volta
    private Scheme scheme;
    private PrivateObjectiveCard privateObjective;


    public Player (String nickname) {
        this.nickname=nickname;
    }

    //public void login(){}       credo non sia necessario

    /*public boolean isInGame() {       credo non sia necessario visto che dobbiamo gestire solo una partita alla volta
        return inGame;
    }*/

    public int getNumOfToken() {
        return numOfToken;
    }

    public int getOrderInRound() {
        return orderInRound;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void setOrderInRound(int i){
        orderInRound=i;
    }

    public PrivateObjectiveCard getPrivateObjective() {
        return privateObjective;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /*public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }*/

    public void setNumOfToken(int numOfToken) {
        this.numOfToken = numOfToken;
    }

    public void setPrivateObjective(PrivateObjectiveCard objectiveCard) {
        privateObjective = objectiveCard;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public boolean useDice(Box box, Dice dice) throws NotValidException {
        if(scheme.isEmpty()){
            if(scheme.checkFirst(box, dice)){
                box.placeDice(dice);
                scheme.setNotEmpty();
                return true;
            }
        }
        else if(!box.isFull()&& scheme.checkBox(box,dice) && scheme.checkDiceAdjacent(box,dice)){
            box.placeDice(dice);
            return true;
        }
        else throw new NotValidException("L'inserimento non è corretto");
        return false;
    }

    @Override
    public String toString() {
        return "ID:"+this.getNickname()+"\nORDINE"+this.getOrderInRound()+"\nTOKEN"+this.getNumOfToken()+"\nSCORE:"+this.getScore();
    }

}