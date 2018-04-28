package Progetto.Model;

import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Match {

    private int id;
    private int numPlayers;
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

        for (int i=0; i<10; i++) {
            Round round = new Round (playerPlaying);
            playerPlaying += 1;
            if (playerPlaying > numPlayers) //perchè =1??
                playerPlaying = 1;
        }

        //ora devo aspettare che round mi dica di finire
        endMatch();
        return null;
    }

    public void inizializePlayer(){

        // inizializzo i dati di un giocatore

        /*Integer[] array = new Integer[]{1, 2, 3, 4};
        List<Integer> possibleNumbers = Arrays.asList(array);
        System.out.println(possibleNumbers);
        Collections.shuffle(possibleNumbers);
        System.out.println(possibleNumbers);*/

        int possibleNumbers[]={1,2,3,4};
        int i=1;
        int order, oldOrder;
        //boolean find;
        String nickname;
        System.out.println("Ciao, quanti siete a giocare?");
        Scanner input= new Scanner(System.in);
        numPlayers=input.nextInt();
        players = new ArrayList<Player>();
        while(i<=numPlayers){
            Scanner input1= new Scanner(System.in);
            System.out.println("Inserisci nickname "+i);
            nickname=input1.nextLine();
            System.out.println(nickname);
            do {
                Random random = new Random();
                order = random.nextInt(numPlayers); // order sarà uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
                if (possibleNumbers[order]<=numPlayers&&possibleNumbers[order]!=0) {
                    System.out.println(possibleNumbers[order]);
                    Player player = new Player(nickname,possibleNumbers[order]);
                    players.add(player);
                    //players.add(new Player(nickname, possibleNumbers.get(j-1)));
                    i++;
                    System.out.println(player.getNickname());
                }
                oldOrder = possibleNumbers[order];
                possibleNumbers[order] = 0;
            } while (oldOrder>numPlayers||oldOrder==0);

            /*Random random = new Random();
            int order = random.nextInt(numPlayers); // order sar? uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
            do {
                if (order != 0) {
                    players.add(new Player(nickname, possibleNumbers[order]));
                    possibleNumbers[order] = 0;         // metto 0 al posto di ogni numero che ho in possibleNumbers, cos? tengo traccia dei numeri che gi? ho assegnato ad altri giocatori
                    i++;
                }
            } while (possibleNumbers[order]==0);*/
        }
        playerPlaying = 1; // all'inizio del gioco il primo giocatore sar? il numero 1

        // preparo tutte le carte, segnalini, ecc del giocatore


        i=0;
        System.out.println("ok1");
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        System.out.println("ok2");
        ArrayList<ObjectiveCard> privateObjectives = new ArrayList<ObjectiveCard>();
        System.out.println("ok3");
        privateObjectives = objectiveCardDeck.drawObjectiveCard(numPlayers, true);
        System.out.println("ok4");
        while (i<numPlayers) {
            System.out.println("ok10");
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            /* il giocatore sceglie il suo schema:
            players.get(i).setScheme(un certo schema che ha scelto); */
            System.out.println("ok5");
            //players.get(i).setNumOfToken(players.get(i).getScheme().getDifficulty());  ancora non si può eseguire
            System.out.println("ok6");
            System.out.println(players.get(i).getNickname());
            //System.out.println(players.get(i).getNumOfToken());            ancora non si può eseguire
            System.out.println(players.get(i).getOrderInRound());
            System.out.println(players.get(i).getPrivateObjective().getName());
            i++;
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
