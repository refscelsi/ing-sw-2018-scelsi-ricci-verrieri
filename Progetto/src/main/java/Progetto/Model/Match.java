package Progetto.Model;

import Progetto.Controller.Observable;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ObjectiveCard.*;
import Progetto.Model.ToolCard.ToolCard;

import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Match extends Observable{

    private String id;
    private int numPlayers;
    private ArrayList<Player> players;
    private int firstPlayer;    // primo giocatore del round. Se sono 4 giocatori può essere 0, 1, 2 o 3
    private Bag bag;
    private ArrayList<ObjectiveCard> publicObjectives;
    private String np1;

    public Match(String id , int numPlayers , String np1) {
        this.id=id;
        this.numPlayers=numPlayers;
        this.np1=np1;
    }

    public String startMatch(){
        System.out.println("l'id della partita ?: " + id);

        inizializePlayer();

        initializeTable();

        shuffleCard();

        //inizializzare un array con l'ordine dei player fino al decimo round e scorrerlo per svolgere i turni
        for (int i=0; i<10; i++) {
            Round round = new Round (firstPlayer);
            firstPlayer += 1;
            if (firstPlayer >= numPlayers)
                firstPlayer = 0;
        }
        this.notifyObserver();
        //aspettare che round mi dica di finire
        endMatch();
        return null;
    }

    public void inizializePlayer(){
        //Bisogna distinguere se si tratta di una partita locale o su un server

        //##iniz var
        players=new ArrayList<Player>();
        int possibleNumbers[]={1,2,3,4};
        int i=1;
        int order, oldOrder;
        //boolean find;
        String nickname;
        String splayer= "Player";
        int k = 0;

        //#### Locale

        while(i<=numPlayers){
            do {
                Random random = new Random();
                order = random.nextInt(numPlayers); // order sarà uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
                if (possibleNumbers[order]<=numPlayers&&possibleNumbers[order]!=0) {
                    System.out.println(possibleNumbers[order]);
                    if (k==0){
                        nickname=np1;
                    } else
                        nickname=splayer.concat(String.valueOf(k));
                    k++;
                    System.out.println(nickname);
                    //players.add(new Player(nickname, possibleNumbers.get(j-1)));
                    players.add(new Player(nickname,possibleNumbers[order]));
                    i++;
                }
                oldOrder = possibleNumbers[order];
                possibleNumbers[order] = 0;
            } while (oldOrder>numPlayers||oldOrder==0);

        }

        //# svuoto la ram
        splayer=null;
        nickname=null;
        possibleNumbers= null;

        //#### fine locale

        //#### server

        //da implementare

        //#### fine server

        //############################################################################### cose che boh

        firstPlayer = 0; // all'inizio del gioco il primo giocatore sar? il numero 0

        // preparo tutte le carte, segnalini, ecc del giocatore

        i=0;
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = new ArrayList<PrivateObjectiveCard>();
        privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        while (i<numPlayers) {
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            // il giocatore sceglie il suo schema:
            //players.get(i).setScheme(un certo schema che ha scelto);
            //players.get(i).setNumOfToken(players.get(i).getScheme().getDifficulty());  ancora non si può eseguire
            System.out.println(players.get(i).getNickname());
            //System.out.println(players.get(i).getNumOfToken());            ancora non si può eseguire
            System.out.println(players.get(i).getOrderInRound());
            System.out.println(players.get(i).getPrivateObjective().getColor());
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
        publicObjectives = objectiveCardDeck.drawObjectiveCard();
        System.out.println(publicObjectives.size());

        bag.setDices();
        int i=bag.getSize();
        System.out.println(i);

        System.out.println("Colore"+bag.draw(4));
        System.out.println(bag.getSize());

        Dice dice= new Dice();
        System.out.println(dice.throwDice());

    }

    public void shuffleCard(){
        //distribuire in modo casuale le carte ai giocatori e sul tavolo

        //controllare che i conti tornino (difficolt? carte ecc)
    }

    /*public int startRound(int firstPlayer) {
    }*/

    public ArrayList<Player> getRanking() {   // ritorna un array di giocatori ordinato dal punteggio massimo al minimo
        int scores[] = new int[numPlayers];
        ArrayList<Player> ranking = new ArrayList<Player>();
        int i, j, max, k=1;
        boolean found = false;
        for (i=0; i<numPlayers; i++) {
            scores[i] = calculateScore(players.get(i));
        }
        for(i=0; i<numPlayers; i++) {
            max = 0;
            for(j=1; j<numPlayers; j++) {
                if (scores[j] > scores[max])
                    max = j;
                else if (scores[j]==scores[max])
                    if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())>players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        max = j;
                    else if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())==players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        if (players.get(j).getNumOfToken()>players.get(max).getNumOfToken())
                            max = j;
                        else if (players.get(j).getNumOfToken()==players.get(max).getNumOfToken())
                            while (k<=numPlayers&&!found)
                                if (players.get(firstPlayer-k)==players.get(j)) {
                                    max = j;
                                    found = true;
                                }
                                else if (players.get(firstPlayer-k)==players.get(max))
                                    found = true;
                                k++;
            }

            ranking.add(players.get(max));
        }

        return ranking;
    }

    public int calculateScore(Player player) {
        int score = 0;
        for (int i=0; i<3; i++)
            score = score + publicObjectives.get(i).calculateScore();
        score = score + player.getPrivateObjective().calculateScore(player.getScheme());
        score = score + player.getNumOfToken();
        score = score - player.getScheme().countFreeBoxes();
        player.setScore(score);
        return score;
    }

    public void moveToNext(){
    }

    public void useDice(Box box, Dice dice, Scheme scheme, DraftPool draftPool) throws NotValidException {
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

    public void useToolCard(ToolCard toolCard) throws ToolCardException, NotValidException { //il controller passa la tool che mi serve e che creo ogni volta che devo usare
        toolCard.execute();
    }


    public void endMatch() {
       // qui calcolo il punteggio
    }
}
