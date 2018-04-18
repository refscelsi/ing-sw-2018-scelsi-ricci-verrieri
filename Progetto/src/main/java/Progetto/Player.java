package Progetto;

public class Player {
    private final String nickname;
    private final int orderInRound;    // ordine che viene assegnato al giocatore nel primo round. Questo numero identifica il giocatore ed è molto più comodo gestire tutta la parte di passaggio da un giocatore all'altro utilizzando questo
    private int numOfToken;
    private int score;
    private boolean inGame;

    public Player(String nickname, int orderInRound){
        this.nickname=nickname;
        this.orderInRound=orderInRound;
    }

    public void login(){

    }

    public boolean isInGame() {
        return inGame;
    }

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

    public void setScore(int score) {
        this.score = score;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void setNumOfToken(int numOfToken) {
        this.numOfToken = numOfToken;
    }

    public void drawDice(){
    }

    @Override
    public String toString() {
        return "ID:"+this.nickname()+"\nORDINE"+this.getOrderInRound()+"\nTOKEN"+this.getNumOfToken()+"\nSCORE:"+this.getScore()+"\ninGame?"+this.isInGame();
    }
}

