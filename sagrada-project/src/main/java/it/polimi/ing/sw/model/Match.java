
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

    private int numPlayers=0, numRound=0;
    //array ordine players nel round
    private Player[] playersRound;
    //indice playerPlaying in playersRound
    private int playersRoundIndex;
    // primo giocatore del round
    private Player firstPlayer;
    //giocatore che sta giocando il turno
    private Player playerPlaying;
    //bag della partita
    private Bag bag;
    //mazzo carte obiettivo
    private ArrayList<ObjectiveCard> publicObjectives;
    //mazzo di schemi per i giocatori
    private SchemeCardDeck schemeCardDeck;
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
    private ArrayList<RemotePlayer> remotePlayer;
    //hashmap con la corrispondenza player-remoteplayer
    private HashMap<Player,RemotePlayer> playerMap;




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


    //metodi per gestire il LOGIN

    //quando si loggano in almeno 2 setta un boolean a true
    public void login (String nickname, RemotePlayer remotePlayer) throws NotValidNicknameException, RemoteException, ToolCardException, NotValidException {
        if(playerMap.size()==0){
            Player player=new Player(nickname);
            player.setLogged(true);
            playerMap.put(player, remotePlayer);
            players.add(player);
            this.remotePlayer.add(remotePlayer);
            numPlayers++;
            System.out.println(numPlayers);
            notifyLogin(player);
            return;
        }
        else if(playerMap.size()<Constants.MAX_PLAYERS) {
            if(checkNickname(nickname)) {
                Player player = new Player(nickname);
                player.setLogged(true);
                playerMap.put(player, remotePlayer);
                players.add(player);
                this.remotePlayer.add(remotePlayer);
                numPlayers++;
                System.out.println(numPlayers);
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
            playerPlaying.setState(PlayerState.ENDEDTURN);
            notifyEndTurn(playerPlaying);
            playerPlaying = playersRound[playersRoundIndex];
            playerPlaying.setState(PlayerState.TURNSTARTED);
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


    public void useDice (Player player, int indexOfDiceInDraftpool, int row, int col, boolean finish) throws NotValidException, RemoteException {
        player.getScheme().placeDice(row,col,draftPool.getDice(indexOfDiceInDraftpool));
        draftPool.removeDice(draftPool.getDice(indexOfDiceInDraftpool));
        if(finish){
            player.setState(PlayerState.FINISHTURN);
        }
        else {
            player.setState(PlayerState.USEDDICE);
        }
        notifyChangement();
        playerMap.get(player).onSetPlaying();
    }


    public void chooseScheme(Player player, int id) throws RemoteException {
        player.setScheme(schemeCardDeck.getSchemeWithId(id));
        player.setNumOfToken(schemeCardDeck.getSchemeWithId(id).getDifficulty());
        player.setState(PlayerState.READYTOPLAY);
        playerMap.get(player).onSuccess("ok hai scelto bene lo schema ");
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

    public void setState(boolean finish, Player player){
        if(finish){
            player.setState(PlayerState.FINISHTURN);
        }
        else
            player.setState(PlayerState.USEDTOOLCARD);
    }


    //metodi delle carte


    public void useToolCard1(Player player, int indexOfDiceInDraftPool, String operation, boolean finish) throws NotValidException, RemoteException, NotValidPlayException {
        if(checkToken(player,1)) {
            findToolCard(1).execute1(draftPool, indexOfDiceInDraftPool, operation);
            player.setNumOfToken(playerPlaying.getNumOfToken()-findToolCard(1).getNumOfTokens());
            setState(finish,player);
            notifyChangement();
            playerMap.get(player).onSetPlaying();
        }
    }

    public void useToolCard234(Player player, int id, int sourceRow, int sourceCol, int destRow, int destCol, boolean finish) throws NotValidException, RemoteException, NotValidPlayException {
        switch (id){
            case 2:
                if(checkToken(player,id)) {
                    findToolCard(id).execute2(player.getScheme(), sourceRow, sourceCol, destRow, destCol);
                    player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                    setState(finish,player);
                    notifyChangement();
                    playerMap.get(player).onSetPlaying();
                    break;
                }
            case 3:
                if(checkToken(player,id)) {
                    findToolCard(id).execute3(player.getScheme(), sourceRow, sourceCol, destRow, destCol);
                    player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                    setState(finish,player);
                    notifyChangement();
                    playerMap.get(player).onSetPlaying();
                    break;
                }
            case 4:
                if(checkToken(player,id)) {
                    findToolCard(id).execute4(player.getScheme(), sourceRow, sourceCol, destRow, destCol);
                    if(findToolCard(id).getFirstExecutionDone()){
                        playerMap.get(player).onOtherInfoToolCard4(this);
                    }
                    player.setNumOfToken(player.getNumOfToken()-findToolCard(id).getNumOfTokens());
                    setState(finish,player);
                    notifyChangement();
                    playerMap.get(player).onSetPlaying();
                    break;
                }
        }
    }

    public void useToolCard5(Player player, int indexInDraftpool, int round, int indexInRound, boolean finish) throws RemoteException, NotValidPlayException {
        if(checkToken(player,5)){
            findToolCard(5).execute5(draftPool,indexInDraftpool,roundTrack, round, indexInRound);
            player.setNumOfToken(player.getNumOfToken()-findToolCard(5).getNumOfTokens());
            setState(finish,player);
            notifyChangement();
            playerMap.get(player).onSetPlaying();
        }
    }

    public void useToolCard6(Player player, int indexInDraftPool, boolean finish) throws RemoteException, NotValidPlayException {
        if(checkToken(player,6)){
            findToolCard(6).execute6(draftPool,indexInDraftPool);
            player.setNumOfToken(player.getNumOfToken()-findToolCard(6).getNumOfTokens());
            setState(finish,player);
            notifyChangement();
            playerMap.get(player).onSetPlaying();
        }
    }

    public void useToolCard78(Player player,int id,boolean finish) throws NotValidException, RemoteException, NotValidPlayException {
        switch(id){
            case 7:
                if(checkToken(player,id)){
                    if(playersRoundIndex>numPlayers-1){
                        findToolCard(7).execute7(draftPool);
                        player.setNumOfToken(player.getNumOfToken()-findToolCard(7).getNumOfTokens());
                        setState(finish,player);
                        notifyChangement();
                        playerMap.get(player).onSetPlaying();
                        break;
                    }
                }
                else throw new NotValidException("non puoi usare la toolcard, aspetta il secondo turno!");
            case 8:
                if(checkToken(player,id)){
                    findToolCard(8).execute8(playersRound,playersRoundIndex);
                    setState(finish,player);
                    playerMap.get(player).onSetPlaying();
                    break;
                }

        }
    }

    public void useToolCard9(Player player, int dice, int row, int col, boolean finish) throws NotValidException, RemoteException, NotValidPlayException {
        if(checkToken(player,9)){
            findToolCard(9).execute9(player.getScheme(),draftPool.getDice(dice),row,col);
            player.setNumOfToken(player.getNumOfToken()-findToolCard(9).getNumOfTokens());
            setState(finish,player);
            notifyChangement();
            playerMap.get(player).onSetPlaying();
        }
    }

    public void useToolCard10(Player player, int dice, boolean finish) throws NotValidException, RemoteException, NotValidPlayException {
        if(checkToken(player,10)){
            findToolCard(10).execute10(draftPool.getDice(dice));
            player.setNumOfToken(player.getNumOfToken()-findToolCard(10).getNumOfTokens());
            setState(finish,player);
            notifyChangement();
            playerMap.get(player).onSetPlaying();
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



