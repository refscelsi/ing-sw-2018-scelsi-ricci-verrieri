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

        initializeTable();

        shuffleCard();

        Round r = new Round();

        //ora devo aspettare che round mi dica di finire
        endMatch();
    }

    public void initializeTable(){
        //ripescare dal db le carte
        //caricarle in adeguate strutture dati

        //costruire le coppie di schemi per le carte schema


    }

    public void shuffleCard(){
        //distribuire in modo casuale le carte ai giocatori e sul tavolo

        //controllare che i conti tornino (difficoltà carte ecc)
    }

    public void endMatch(){
       // qui calcolo il punteggio
    }
}
