
package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.model.toolCard.*;
import it.polimi.ing.sw.util.Constants;
//import it.polimi.ing.sw.server.Observable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;
import java.util.Collections;


public class Match implements Serializable {

    private int numPlayers=0, numRound=0;
    //array ordine players nel round
    private Player[] playersRound;
    // primo giocatore del round
    private Player firstPlayer;
    // indice dell'array playersRound -> giocatore che sta giocando il turno
    private Player playerPlaying;
    //bag della partita
    private Bag bag;
    //mazzo carte obiettivo
    private ArrayList<ObjectiveCard> publicObjectives;
    //mazzo toolCards
    private ArrayList<ToolCard> toolCards;
    //riserva della partita
    private DraftPool draftPool;
    //roundTrack
    private RoundTrack roundTrack;
    //array coi players della partita
    private ArrayList<Player> players;
    //array che contiente la classifica dei players
    private ArrayList<Player> ranking;
    //array di clientObserver che mi serve per notificare la ui dei cambiamenti avvenuti
    private ArrayList<RemotePlayer> remotePlayers;

    //hashmap con la corrispondenza player-remoteplayer
    private HashMap<Player,RemotePlayer> playerMap;


    public Match() {
        this.playerMap=new HashMap<Player,RemotePlayer>();
        this.players=new ArrayList<Player>();
        this.remotePlayers= new ArrayList<RemotePlayer>();
    }



    // metodi GETTERS

    //ritorna un giocatore con un certo nickname
    public Player getPlayer(String nickname){
        if(players.size()!=0){
            for(Player player: players){
                if(player.getNickname().equals(nickname)){
                    return player;
                }
            }
        }
        return null;
    }

    public DraftPool getDraftPool() {
        return draftPool;
    }

    public ArrayList<ObjectiveCard> getPublicObjectives() {
        return publicObjectives;
    }

    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }

    public Player getPlayerPlaying(){
        return playerPlaying;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }



    // metodi SETTERS


    public void setColorOfPawns() {
        Color[] colorOfPawns = new Color[4];
        colorOfPawns[0] = Color.PURPLE;
        colorOfPawns[1] = Color.RED;
        colorOfPawns[2] = Color.GREEN;
        colorOfPawns[3] = Color.BLUE;
        for (int i=0; i<numPlayers; i++)
            players.get(i).setColor(colorOfPawns[i]);
    }


    public void addRemotePlayer(RemotePlayer client){
        remotePlayers.add(client);
    }


    public Player login (String nickname,RemotePlayer remotePlayer) {
        numPlayers++;
        Player player= new Player(nickname);
        playerMap.put(player,remotePlayer);
        players.add(player);
        remotePlayers.add(remotePlayer);
        return player;
    }



    // metodi VARI per gestire la PARTITA (non il singolo turno)


    public void startMatch() throws ToolCardException, NotValidException, RemoteException {
        initializeTable();
        inizializePlayers();
        setColorOfPawns();
        onGameUpdate();
    }


    // inizializzo tutte le cose che riguardano il tavolo di gioco

    public void initializeTable() throws ToolCardException, NotValidException {
        bag = new Bag();
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        publicObjectives = objectiveCardDeck.drawObjectiveCard();
        ToolCards tool = new ToolCards();
        toolCards = tool.getToolCards();
        roundTrack = new RoundTrack();
    }


    // all'inizio della partita, inizializzo tutto ciò che riguarda i players

    public void inizializePlayers() {

        Collections.shuffle(players);
        playersRound = new Player[numPlayers*2];
        SchemeCardDeck schemeCardDeck = new SchemeCardDeck();
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        playerPlaying= null;

        for (int i=0; i<numPlayers; i++) {
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            players.get(i).setSchemesToChoose(schemeCardDeck.drawSchemeCard());
        }
    }


    // inizia un nuovo round: si estraggono i dadi, si stabilisce il primo giocatore e si
    // chiama il metodo che costruisce l'array playersRound. Se si sono già giocati 10 round, calcola il punteggio.

    public void startRound() {
        if (numRound>=Constants.NUM_ROUNDS)
            calculateRanking();
        else {
            draftPool = bag.draw(numPlayers);
            numRound++;
            firstPlayer=players.get(0);
            playerMap.get(firstPlayer).onSetActive();
            if (firstPlayer >= numPlayers)
                firstPlayer = 0;
            playerPlaying = firstPlayer;
            changePlayersRound(firstPlayer);
            //notifyNewRound(players.get(firstPlayer), draftPool);
        }
    }


    // all'inizio di ogni round, si costruisce l'array con l'ordine in cui giocheranno i players nel round,
    // cioè playersRound

    public void changePlayersRound (Player firstPlayer) {
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


    public void changePlayer () {
        playerPlaying=players.
        //notifyNextPlayer(players.get(playersRound[playerPlaying]));
    }


    // termina il turno e si chiama il metodo che inizia un nuovo turno

    public void endRound(){
        roundTrack.addDicesRound(draftPool);
        startRound();
    }


    // calcola il punteggio di un giocatore

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


    // calcola la classifica finale

    public void calculateRanking() {   // ritorna un array di players ordinato dal punteggio massimo al minimo
        int scores[] = new int[numPlayers];
        ranking = new ArrayList<Player>();
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
                    if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())> players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        max = j;
                    else if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())== players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        if (players.get(j).getNumOfToken()> players.get(max).getNumOfToken())
                            max = j;
                        else if (players.get(j).getNumOfToken()== players.get(max).getNumOfToken())
                            while (k<=numPlayers&&!found)
                                if (players.get(firstPlayer-k)== players.get(j)) {
                                    max = j;
                                    found = true;
                                }
                                else if (players.get(firstPlayer-k)== players.get(max))
                                    found = true;
                k++;
            }

            ranking.add(players.get(max));
        }
    }



    // metodi VARI per gestire il TURNO di un giocatore


    public void useDice (Box box, Dice dice, Scheme scheme) throws NotValidException, RemoteException {
        scheme.placeDice(box, dice);
        draftPool.removeDice(dice);
        for(RemotePlayer remotePlayer: remotePlayers){
            remotePlayer.onGameUpdate(this);
        }
    }


    // aggiornamenti alle view

    public void onGameUpdate() throws RemoteException {
        for(RemotePlayer remotePlayer: remotePlayers){
            remotePlayer.onGameUpdate(this);
        }
    }

    public ArrayList<Player> getOtherPlayers(String nickname){
        ArrayList<Player> otherPlayers=players;
        otherPlayers.remove(getPlayer(nickname));
        return otherPlayers;
    }


}



