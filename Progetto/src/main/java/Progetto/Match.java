package Progetto;

import java.util.Scanner;
import java.util.Random;
import java.lang.reflect.Array;
import java.util.*;

public class Match {

    private int id;
    private int numPlayers;
    private int i=1 ;
    private ArrayList<Player> players;
    private int playerPlaying;
    private Bag bag;
    private ArrayList<ObjectiveCard> publicObjectives;

    public Match(int id /*, int numPlayers */) {   /* Match non può avere un costruttore che prende in input anche il numero dei giocatori, perché altrimenti la classe Match non può essere creata finché non si sa il numero dei giocatori */
        this.id=id;
        /* this.numPlayers=numPlayers; */
    }

    public String startMatch(){
        System.out.println("l'id della partita ?: " + id);

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
        return null;
    }

    public void inizializePlayer(){

        // inizializzo i dati di un giocatore

        int possibleNumbers[]={1,2,3,4};
        String nickname;
        System.out.println("\nCiao, quanti siete a giocare?");
        Scanner input= new Scanner(System.in);
        numPlayers=input.nextInt();
        players = new ArrayList<Player>();
        while(i<=numPlayers){
            System.out.println("\nInserisci nickname "+i);
            nickname=input.nextLine();
            Random random = new Random();
            int order = random.nextInt(numPlayers); // order sar? uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
            if (possibleNumbers[order]!=0) {
                players.add(new Player(nickname, possibleNumbers[order]));
                possibleNumbers[order]=0;         // metto 0 al posto di ogni numero che ho in possibleNumbers, cos? tengo traccia dei numeri che gi? ho assegnato ad altri giocatori
                i++;
            }
        }
        playerPlaying = 1; // all'inizio del gioco il primo giocatore sar? il numero 1

        // preparo tutte le carte, segnalini, ecc del giocatore

        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        ArrayList<ObjectiveCard> privateObjectives = new ArrayList<ObjectiveCard>();
        privateObjectives = objectiveCardDeck.drawObjectiveCard(numPlayers, true);
        while (i<numPlayers) {
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            /* il giocatore sceglie il suo schema:
            players.get(i).setScheme(un certo schema che ha scelto); */
            players.get(i).setNumOfToken(players.get(i).getScheme().getDifficulty());
        }

    }

    public void initializeTable(){
        //ripescare dal db le carte
        //caricarle in adeguate strutture dati
        //costruire le coppie di schemi e le carte scheme --> chiamo metodo createSchemeCard()

        // creo il sacchetto dei dadi ed estraggo le 3 carte obiettivo pubblico

        bag = new Bag();
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        publicObjectives = new ArrayList<ObjectiveCard>();
        publicObjectives = objectiveCardDeck.drawObjectiveCard(3, false);

    }

    public void shuffleCard(){
        //distribuire in modo casuale le carte ai giocatori e sul tavolo

        //controllare che i conti tornino (difficolt? carte ecc)
    }

    /*public int startRound(int firstPlayer) {
    }*/

    public void endMatch(){
       // qui calcolo il punteggio
    }
}
