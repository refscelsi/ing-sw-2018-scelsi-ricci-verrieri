package Progetto;

public class Player {
    private int playerId;
    private int orderInRound;
    private int numOfToken;
    private int score;
    private boolean inGame;

    public Player(int playerId, int orderInRound, int numOfToken, int score, boolean inGame){
        this.playerId=playerId;
        this.orderInRound=orderInRound;
        this.numOfToken=numOfToken;
        this.score=score;
        this.inGame=inGame;
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

    public int getPlayerId() {
        return playerId;
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

    public void setOrderInRound(int orderInRound) {
        this.orderInRound = orderInRound;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void drawDice(){
    }

    @Override
    public String toString() {
        return "ID:"+this.getPlayerId()+"\nORDINE"+this.getOrderInRound()+"\nTOKEN"+this.getNumOfToken()+"\nSCORE:"+this.getScore()+"\ninGame?"+this.isInGame();
    }
}

