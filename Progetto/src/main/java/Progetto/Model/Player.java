package Progetto.Model;

import Progetto.Model.Exceptions.NotValidException;

public class Player {
    private final String nickname;
    private final int orderInRound;    // ordine che viene assegnato al giocatore nel primo round. Questo numero identifica il giocatore ed è molto più comodo gestire tutta la parte di passaggio da un giocatore all'altro utilizzando questo
    private int numOfToken;
    private int score;
    //private boolean inGame;      credo non sia necessario visto che dobbiamo gestire solo una partita alla volta
    private Scheme scheme;
    private ObjectiveCard privateObjective;


    public Player (String nickname, int orderInRound) {
        this.nickname=nickname;
        this.orderInRound=orderInRound;
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

    public ObjectiveCard getPrivateObjective() {
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

    public void setPrivateObjective(ObjectiveCard objectiveCard) {
        privateObjective = objectiveCard;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public void useDice(Box box, Dice dice, DraftPool draftPool) throws NotValidException {
        if(scheme.isEmpty()){
            if(scheme.checkFirst(box, dice)){
                box.placeDice(dice);
                draftPool.removeDice(dice);
                scheme.setNotEmpty();
            }
        }
        else if(!box.isFull()&& scheme.checkBox(box,dice) && scheme.checkDiceAdjacent(box,dice)){
            box.placeDice(dice);
            draftPool.removeDice(dice);
        }
        else throw new NotValidException("L'inserimento non è corretto");
    }

    @Override
    public String toString() {
        return "ID:"+this.getNickname()+"\nORDINE"+this.getOrderInRound()+"\nTOKEN"+this.getNumOfToken()+"\nSCORE:"+this.getScore();
    }
}

