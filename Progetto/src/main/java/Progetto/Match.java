package Progetto;

import java.util.Scanner;
import java.util.Random;
import Progetto.Player;
import Progetto.Round;

public class Match {

    private int id;
    private int numPlayers;
    private int i=1 ;
    private Player[] players;
    private int playerPlaying;

    public Match(int id, int numPlayers) {
        this.id=id;
        this.numPlayers=numPlayers;
    }

    public void startMatch(){
        System.out.println("l'id della partita è: " + id);

        inizializePlayer();

        initializeTable();

        shuffleCard();

        for (i=0; i<10; i++) {
            Round round = new Round (playerPlaying);
            playerPlaying += 1;
            if (playerPlaying > numPlayers)
                playerPlaying = 1;
        }

        //ora devo aspettare che round mi dica di finire
        endMatch();
    }

    public void inizializePlayer(){
        int possibleNumbers[]={1,2,3,4};
        String nickname;
        System.out.println("\nCiao, quanti siete a giocare?");
        Scanner input= new Scanner(System.in);
        numPlayers=input.nextInt();
        players = new Player[numPlayers];
        while(i<=numPlayers){
            System.out.println("\nInserisci nickname "+i);
            nickname=input.nextLine();
            Random random = new Random();
            int order = random.nextInt(numPlayers); // order sarà uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
            if (possibleNumbers[order]!=0) {
                players[i] = new Player(nickname, possibleNumbers[order]);
                possibleNumbers[order]=0;         // metto 0 al posto di ogni numero che ho in possibleNumbers, così tengo traccia dei numeri che già ho assegnato ad altri giocatori
                i++;
            }
        }
        playerPlaying = 1;       // all'inizio del gioco il primo giocatore sarà il numero 1

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

    public int startRound(int firstPlayer) {

    }

    public void endMatch(){
       // qui calcolo il punteggio
    }
}
