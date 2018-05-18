package Progetto.Model;

import Progetto.Controller.Observable;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ObjectiveCard.*;
import Progetto.Model.ToolCard.ToolCard;
import Progetto.Model.*;

import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Match extends Observable{

    private static int last_id=0;
    private int id;
    private int numPlayers=0, numRound=0;
    private ArrayList<Player> players;
    private int[] playersRound;
    private int firstPlayer;   // primo giocatore del round. Se sono 4 giocatori può essere 0, 1, 2 o 3
    private int playerPlaying; // indice dell'array playersRound -> giocatore che sta giocando il turno
    private Bag bag;
    private ArrayList<ObjectiveCard> publicObjectives;
    private String np1;
    private State gameState;
    private DraftPool draftPool;

    public Match() {
        id = last_id;
        last_id++;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    /*public String startMatch(){

        System.out.println("l'id della partita ?: " + id);

        inizializePlayer();

        initializeTable();

        for (int i=0; i<10; i++) {
            gameState = State.START_ROUND;
            notifyObserver(gameState);
            Round round = new Round(firstPlayer,draftPool);
            firstPlayer += 1;
            if (firstPlayer >= numPlayers)
                firstPlayer = 0;
            gameState = State.CHANGE_ROUND;
            notifyObserver(gameState);
        }

        gameState = State.END_MATCH;
        notifyObserver(gameState);  

        //this.notifyObserver();
        //aspettare che round mi dica di finire
        endMatch();
        return null;
    }*/

    public void startRound() {
        draftPool = bag.draw(numPlayers);
        firstPlayer ++;
        numRound ++;
        if (firstPlayer >= numPlayers)
            firstPlayer = 0;
        playerPlaying=firstPlayer;
        changeRound(firstPlayer);
        notifyObserver(players.get(firstPlayer), draftPool);
    }

    public void createNewPlayer (String nickname) {
        numPlayers++;
        players.add(new Player(nickname));
    }

    public void inizializePlayer(){
        //Bisogna distinguere se si tratta di una partita locale o su un server

        //##iniz var
        SchemeCardDeck schemeCardDeck = new SchemeCardDeck();
        ArrayList<SchemeCard> schemes = new ArrayList<SchemeCard>();
        players = new ArrayList<Player>();
        playersRound = new int[numPlayers*2];
        int possibleNumbers[] = {1,2,3,4};
        int i = 0;
        int order, oldOrder;
        String nickname;
        String splayer = "Player";
        int k = 0;
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = new ArrayList<PrivateObjectiveCard>();
        privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        firstPlayer=-1;
        playerPlaying=-1;

        //#### Locale

        while(i<numPlayers){

            players.get(i).setPrivateObjective(privateObjectives.get(i));
            schemes = schemeCardDeck.drawSchemeCard();

            do {
                Random random = new Random();
                order = random.nextInt(numPlayers); // order sarà uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
                if (possibleNumbers[order]<=numPlayers&&possibleNumbers[order]!=0) {

                    // Ricky questa cosa dei 4 nomi non l'ho capita perché l'hai fatta così

                    if (k==0){
                        nickname=np1;
                    } else
                        nickname=splayer.concat(String.valueOf(k));
                    k++;
                    players.get(i).setOrderInRound(possibleNumbers[order]);
                    i++;
                }
                oldOrder = possibleNumbers[order];
                possibleNumbers[order] = 0;
            } while (oldOrder>numPlayers||oldOrder==0);

            notifyObserver(schemes,players.get(i));

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
        notifyObserver1(publicObjectives);

    }

    public void changePlayer () {
        playerPlaying++;
        notifyObserver(players.get(playersRound[playerPlaying]));
    }

    public void changeRound (int firstPlayer) {
        int i=0, j;
        while (i<numPlayers) {
            playersRound[i] = i + firstPlayer;
            if (playersRound[i] >= numPlayers)
                for (j = 0; i < numPlayers; i++)
                    playersRound[i]=j;
            i++;
        }
        for (i=0; i<numPlayers; i++)
            playersRound[i+numPlayers] = playersRound[numPlayers-i];
    }

    public void useDice (Box box, Dice dice, Player player) throws NotValidException {
        player.useDice(box, dice);
        draftPool.removeDice(dice);
        notifyObserver(dice);
    }

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

    /*public void useDice(Box box, Dice dice, Scheme scheme, DraftPool draftPool) throws NotValidException {
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
    }*/


    public void endMatch() {
        ArrayList<Player> ranking = getRanking();
        notifyObserver(ranking);
    }
}

