
package it.polimi.ing.sw.model;

import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
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

    private transient int numPlayers=0, numRound=0;
    //array ordine players nel round
    private transient Player[] playersRound;
    //indice playerPlaying in playersRound
    private transient int playersRoundIndex;
    // primo giocatore del round
    private transient Player firstPlayer;
    //giocatore che sta giocando il turno
    private transient Player playerPlaying;
    //bag della partita
    private transient Bag bag;
    //mazzo carte obiettivo
    private ArrayList<ObjectiveCard> publicObjectives;
    //mazzo di schemi per i giocatori
    private transient SchemeCardDeck schemeCardDeck;
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
    private transient ArrayList<RemotePlayer> remotePlayer;
    //hashmap con la corrispondenza player-remoteplayer
    private transient HashMap<Player,RemotePlayer> playerMap;




    public Match() {
        this.playerMap=new HashMap<Player,RemotePlayer>();
        this.players=new ArrayList<Player>();
        this.remotePlayer = new ArrayList<RemotePlayer>();
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

    public int getPlayersRoundIndex() {
        return playersRoundIndex;
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

    public ArrayList<Player> getPlayers(){return this.players;}


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


    //metodi per gestire il LOGIN

    //quando si loggano in almeno 2 setta un boolean a true
    public void login (String nickname, RemotePlayer remotePlayer) throws NotValidNicknameException, RemoteException, ToolCardException, NotValidException {
        if(playerMap.size()<Constants.MAX_PLAYERS) {
            if(checkNickname(nickname)) {
                Player player = new Player(nickname);
                player.setLogged(true);
                playerMap.put(player, remotePlayer);
                players.add(player);
                this.remotePlayer.add(remotePlayer);
                numPlayers++;
                System.out.println(numPlayers);
                //notifyChangement();
                notifyLogin(player);
                return;
            }
            else
                throw new NotValidNicknameException("il nickname scelto è già in uso, scegline un altro!");
        }
    }

    //controllo sui nickname
    public boolean checkNickname(String nickname){
        boolean check=true;
        for(Player player: players){
            if(player.getNickname().equals(nickname)){
                check=false;
            }
        }
        return check;
    }


    // metodi VARI per gestire la PARTITA (non il singolo turno)

    public void joinMatch() throws ToolCardException, RemoteException, NotValidException, NotValidPlayException {
        if(players.size()==2){
            //devo aggiungere timer e cazzi vari
            startMatch();
        }
        else
            return;
    }

    public void checkAllReady() throws RemoteException {
        boolean check=true;

        for(Player player: players){
            if(!player.getState().equals(PlayerState.READYTOPLAY)){
                check=false;
            }
        }
        if(check){
            startRound();
        }
    }

    public void startMatch() throws ToolCardException, NotValidException, RemoteException, NotValidPlayException {
        initializeTable();
        inizializePlayers();
        setColorOfPawns();
        notifyStartedMatch();
        System.out.println("i'm back bitch!");
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


    // all'inizio della partita, inizializzo per ogni player di players le carte schema da scegliere e le carte obiettivo privato

    public void inizializePlayers() {

        this.schemeCardDeck = new SchemeCardDeck();
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        playerPlaying= null;
        firstPlayer=null;

        for (int i=0; i<numPlayers; i++) {
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            players.get(i).setSchemesToChoose(schemeCardDeck.drawSchemeCard());
            players.get(i).setState(PlayerState.SCHEMETOCHOOSE);
        }

        this.schemeCardDeck=new SchemeCardDeck();
    }


    // inizia un nuovo round: si estraggono i dadi, si stabilisce il primo giocatore e si
    // chiama il metodo che costruisce l'array playersRound.
    //se è il primo round decido a caso i turni dei giocatori
    public void startRound() throws RemoteException {
        System.out.println("Inizia round in match");
        if(numRound==0){
            draftPool=bag.draw(numPlayers);
            Collections.shuffle(players);
            playersRound= new Player[numPlayers*2];
            firstPlayer=players.get(0);
            createRoundPlayers(0);
            playerPlaying=firstPlayer;
            playersRoundIndex=0;
            playerPlaying.setState(PlayerState.TURNSTARTED);
            for(Player player:players){
                if(!(player.equals(playerPlaying))){
                    player.setState(PlayerState.ENDEDTURN);
                }
            }
            notifyChangement();
            notifyStartTurn(firstPlayer);
            System.out.println("Ho notificato l'inizio del turno a un giocatore (da match)");
            System.out.println("i'm back bitch!");
        }
        else{
            draftPool=bag.draw(numPlayers);
            changePlayersRound(firstPlayer);
            firstPlayer=playersRound[0];
            playerPlaying=firstPlayer;
            playerPlaying.setState(PlayerState.TURNSTARTED);
            System.out.println("sono il giocatore "+ playerPlaying.getNickname()+ "nello stato: " + playerPlaying.getState().toString());
            for(Player player:players){
                if(!(player.equals(playerPlaying))){
                    player.setState(PlayerState.ENDEDTURN);
                }
            }
            notifyChangement();
            notifyStartTurn(firstPlayer);
        }
    }


    // all'inizio di ogni round, si costruisce l'array con l'ordine in cui giocheranno i players nel round,
    // cioè playersRound

    public void changePlayersRound (Player firstPlayer) {
        int first=players.indexOf(firstPlayer);
        //spasso il turno a destra
        if(first<numPlayers-1){
            firstPlayer=players.get(first+1);
            createRoundPlayers(first+1);
        }
        //passo il turno a destra , ma sono l'ultimo --> riparto dall'inizio
        if(first==numPlayers-1){
            firstPlayer=players.get(0);
            createRoundPlayers(0);
        }
    }

    public void createRoundPlayers(int firstPlayerIndex){
        int first=firstPlayerIndex;

        for(int i=0; i<numPlayers; i++){
            if(first<numPlayers){
                playersRound[i]=players.get(first);
                playersRound[((numPlayers*2)-1)-i]=players.get(first);
            }
            else if(first==numPlayers){
                first=0;
                playersRound[i]=players.get(first);
                playersRound[((numPlayers*2)-1)-i]=players.get(first);
            }
            first++;
        }

    }

    public void changePlayer () throws RemoteException {
        if(playersRoundIndex<(numPlayers*2)-1) {
            playersRoundIndex++;
            System.out.println("giocatore: "+ playerPlaying.getNickname()+ "\n stato:"+ playerPlaying.getState().toString());
            playerPlaying.setState(PlayerState.ENDEDTURN);
            System.out.println("giocatore: "+ playerPlaying.getNickname()+ "\n stato:"+ playerPlaying.getState().toString());
            notifyEndTurn(playerPlaying);
            playerPlaying = playersRound[playersRoundIndex];
            playerPlaying.setState(PlayerState.TURNSTARTED);
            System.out.println("giocatore: "+ playerPlaying.getNickname()+ "\n stato:"+ playerPlaying.getState().toString());
            notifyStartTurn(playerPlaying);
        }
        else if(playersRoundIndex==(numPlayers*2)-1){
            playersRoundIndex=0;
            endRound();
        }
    }


    // termina il turno e si chiama il metodo che inizia un nuovo turno

    public void endRound() throws RemoteException {
        numRound++;
        roundTrack.addDicesRound(draftPool);

        if(numRound<Constants.NUM_ROUNDS){
            startRound();
        }
        else if(numRound==Constants.NUM_ROUNDS) {
            //notifyChangement(); così vedono la roundtrack aggiornata e poi tutta la roundtrack
            System.out.println("1");
            calculateRanking();
            //da qua dove vado?? quando notifico la classifica e l'ultimo round??
            System.out.println(ranking.get(0).getNickname() + ranking.get(0).getScore());
            System.out.println(ranking.get(1).getNickname() + ranking.get(1).getScore());
        }
    }


    // calcola il punteggio di un giocatore

    public int calculateScore(Player player) {
        int score = 0;
        for (int i=0; i<3; i++) {
            score = score + publicObjectives.get(i).calculateScore(player.getScheme());
            System.out.println(score);
        }
        score = score + player.getPrivateObjective().calculateScore(player.getScheme());
        System.out.println(score);
        score = score + player.getNumOfToken();
        System.out.println(score);
        score = score - player.getScheme().countFreeBoxes();
        System.out.println(score);
        player.setScore(score);
        System.out.println(score);
        System.out.println("2" + player.getScore());
        return score;
    }


    // calcola la classifica finale

    public void calculateRanking() {   // ritorna un array di players ordinato dal punteggio massimo al minimo
        int scores[] = new int[numPlayers];
        ranking = new ArrayList<Player>();
        ArrayList<Player> tempPlayers = players;
        int i, j, max, k=1;
        boolean found = false;
        for (i=0; i<numPlayers; i++) {
            scores[i] = calculateScore(players.get(i));
        }
        for(i=0; i<numPlayers; i++) {
            max = 0;
            for(j=1; j<tempPlayers.size(); j++) {
                if (scores[j] > scores[max]) {
                    max = j;
                    System.out.println("2");
                } else {
                    if (scores[j] == scores[max]) {
                        System.out.println("3");
                        if (tempPlayers.get(j).getPrivateObjective().calculateScore(tempPlayers.get(j).getScheme()) > tempPlayers.get(max).getPrivateObjective().calculateScore(tempPlayers.get(max).getScheme())) {
                            max = j;
                            System.out.println("4");
                        } else {
                            if (tempPlayers.get(j).getPrivateObjective().calculateScore(tempPlayers.get(j).getScheme()) == tempPlayers.get(max).getPrivateObjective().calculateScore(tempPlayers.get(max).getScheme())) {
                                System.out.println("5");
                                if (tempPlayers.get(j).getNumOfToken() > tempPlayers.get(max).getNumOfToken()) {
                                    max = j;
                                    System.out.println("6");
                                } else {
                                    if (tempPlayers.get(j).getNumOfToken() == tempPlayers.get(max).getNumOfToken()) {
                                        int num = 0;
                                        System.out.println("7");
                                        while (num <= numPlayers && !found) {
                                            System.out.println("8");
                                            if (playersRound[num] == tempPlayers.get(j)) {
                                                System.out.println("9");
                                                max = j;
                                                found = true;
                                            } else {
                                                System.out.println("10");
                                                if (playersRound[num] == tempPlayers.get(max)) {
                                                    found = true;
                                                }
                                            }
                                            num++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            ranking.add(tempPlayers.get(max));
            tempPlayers.remove(max);
            System.out.println("11");
        }
    }



    // metodi VARI per gestire il TURNO di un giocatore


    public void useDice (Player player, int indexOfDiceInDraftpool, int row, int col) throws NotValidException {
        player.getScheme().placeDice(row,col,draftPool.getDice(indexOfDiceInDraftpool));
        draftPool.removeDice(draftPool.getDice(indexOfDiceInDraftpool));
    }


    public void chooseScheme(Player player, int id) throws RemoteException {
        player.setScheme(schemeCardDeck.getSchemeWithId(id));
        player.setNumOfToken(schemeCardDeck.getSchemeWithId(id).getDifficulty());
        player.setState(PlayerState.READYTOPLAY);
        System.out.println("Ho scelto schema nel match");
        playerMap.get(player).onSuccess("ok hai scelto bene lo schema ");
        System.out.println("Notifico schema dal match");
    }

    //toolCard

    //metodi ausiliari

    public boolean checkToken(Player player, int idToolCard) throws NotValidPlayException {
        if(player.getNumOfToken()>=findToolCard(idToolCard).getNumOfTokens()){
            return true;
        }
        else
            throw new NotValidPlayException("non hai il numero di segnalini favore necessari");
    }

    public ToolCard findToolCard(int id){
        for(ToolCard toolCard: toolCards){
            if(toolCard.getId()==id){
                return toolCard;
            }
        }
        return null;
    }


    public Boolean getIfFirstTurn(Player player) {
        for (int i=0; i<playersRoundIndex; i++) {
            if (playersRound[i]==player)
                return false;
        }
        return true;
    }


    //metodi delle carte


    public void useToolCard (Player player, int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException, NotValidPlayException {
        if(checkToken(player,id)) {
            findToolCard(id).execute(draftPool, roundTrack, player.getScheme(), playersRound, bag, dice, operation, sourceRow, sourceCol, destRow, destCol);
            player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
            findToolCard(id).incrementNumOfTokens();
        }
    }


    public void usedToolCard (Player player, int id) throws RemoteException {
        notifyChangement();
        switch (id) {
            case 4:
            case 6:
            case 11:
            case 12: {
                playerMap.get(player).onOtherInfoToolCard(id);
                break;
            }
            default: {
                notifyStartTurn(player);
                break;
            }
        }
    }



    // aggiornamenti alle view

    public void notifyChangement() throws RemoteException {
        for(RemotePlayer remotePlayer : remotePlayer){
            remotePlayer.onGameUpdate(this);
        }
    }

    public ArrayList<Player> getOtherPlayers(String nickname){
        ArrayList<Player> otherPlayers=players;
        otherPlayers.remove(getPlayer(nickname));
        return otherPlayers;
    }

    public void notifyLogin(Player player) throws RemoteException {
        playerMap.get(player).onPlayerLogged();
    }

    private void notifyStartedMatch() throws RemoteException, NotValidPlayException {
        for(RemotePlayer remotePlayer : remotePlayer){
            remotePlayer.onSchemeToChoose(this);
        }
    }

    private void notifyStartTurn(Player player) throws RemoteException {
        playerMap.get(player).onSetPlaying();
    }

    public void notifyEndTurn(Player player) throws RemoteException {
        playerMap.get(player).onTurnEnd();
    }

    public void notifySucces(String message) throws RemoteException{
        for(RemotePlayer remotePlayer : this.remotePlayer){
            remotePlayer.onSuccess(message);
        }
    }

}



