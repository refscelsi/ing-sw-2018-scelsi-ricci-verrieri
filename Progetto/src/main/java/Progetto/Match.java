package Progetto;

public class Match {

    private int id;
    private int numPlayer;

    public Match(int id, int numPlayer) {
        this.id=id;
        this.numPlayer=numPlayer;
    }

    public void startMatch(){
        System.out.print("l'id della partita è:"+id);
    }
}
