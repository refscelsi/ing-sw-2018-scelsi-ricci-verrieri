package Progetto;

public class RoundTrack {
    private Dice dadi[]= new Dice[10];

    public void setDadoRound(int round, Dice dado){
        dadi[round]=dado;
    }

    public Dice getDadoRound(int round){
        return dadi[round];
    }
}

