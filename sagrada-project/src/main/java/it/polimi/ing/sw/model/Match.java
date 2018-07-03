
package it.polimi.ing.sw.model;

import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.model.toolCard.*;
import it.polimi.ing.sw.util.Constants;
//import it.polimi.ing.sw.server.Observable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;
import java.util.Collections;

import static java.lang.System.clearProperty;
import static java.lang.System.exit;


public class Match implements Serializable{

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
    //array di persone che si sono loggate ma sono in attesa
    private transient ArrayList<Player> playersLogged;
    //controllo se la partita è in fase di avvio o meno
    private boolean matchStarted=false;
    //numero giocatori effettivamente giocanti
    private int numPlayersPlaying=0;




    public Match() {
        this.playerMap=new HashMap<Player,RemotePlayer>();
        this.players=new ArrayList<Player>();
        this.remotePlayer = new ArrayList<RemotePlayer>();
        this.playersLogged=new ArrayList<Player>();
    }



    // metodi GETTERS

    //ritorna un giocatore loggato con un certo nickname
    public Player getPlayerLogged(String nickname){
        if(playersLogged.size()!=0){
            for(Player player: playersLogged){
                if(player.getNickname().equals(nickname)){
                    return player;
                }
            }
        }
        return null;
    }

    //ritorna un giocatore della partita con un certo nickname
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

    public ArrayList<Player> getOtherPlayers(String nickname){
        ArrayList<Player> otherPlayers=players;
        otherPlayers.remove(getPlayer(nickname));
        return otherPlayers;
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
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setToolCards(ArrayList<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    public void setPublicObjectives(ArrayList<ObjectiveCard> publicObjectives) {
        this.publicObjectives = publicObjectives;
    }

    public void setRanking(ArrayList<Player> ranking) {
        this.ranking = ranking;
    }

    //metodi per gestire il LOGIN

    //quando si loggano in almeno 2 setta un boolean a true
    public void login (String nickname, RemotePlayer remotePlayer) throws NotValidNicknameException, NotPossibleConnectionException {
        if(!checkReconnection(nickname) ) {
            if(playerMap.size()<Constants.MAX_PLAYERS){
                if(!matchStarted) {
                    if (checkNickname(nickname)) {
                        Player player = new Player(nickname);
                        player.setLogged(true);
                        playerMap.put(player, remotePlayer);
                        players.add(player);
                        this.remotePlayer.add(remotePlayer);
                        numPlayers++;
                        numPlayersPlaying++;
                        System.out.println(numPlayers);
                        return;
                    } else
                        throw new NotValidNicknameException("il nickname scelto è già in uso, scegline un altro!");
                }else
                    throw new NotPossibleConnectionException("la partita è già iniziata");
            }
            else {
                Player player = new Player(nickname);
                player.setLogged(true);
                player.setState(PlayerState.OFFLINE);
                System.out.println(player.getNickname());
                playersLogged.add(player);
                throw new NotPossibleConnectionException("la partita è piena");
            }
        }
        else
            System.out.println("bentornato coglione");
            numPlayersPlaying++;
            getPlayer(nickname).setState(PlayerState.INIZIALIZED);
    }

    ////////////////LOGIN

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

    public boolean checkReconnection(String nickname){
        for(Player player: players){
            if(player.getNickname().equals(nickname) && player.getState().equals(PlayerState.OFFLINE)) {
                return true;
            }
        }
        return false;
    }




    // metodi VARI per gestire la PARTITA (non il singolo turno)

    public synchronized void joinMatch() throws RemoteException, NotValidPlayException {
        if(!matchStarted){
            if(players.size()==Constants.MAX_PLAYERS){
                matchStarted=true;
                startMatch();
            }
            else if(players.size()==2){
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (players.size() >= 2) {
                            try {
                                matchStarted=true;
                                startMatch();
                            } catch ( RemoteException e ) {
                                exit(0);
                            } catch ( NotValidPlayException e ) {
                                try {
                                    notifyNotValidPlayException(playerPlaying, e.getMessage());
                                } catch ( RemoteException e1 ) {
                                    exit(0);
                                }
                            }
                        }
                    }
                }, 10000);
            }
        }

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

