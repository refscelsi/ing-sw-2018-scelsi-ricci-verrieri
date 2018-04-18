package Progetto;

import java.util.Scanner;

public class Match {

    private int id;
    private int numPlayer;
    private int i=1 ;

    public Match(int id, int numPlayer) {
        this.id=id;
        this.numPlayer=numPlayer;
    }

    public void startMatch(){
        System.out.print("l'id della partita è:"+id);

        inizializePlayer();

        initializeTable();

        shuffleCard();

        Round r = new Round();

        //ora devo aspettare che round mi dica di finire
        endMatch();
    }

    public void inizializePlayer(){
        System.out.println("\nCiao, quanti siete a giocare?");
        Scanner input= new Scanner(System.in);
        numPlayer=input.nextInt();
        while(i<=numPlayer){
            Player player1=new Player(i++,i,i,0,false);
            String s=player1.toString();
            System.out.println(s);
        }
    }

    public void initializeTable(){
        //ripescare dal db le carte
        //caricarle in adeguate strutture dati

        //costruire le coppie di schemi e le carte scheme --> chiamo metodo createSchemeCard()

    }

    public void shuffleCard(){
        //distribuire in modo casuale le carte ai giocatori e sul tavolo

        //controllare che i conti tornino (difficoltà carte ecc)
    }

    public void endMatch(){
       // qui calcolo il punteggio
    }
}