    public void startMatch() throws RemoteException, NotValidPlayException {
        initializeTable();
        inizializePlayers();
        setColorOfPawns();
        notifyStartedMatch();
    }

    // inizializzo tutte le cose che riguardano il tavolo di gioco

    public void initializeTable() {
        bag = new Bag();
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        publicObjectives = objectiveCardDeck.drawObjectiveCard();
        ToolCards tool = new ToolCards();
        toolCards = tool.getToolCards();
        roundTrack = new RoundTrack();
        draftPool=new DraftPool();
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
            playerPlaying.setState(PlayerState.ENDEDTURN);
            notifyEndTurn(playerPlaying);
            if(playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)){
                System.out.println("quaalcuno è off");
                playersRoundIndex++;
            }
            else if(playersRound.length==8 && playersRound[playersRoundIndex+1].getState().equals(PlayerState.OFFLINE)){
                playersRoundIndex= playersRoundIndex+2;
            }
            else {
                playerPlaying = playersRound[playersRoundIndex];
                playerPlaying.setState(PlayerState.TURNSTARTED);
                System.out.println("giocatore: " + playerPlaying.getNickname() + "\n stato:" + playerPlaying.getState().toString());
                notifyStartTurn(playerPlaying);
            }

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
            calculateRanking();
            notifyGameEnd();
        }
    }


    // calcola il punteggio di un giocatore

    public int calculateScore(Player player) {
        int score = 0;
        for (int i=0; i<3; i++) {
            score = score + publicObjectives.get(i).calculateScore(player.getScheme());
        }
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
        ArrayList<Player> tempPlayers = new ArrayList<Player>(players);
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
                } else {
                    if (scores[j] == scores[max]) {
                        if (tempPlayers.get(j).getPrivateObjective().calculateScore(tempPlayers.get(j).getScheme()) > tempPlayers.get(max).getPrivateObjective().calculateScore(tempPlayers.get(max).getScheme())) {
                            max = j;
                        } else {
                            if (tempPlayers.get(j).getPrivateObjective().calculateScore(tempPlayers.get(j).getScheme()) == tempPlayers.get(max).getPrivateObjective().calculateScore(tempPlayers.get(max).getScheme())) {
                                if (tempPlayers.get(j).getNumOfToken() > tempPlayers.get(max).getNumOfToken()) {
                                    max = j;
                                } else {
                                    if (tempPlayers.get(j).getNumOfToken() == tempPlayers.get(max).getNumOfToken()) {
                                        int num = 0;
                                        while (num <= numPlayers && !found) {
                                            if (playersRound[num] == tempPlayers.get(j)) {
                                                max = j;
                                                found = true;
                                            } else {
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
        }
    }



    // metodi VARI per gestire il TURNO di un giocatore


    public void useDice (Player player, int indexOfDiceInDraftpool, int row, int col) throws NotValidException, RemoteException {
        player.getScheme().placeDice(row,col,draftPool.getDice(indexOfDiceInDraftpool));
        draftPool.removeDice(draftPool.getDice(indexOfDiceInDraftpool));
        changePlayerStateAfterUseDice(player);
    }

    public void changePlayerStateAfterUseDice (Player player) throws RemoteException {
        switch (player.getState()) {
            case USEDTOOLCARD: {
                notifyChangement();
                changePlayer();
                break;
            }
            case TURNSTARTED: {
                player.setState(PlayerState.USEDDICE);
                notifyChangement();
                notifyStartTurn(player);
                break;
            }
            default:
                break;
        }
    }


    public void chooseScheme(Player player, int id) throws RemoteException {
        player.setScheme(schemeCardDeck.getSchemeWithId(id));
        player.setNumOfToken(schemeCardDeck.getSchemeWithId(id).getDifficulty());
        player.setState(PlayerState.READYTOPLAY);
        playerMap.get(player).onSuccess("Schema scelto correttamente. Attendi l'inizio del primo turno.");
    }

    //toolCard

    //metodi ausiliari

    public boolean checkToken(Player player, int idToolCard) throws NotValidPlayException {
        if (!player.getState().equals(PlayerState.TURNSTARTED)&&!player.getState().equals(PlayerState.USEDDICE))
            return true;
        if(player.getNumOfToken()>=findToolCard(idToolCard).getNumOfTokens()){
            return true;
        }
        else
            throw new NotValidPlayException("I tuoi segnalini favore non sono sufficienti.");
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


    public void useToolCard (Player player, int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException, NotValidPlayException, RemoteException {
        if(checkToken(player,id)) {
            findToolCard(id).execute(draftPool, roundTrack, player.getScheme(), playersRound, bag, dice, operation, sourceRow, sourceCol, destRow, destCol);
            switch (id) {
                case 4: {
                    if (!findToolCard(id).getFirstExecutionDone()) {
                        player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                        findToolCard(id).incrementNumOfTokens();
                    }
                    break;
                }
                case 11:
                case 12: {
                    if (findToolCard(id).getFirstExecutionDone()) {
                        player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                        findToolCard(id).incrementNumOfTokens();
                    }
                    break;
                }
                default: {
                    player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                    findToolCard(id).incrementNumOfTokens();
                    break;
                }
            }
        }
        changePlayerStateAfterToolCard(player, id);
    }


    public void changePlayerStateAfterToolCard(Player player, int id) throws NotValidPlayException, RemoteException {
        switch(id) {

            // carte che si possono utilizzare in qualsiasi momento
            case 1:
            case 2:
            case 3:
            case 5:
            case 10: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                } else {
                    if (player.getState().equals(PlayerState.USEDDICE)) {
                        notifyChangement();
                        changePlayer();
                        break;
                    }
                }
                break;
            }

            // carte che si possono utilizzare in qualsiasi momento ma si eseguono in 2 step
            case 4:
            case 12: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.FIRSTSTEPTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                else {
                    if (player.getState().equals(PlayerState.FIRSTSTEPTOOLCARD)) {
                        player.setState(PlayerState.USEDTOOLCARD);
                        notifyUsedToolCard(player, id);
                        break;
                    }
                    else {
                        if (player.getState().equals(PlayerState.USEDDICE)) {
                            player.setState(PlayerState.USEDDICETOOLCARD);
                            notifyUsedToolCard(player, id);
                            break;
                        }
                        else {
                            if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                                notifyChangement();
                                changePlayer();
                                break;
                            }
                        }
                    }
                }
                break;
            }


            // carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
            case 6: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                break;
            }

            // carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
            case 11: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.USEDDICETOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                else {
                    if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                        notifyChangement();
                        changePlayer();
                        break;
                    }
                }
                break;
            }


            // carta che può essere utilizzata solo durante il secondo turno e prima di scegliere il secondo dado
            case 7: {
                if (player.getState().equals(PlayerState.TURNSTARTED)&&!getIfFirstTurn(player)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                break;
            }

            // carta che può essere utilizzata solo durante il primo turno
            case 8: {
                if (player.getState().equals(PlayerState.TURNSTARTED)&&getIfFirstTurn(player)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;

                } else {
                    if (player.getState().equals(PlayerState.USEDDICE)&&getIfFirstTurn(player)) {
                        notifyChangement();
                        changePlayer();
                        break;
                    }
                }
                break;
            }


            // carta che può essere utilizzata solo se non si è già piazzato un dado
            case 9: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    notifyChangement();
                    changePlayer();
                    break;
                }
                break;
            }
            default:
                break;
        }
    }


    public void notifyUsedToolCard (Player player, int id) throws RemoteException {
        switch (id) {
            case 4: {
                if (findToolCard(id).getFirstExecutionDone())
                    playerMap.get(player).onOtherInfoToolCard(id);
                else
                    notifyChangement();
                    notifyStartTurn(player);
                break;
            }

            case 6: {
                notifyChangement();
                playerMap.get(player).onOtherInfoToolCard(id);
                break;
            }
            case 11:
            case 12: {
                notifyChangement();
                if (findToolCard(id).getFirstExecutionDone())
                    playerMap.get(player).onOtherInfoToolCard(id);
                else
                    notifyStartTurn(player);
                break;
            }
            default: {
                notifyChangement();
                notifyStartTurn(player);
                break;
            }
        }
    }



    public void exitPlayer(Player player) throws RemoteException {
        player.setState(PlayerState.OFFLINE);
        numPlayersPlaying=numPlayersPlaying-1;
        System.out.println(players.size());
        System.out.println(numPlayersPlaying);
        if(numPlayersPlaying==1){
            calculateRanking();
            notifyGameEnd();
            return;
        }
        else if(playerPlaying.equals(player)){
            if(playersRoundIndex<numPlayers*2-1){
                playersRoundIndex++;
                if(!playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                    playerPlaying = playersRound[playersRoundIndex];
                    playerPlaying.setState(PlayerState.TURNSTARTED);
                    System.out.println("giocatore: " + playerPlaying.getNickname() + "\n stato:" + playerPlaying.getState().toString());
                    notifyStartTurn(playerPlaying);
                }
            }
            else if(playersRoundIndex == ((numPlayers * 2) - 1)){
                playersRoundIndex = 0;
                endRound();
            }
        }
    }

    // aggiornamenti alle view

    public void notifyChangement() throws RemoteException {
        for(Player player: players){
            try {
                playerMap.get(player).onGameUpdate(this.matchClone());
            }catch ( RemoteException e){
                exitPlayer(player);
            }

        }
    }


    private void notifyStartedMatch() throws RemoteException, NotValidPlayException {
        for(Player player: players){
            try {
                playerMap.get(player).onSchemeToChoose(this.matchClone());
            }catch ( RemoteException e){
                exitPlayer(player);
            }

        }
    }

    private void notifyStartTurn(Player player) throws RemoteException {
        try{
        playerMap.get(player).onSetPlaying();
        }
        catch ( RemoteException e ){
            exitPlayer(player);
        }
    }

    public void notifyEndTurn(Player player) throws RemoteException {
        try{
            playerMap.get(player).onTurnEnd();
        }
        catch ( RemoteException e ){
            exitPlayer(player);
        }
    }

    public void notifyGameEnd() throws RemoteException {
        for(Player player: players){
            try {
                playerMap.get(player).onGameEnd(this.matchClone());
            }catch ( RemoteException e){
                exitPlayer(player);
            }

        }
    }

    public void notifySucces(String message) throws RemoteException{
        for(Player player: players){
            try {
                playerMap.get(player).onSuccess(message);
            }catch ( RemoteException e){
                exitPlayer(player);
            }
        }
    }

    public void notifyNotValidUseDiceException(Player player, String message) throws RemoteException{
        try{
            playerMap.get(player).onNotValidUseDiceException(message);
        }
        catch ( RemoteException e ){
            exitPlayer(player);
        }
    }

    public void notifyNotValidToolCardException(Player player, int id, String message) throws RemoteException{
        try{
            playerMap.get(player).onNotValidToolCardException(id,message);
        }
        catch ( RemoteException e ){
            exitPlayer(player);
        }
    }

    public void notifyNotValidPlayException(Player player, String message) throws RemoteException{
        try{
            playerMap.get(player).onNotValidPlayException(message);
        }
        catch ( RemoteException e ){
            exitPlayer(player);
        }
    }

    public void notifyNotValidNicknameException(RemotePlayer player, String message) throws RemoteException{
        player.onNotValidNicknameException(message);
    }

    public void notifyNotPossibleConnectionException(RemotePlayer player, String message) throws RemoteException{
        player.onNotPossibleConnectionException(message);
    }


    public Match matchClone(){
        Match matchClone=new Match();
        matchClone.setDraftPool(draftPool.cloneDraftPool());
        matchClone.setRoundTrack(roundTrack.cloneRoundTrack());
        ArrayList<Player> playersClone=new ArrayList<Player>();
        for(Player player:players){
            playersClone.add(player.playerClone());
        }
        ArrayList<ToolCard> toolCardsClone=new ArrayList<ToolCard>();
        for(int i=0; i<toolCards.size();i++){
            ToolCard toolCardCopy=toolCards.get(i);
            toolCardsClone.add(toolCardCopy);
        }
        matchClone.setToolCards(toolCardsClone);
        matchClone.setPlayers(playersClone);
        matchClone.setPublicObjectives(publicObjectives);
        matchClone.setRanking(this.getRanking());
        return matchClone;
    }

}



