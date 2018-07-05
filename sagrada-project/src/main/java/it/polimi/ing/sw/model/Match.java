
package it.polimi.ing.sw.model;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.model.toolCard.ToolCard;
import it.polimi.ing.sw.util.Constants;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import static java.lang.System.exit;

//import it.polimi.ing.sw.server.Observable;


public class Match implements Serializable {


    /**
     * Numero dei giocatori in partita
     */
    private transient int numPlayers = 0;
    /**
     * Numero dei giocatori effettivamente in gioco,
     * viene decrementato/incrementato in caso
     * di disconnessione/riconnessione
     */
    private int numPlayersPlaying = 0;
    /**
     * Numero del round in corso
     */
    private transient int numRound = 0;
    /**
     * Array dei giocatori del round, tiene traccia dell'ordine
     * in cui devono giocare
     */
    private transient Player[] playersRound;
    /**
     * Indice del giocatore che sta giocando nell'array
     * di uno specifico round, serve per decidere a quale
     * giocatore passare il turno nelo round
     */
    private transient int playersRoundIndex;
    /**
     * Riferimento al primo giocatore del round,
     * serve per decidere il primo giocatore del prossimo round
     */
    private transient Player firstPlayer;
    /**
     * Riferimento al giocatore che sta
     * svolgendo il turno
     */
    private transient Player playerPlaying;
    /**
     * Riferimento al sacchetto dei dadi
     * della partita
     */
    private transient Bag bag;
    /**
     * Mazzo delle carte obiettivo pubbliche
     */
    private ArrayList<ObjectiveCard> publicObjectives;
    /**
     * Mazzo di schemi per i giocatori,
     * all'inizio della partita ogni giocatore sceglierà
     * il suo schema dal mazzo
     */
    private transient SchemeCardDeck schemeCardDeck;
    /**
     * Mazzo delle ToolCard della partita
     */
    private ArrayList<ToolCard> toolCards;
    /**
     * Riserva dei dadi di ogni round
     */
    private DraftPool draftPool;
    /**
     * Tracciato dei punti
     */
    private RoundTrack roundTrack;
    /**
     * ArrayList contente i giocatori
     * della partita
     */
    private ArrayList<Player> players;
    /**
     * ArrayList contenente la classifica dei giocatori
     */
    private ArrayList<Player> ranking;
    /**
     * ArrayList contenente il riferimento alla
     * view di ogni giocatori
     */
    private transient ArrayList<RemotePlayer> remotePlayers;
    /**
     * Hashmap contenente la corrispondenza tra ogni giocatore
     * e la sua view
     */
    private transient HashMap<Player, RemotePlayer> playerMap;
    /**
     * ArrayList contenente tutti i giocatori che si sono loggati,
     * anche quelli che non sono attualmente in gioco
     */
    private transient ArrayList<Player> playersLogged;
    /**
     * Boolean per verificare lo stato della partita
     * e la possibilità di aggiungere o meno giocatori
     */
    private boolean matchStarted = false;


    /**
     * costruttore della partita, inizializza gli array per il salvataggio
     * dei giocatori
     */
    public Match() {
        this.playerMap = new HashMap<Player, RemotePlayer>();
        this.players = new ArrayList<Player>();
        this.remotePlayers = new ArrayList<RemotePlayer>();
        this.playersLogged = new ArrayList<Player>();
    }

    /**
     * METODI GETTERS, utili per il metodo @cloneMatch() e per gli update sulla view
     */

    /**
     * Ritorna un giocatore loggato con un certo nickname
     *
     * @param nickname
     * @return il giocatore corrispondente al nickname, null altrimenti
     */
    public Player getPlayerLogged(String nickname) {
        if (playersLogged.size() != 0) {
            for (Player player : playersLogged) {
                if (player.getNickname().equals(nickname)) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * ritorna il giocatore in gioco con un certo nickname
     *
     * @param nickname
     * @return il giocatore col nickname, altrimenti null
     */
    public Player getPlayer(String nickname) {
        if (players.size() != 0) {
            for (Player player : players) {
                if (player.getNickname().equals(nickname)) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * @return l'indice del giocatore giocante in PlayersRound
     */
    public int getPlayersRoundIndex() {
        return playersRoundIndex;
    }

    /**
     * @return Riserva
     */
    public DraftPool getDraftPool() {
        return draftPool;
    }

    /**
     * @return Carte Obiettivo pubbliche
     */
    public ArrayList<ObjectiveCard> getPublicObjectives() {
        return publicObjectives;
    }

    /**
     * @return Tracciato dei Round
     */
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    /**
     * @return classifica dei giocatori
     */
    public ArrayList<Player> getRanking() {
        return ranking;
    }

    /**
     * @return il giocatore in gioco
     */
    public Player getPlayerPlaying() {
        return playerPlaying;
    }

    /**
     * @return numero dei giocatori
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * @param nickname
     * @return un ArrayList contenente tutti i giocatori in gioco nella partita,
     * escluso quello col nickname passato
     */
    public ArrayList<Player> getOtherPlayers(String nickname) {
        ArrayList<Player> otherPlayers = players;
        otherPlayers.remove(getPlayer(nickname));
        return otherPlayers;
    }


    /**
     * METODI SETTERS, utili per il metodo @cloneMatch()
     */

    public void setColorOfPawns() {
        Color[] colorOfPawns = new Color[4];
        colorOfPawns[0] = Color.PURPLE;
        colorOfPawns[1] = Color.RED;
        colorOfPawns[2] = Color.GREEN;
        colorOfPawns[3] = Color.BLUE;
        for (int i = 0; i < numPlayers; i++)
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


    /**
     * METODI NECESSARI PER IL CORRETTO LOGIN DEI GIOCATORI
     */


    /**
     * Metodo che logga un giocatore.
     * Il login va a buon fine se è il primo login che effettua, i giocatori non sono ancora in 4, la partita non è già iniziata.
     * e il nickname non è uguale a quello di nessun altro.
     * Se il login va a buon fine il giocatore viene aggiunto alle liste dei giocatori.
     * Altrimenti, se la partita è piena o già iniziata, viene lanciata eccezione e il giocatore potrà provare a riconnettersi.
     * Se il giocatore si sta riconnettendo, viene aggiornato il numero dei giocatori giocanti e lo stato del giocatore.
     *
     * @param nickname
     * @param remotePlayer
     * @throws NotValidNicknameException      , segnala che vi è già un giocatore con quel nickname
     * @throws NotPossibleConnectionException , segnala che la partita è piena o giù iniziata
     */
    public void login(String nickname, RemotePlayer remotePlayer) throws NotValidNicknameException, NotPossibleConnectionException {
        if (!checkReconnection(nickname)) {
            if (playerMap.size() < Constants.MAX_PLAYERS) {
                if (!matchStarted) {
                    if (checkNickname(nickname)) {
                        Player player = new Player(nickname);
                        player.setLogged(true);
                        playerMap.put(player, remotePlayer);
                        players.add(player);
                        remotePlayers.add(remotePlayer);
                        numPlayers++;
                        numPlayersPlaying++;
                        return;
                    } else
                        throw new NotValidNicknameException("il nickname scelto è già in uso, scegline un altro!");
                } else
                    throw new NotPossibleConnectionException("la partita è già iniziata");
            } else {
                throw new NotPossibleConnectionException("la partita è piena");
            }
        } else
            playerMap.replace(getPlayer(nickname), remotePlayer);
        getPlayer(nickname).setState(PlayerState.INIZIALIZED);
        numPlayersPlaying++;
    }

    /**
     * metodo che controlla la validità del nickname scelto
     *
     * @param nickname
     * @return true se il nickname non è già stato usato da altri , false altrimenti
     */
    public boolean checkNickname(String nickname) {
        boolean check = true;
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) {
                check = false;
            }
        }
        return check;
    }

    /**
     * metodo che controlla se il giocatore sta tendando di riconnettersi o meno
     *
     * @param nickname
     * @return true se il giocatore è presente nella lista dei giocatori , ma è offline, false altrimenti
     */
    public boolean checkReconnection(String nickname) {
        for (Player player : players) {
            if (player.getNickname().equals(nickname) && player.getState().equals(PlayerState.OFFLINE)) {
                return true;
            }
        }
        return false;
    }

    /**
     *METODI PER LA GESTIONE DELLA LOGICA DI PARTITA
     */


    /**
     * Metodo con cui i giocatori entrano effettivamente in partita,
     * se si arriva a 4 giocatori, inizia la partita.
     * se si arriva a 2 giocatori e scade il timer, inizia la partita.
     *
     * @throws RemoteException
     * @throws NotValidPlayException
     */
    public synchronized void joinMatch() throws RemoteException, NotValidPlayException {
        if (!matchStarted) {
            if (players.size() == Constants.MAX_PLAYERS) {
                matchStarted = true;
                startMatch();
            } else if (players.size() == 2) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (players.size() >= 2 && matchStarted==false) {
                            try {
                                matchStarted = true;
                                startMatch();
                            } catch (RemoteException e) {
                                exit(0);
                            } catch (NotValidPlayException e) {
                                try {
                                    notifyNotValidPlayException(playerPlaying, e.getMessage());
                                } catch (RemoteException e1) {
                                    exit(0);
                                }
                            }
                        }
                    }
                }, 10000);
            }
        }

    }

    /**
     * Metodo chiamato dai giocatori dopo aver scelto lo schema, per controllare se anche gli altri giocatori hanno scelto il loro.
     * Se i giocatori si disconnettono in questa fase rimangono comunque in partita, a meno che non ne rimanga solo 1.
     *
     * @throws RemoteException
     */
    public void checkAllReady() throws RemoteException {
        boolean check = false;
        int countPlayer=0;

        for (Player player : players) {
            if (!player.getState().equals(PlayerState.READYTOPLAY)) {
                if (player.getState().equals(PlayerState.OFFLINE)) {
                    numPlayersPlaying--;
                    if (numPlayersPlaying == 1) {
                        endMatch();
                    }
                    else countPlayer++;
                }
            }
            else{
                countPlayer++;
            }
        }
        if (countPlayer==numPlayers)
        {
            check=true;
        }
        if (check) {
            startRound();
        }
    }

    /**
     * metodo che fa partira la partita: vengono inizializzati i giorcatori e la tavola di gioco.
     * tutti i giocatori vengono notificati dell'inizio del match.
     *
     * @throws RemoteException
     * @throws NotValidPlayException
     */
    public void startMatch() throws RemoteException, NotValidPlayException {
        initializeTable();
        inizializePlayers();
        setColorOfPawns();
        notifyStartedMatch();
    }


    /**
     * Metodo che inizializza la Tavola di gioco, quindi la Riserva, il Tracciato dei Round ,le Carte Utensili e la Carte Obiettivo.
     */
    public void initializeTable() {
        bag = new Bag();
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        publicObjectives = objectiveCardDeck.drawObjectiveCard();
        ToolCards tool = new ToolCards();
        toolCards = tool.getToolCards();
        roundTrack = new RoundTrack();
        draftPool = new DraftPool();
    }


    /**
     * Metodo che inizializza i giocatori, assegnando un mazzo di Carte Schema tra cui scegliere e un Obiettivo Privato
     * aggiorna anche lo stato dei giocatori
     */
    public void inizializePlayers() {
        this.schemeCardDeck = new SchemeCardDeck();
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        playerPlaying = null;
        firstPlayer = null;

        for (int i = 0; i < numPlayers; i++) {
            players.get(i).setPrivateObjective(privateObjectives.get(i));
            players.get(i).setSchemesToChoose(schemeCardDeck.drawSchemeCard());
            players.get(i).setState(PlayerState.SCHEMETOCHOOSE);
        }
        this.schemeCardDeck = new SchemeCardDeck();
    }

    /**
     * Metodo per il termine della partita, pone il boolean a false e reinizializza tutti gli attributi
     */
    public void endMatch() {
        matchStarted = false;
        numPlayers = 0;
        playersRoundIndex = 0;
        numPlayersPlaying = 0;
        numRound = 0;
        this.playerMap = new HashMap<Player, RemotePlayer>();
        this.players = new ArrayList<Player>();
        this.remotePlayers = new ArrayList<RemotePlayer>();
        this.playersLogged = new ArrayList<Player>();
    }


    /**
     * Metodo che da inizio a un nuovo round: si estraggono i dadi, si stabilisce il primo giocatore e si
     * chiama il metodo che costruisce l'array playersRound per il round appena iniziato.
     * se è il primo round si decide casualmente il turno dei giocatori.
     * Aggiorna anche gli stati dei giocatori e notifica il primo giocatore del round che è il suo turno.
     */

    public void startRound() throws RemoteException {
        if (numRound == 0) {
            draftPool = bag.draw(numPlayers);
            Collections.shuffle(players);
            playersRound = new Player[numPlayers * 2];
            firstPlayer = players.get(0);
            createRoundPlayers(0);
            playerPlaying = firstPlayer;
            playersRoundIndex = 0;
            playerPlaying.setState(PlayerState.TURNSTARTED);
            for (Player player : players) {
                if (!(player.equals(playerPlaying))) {
                    if (!(player.getState().equals(PlayerState.OFFLINE))) {
                        player.setState(PlayerState.ENDEDTURN);
                    }
                }
            }
            notifyChangement();
            notifyStartTurn(firstPlayer);
        } else {
            draftPool = bag.draw(numPlayers);
            changePlayersRound(firstPlayer);
            firstPlayer = playersRound[0];
            playerPlaying=firstPlayer;
            if(playerPlaying.getState().equals(PlayerState.OFFLINE)){
                playerPlaying=playersRound[1];
            }
            System.out.println(playerPlaying.getNickname()+ playerPlaying.getState());
            playerPlaying.setState(PlayerState.TURNSTARTED);
            for (Player player : players) {
                if (!(player.equals(playerPlaying))) {
                    if (!(player.getState().equals(PlayerState.OFFLINE))) {
                        player.setState(PlayerState.ENDEDTURN);
                    }
                }
            }
            notifyChangement();
            notifyStartTurn(playerPlaying);
        }
    }


    /**
     * Metodo che modifica l'Array dell'ordine dei giocatori nel round dopo l'inizio di un nuovo Round
     * Modifica il primo giocatore nel Round. Se tutti sono già stati primi giocatori, si ricomincia.
     *
     * @param firstPlayer , riferimento al primo giocatore nell'Array dei players
     */
    public void changePlayersRound(Player firstPlayer) {
        int first = players.indexOf(firstPlayer);
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



    /**
     * Metodo che crea l'array dell'ordine dei giocatori del primo Round
     *
     * @param firstPlayerIndex
     */
    public void createRoundPlayers(int firstPlayerIndex) {
        int first = firstPlayerIndex;

        for (int i = 0; i < numPlayers; i++) {
            if (first < numPlayers) {
                playersRound[i] = players.get(first);
                playersRound[((numPlayers * 2) - 1) - i] = players.get(first);
            } else if (first == numPlayers) {
                first = 0;
                playersRound[i] = players.get(first);
                playersRound[((numPlayers * 2) - 1) - i] = players.get(first);
            }
            first++;
        }

    }

    /**
     * Metodo per il cambio del turno del giocatore, notifico la fine del turno e l'inizio al nuovo giocatore
     *
     * @throws RemoteException
     */
    public void changePlayer() throws RemoteException {
        if (playersRoundIndex < (numPlayers * 2) - 1) {
            if(numPlayers==2){
                playersRoundIndex++;
                playerPlaying.setState(PlayerState.ENDEDTURN);
                notifyEndTurn(playerPlaying);
                playerPlaying = playersRound[playersRoundIndex];
                playerPlaying.setState(PlayerState.TURNSTARTED);
                notifyStartTurn(playerPlaying);
            }
            else if(numPlayers==3) {
                playersRoundIndex++;
                playerPlaying.setState(PlayerState.ENDEDTURN);
                notifyEndTurn(playerPlaying);
                if (playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                    playersRoundIndex++;
                    if (playersRoundIndex > ((numPlayers * 2) - 1)) {
                        playersRoundIndex = 0;
                        endRound();
                    }
                }
                playerPlaying = playersRound[playersRoundIndex];
                playerPlaying.setState(PlayerState.TURNSTARTED);
                notifyStartTurn(playerPlaying);
            }
            else if(numPlayers==4){
                playersRoundIndex++;
                playerPlaying.setState(PlayerState.ENDEDTURN);
                notifyEndTurn(playerPlaying);
                if (playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                    playersRoundIndex++;
                    if (playersRoundIndex > ((numPlayers * 2) - 1)) {
                        playersRoundIndex = 0;
                        endRound();
                    }
                    else {
                        if (playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                            playersRoundIndex++;
                            if (playersRoundIndex > ((numPlayers * 2) - 1)) {
                                playersRoundIndex = 0;
                                endRound();
                            }
                        }
                    }
                }
                playerPlaying = playersRound[playersRoundIndex];
                playerPlaying.setState(PlayerState.TURNSTARTED);
                notifyStartTurn(playerPlaying);
            }
        } else if (playersRoundIndex == (numPlayers * 2) - 1) {
                playersRoundIndex = 0;
                endRound();
        }
    }


    /**
     * Metodo che gestisce la fine di un Round, aggiunge i dadi al Tracciato dei Round e fa partire un nuovo Round.
     * Se è l'ultimo Round, calcola la classifica dei giocatori e notifica la fine della partita
     *
     * @throws RemoteException
     */
    public void endRound() throws RemoteException {
        numRound++;
        roundTrack.addDicesRound(draftPool);
        if (numRound < Constants.NUM_ROUNDS) {
            startRound();
        } else if (numRound == Constants.NUM_ROUNDS) {
            calculateRanking();
            notifyGameEnd();
        }
    }


    /**
     * Metodo che calcola il punteggio del giocatore ricevuto come parametro
     *
     * @param player
     * @return
     */
    public int calculateScore(Player player) {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            score = score + publicObjectives.get(i).calculateScore(player.getScheme());
        }
        score = score + player.getPrivateObjective().calculateScore(player.getScheme());
        score = score + player.getNumOfToken();
        score = score - player.getScheme().countFreeBoxes();
        player.setScore(score);
        return score;
    }


    /**
     * Metodo che calcola la classifica finale dei giocatori
     */
    public void calculateRanking() {   // ritorna un array di players ordinato dal punteggio massimo al minimo
        int scores[] = new int[numPlayers];
        ranking = new ArrayList<Player>();
        ArrayList<Player> tempPlayers = new ArrayList<Player>(players);
        int i, j, max, k = 1;
        boolean found = false;
        for (i = 0; i < numPlayers; i++) {
            scores[i] = calculateScore(players.get(i));
        }
        for (i = 0; i < numPlayers; i++) {
            max = 0;
            for (j = 1; j < tempPlayers.size(); j++) {
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

    /**
     * METODI PER LA GESTIONE DEL TURNO I OGNI GIOCATORE
     */


    /**
     * Metodo per l'utilizzo di un dado, chiama la funzione di piazzamento e se va a buon fine toglie il dado dalla Riserva.
     * Aggiorna poi lo stato del giocatore attraverso un metodo ausiiario.
     *
     * @param player
     * @param indexOfDiceInDraftpool
     * @param row
     * @param col
     * @throws NotValidException
     * @throws RemoteException
     */
    public void useDice(Player player, int indexOfDiceInDraftpool, int row, int col) throws NotValidException, RemoteException {
        player.getScheme().placeDice(row, col, draftPool.getDice(indexOfDiceInDraftpool));
        draftPool.removeDice(draftPool.getDice(indexOfDiceInDraftpool));
        changePlayerStateAfterUseDice(player);
    }

    /**
     * Controlla in che stato deve passare il giocatore dopo aver utilizzato un dado, se non può più fare mosse passa automaticamente il turno
     *
     * @param player
     * @throws RemoteException
     */
    public void changePlayerStateAfterUseDice(Player player) throws RemoteException {
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

    /**
     * Metodo per settare lo schema scelto dal giocatore, notifica l'avvenuto successo e aggiorna lo stato del giocatore
     *
     * @param player
     * @param id
     * @throws RemoteException
     */
    public void chooseScheme(Player player, int id) throws RemoteException {
        player.setScheme(schemeCardDeck.getSchemeWithId(id));
        player.setNumOfToken(schemeCardDeck.getSchemeWithId(id).getDifficulty());
        player.setState(PlayerState.READYTOPLAY);
        playerMap.get(player).onSuccess("Schema scelto correttamente. Attendi l'inizio del primo turno.");
    }

    /**
     * METODI PER L'UTILIZZO DELLE TOOLCARD
     */

    /**
     * Metodo ausiliario per l'ultilizzo di una toolcard: controlla che il giocatore abbia sufficienti Segnalini Favore
     *
     * @param player
     * @param idToolCard
     * @return
     * @throws NotValidPlayException
     */
    public boolean checkToken(Player player, int idToolCard) throws NotValidPlayException {
        if (!player.getState().equals(PlayerState.TURNSTARTED) && !player.getState().equals(PlayerState.USEDDICE))
            return true;
        if (player.getNumOfToken() >= findToolCard(idToolCard).getNumOfTokens()) {
            return true;
        } else
            throw new NotValidPlayException("I tuoi segnalini favore non sono sufficienti.");
    }

    /**
     * Metodo ausiliario che estrae la ToolCard richiesta dal mazzo
     *
     * @param id
     * @return
     */
    public ToolCard findToolCard(int id) {
        for (ToolCard toolCard : toolCards) {
            if (toolCard.getId() == id) {
                return toolCard;
            }
        }
        return null;
    }

    /**
     * Metodo ausiliario che controlla il turno del giocatore
     *
     * @param player
     * @return
     */
    public Boolean getIfFirstTurn(Player player) {
        for (int i = 0; i < playersRoundIndex; i++) {
            if (playersRound[i] == player)
                return false;
        }
        return true;
    }

    /**
     * Metodo per l'utilizzo generico di una ToolCard: in base all'id chiama gli specifici metodi delle carte coi relativi parametri
     *
     * @param player
     * @param id
     * @param dice
     * @param operation
     * @param sourceRow
     * @param sourceCol
     * @param destRow
     * @param destCol
     * @throws NotValidException
     * @throws NotValidPlayException
     * @throws RemoteException
     */
    public void useToolCard(Player player, int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException, NotValidPlayException, RemoteException {
        if (checkToken(player, id)) {
            findToolCard(id).execute(draftPool, roundTrack, player.getScheme(), playersRound, bag, dice, operation, sourceRow, sourceCol, destRow, destCol);
            switch (id) {
                case 4: {
                    if (!findToolCard(id).getFirstExecutionDone()) {
                        player.setNumOfToken(player.getNumOfToken() - findToolCard(id).getNumOfTokens());
                        findToolCard(id).incrementNumOfTokens();
                    }
                    break;
                }
                case 11:
                case 12: {
                    if (findToolCard(id).getFirstExecutionDone()) {
                        player.setNumOfToken(player.getNumOfToken() - findToolCard(id).getNumOfTokens());
                        findToolCard(id).incrementNumOfTokens();
                    }
                    break;
                }
                default: {
                    player.setNumOfToken(player.getNumOfToken() - findToolCard(id).getNumOfTokens());
                    findToolCard(id).incrementNumOfTokens();
                    break;
                }
            }
        }
        changePlayerStateAfterToolCard(player, id);
    }

    /**
     * Metodo per l'aggiornamento dello stato in seguito all'utilizzo di una ToolCard, in base alle specifiche della stessa
     *
     * @param player
     * @param id
     * @throws NotValidPlayException
     * @throws RemoteException
     */
    public void changePlayerStateAfterToolCard(Player player, int id) throws NotValidPlayException, RemoteException {
        switch (id) {

            /**
             *carte che si possono utilizzare in qualsiasi momento
             */
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

            /**
             *carte che si possono utilizzare in qualsiasi momento ma si eseguono in 2 step
             */
            case 4:
            case 12: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.FIRSTSTEPTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                } else {
                    if (player.getState().equals(PlayerState.FIRSTSTEPTOOLCARD)) {
                        player.setState(PlayerState.USEDTOOLCARD);
                        notifyUsedToolCard(player, id);
                        break;
                    } else {
                        if (player.getState().equals(PlayerState.USEDDICE)) {
                            player.setState(PlayerState.USEDDICETOOLCARD);
                            notifyUsedToolCard(player, id);
                            break;
                        } else {
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
            /**
             * carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
             */
            case 6: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                break;
            }
            /**
             * carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
             */
            case 11: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    player.setState(PlayerState.USEDDICETOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                } else {
                    if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                        notifyChangement();
                        changePlayer();
                        break;
                    }
                }
                break;
            }
            /**
             *  carta che può essere utilizzata solo durante il secondo turno e prima di scegliere il secondo dado
             */
            case 7: {
                if (player.getState().equals(PlayerState.TURNSTARTED) && !getIfFirstTurn(player)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;
                }
                break;
            }
            /**
             * carta che può essere utilizzata solo durante il primo turno
             */
            case 8: {
                if (player.getState().equals(PlayerState.TURNSTARTED) && getIfFirstTurn(player)) {
                    player.setState(PlayerState.USEDTOOLCARD);
                    notifyUsedToolCard(player, id);
                    break;

                } else {
                    if (player.getState().equals(PlayerState.USEDDICE) && getIfFirstTurn(player)) {
                        notifyChangement();
                        changePlayer();
                        break;
                    }
                }
                break;
            }
            /**
             * carta che può essere utilizzata solo se non si è già piazzato un dado
             */
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

    /**
     * Metodo che chiama le notifiche alla View in base alla ToolCard utilizzata
     *
     * @param player
     * @param id
     * @throws RemoteException
     */

    public void notifyUsedToolCard(Player player, int id) throws RemoteException {
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


    /**
     * Metodo che gestisce un'eventuale disconnessione da parte del giocatore.
     * Se il giocatore sta giocando, verifica a chi passare il turno e notifica il cambiamento
     * Se la partita è già iniziata il giocatore rimane in partita e potrà rientrare rieffettuando il Login,
     * altrimenti verrà escluso dalla lista dei giocatori e per rientrare dovrà rieffettuare il login.
     *
     * @param player
     * @throws RemoteException
     */
    public void exitPlayer(Player player) throws RemoteException {
        if (!matchStarted) {
            System.out.println("aiuto");
            players.remove(player);
            playerMap.remove(player);
            numPlayersPlaying--;
            numPlayers--;
            return;
        } else {
            System.out.println(player.getNickname() + "\n" +player.getState());
            if (!(player.getState().equals(PlayerState.OFFLINE))) {
                System.out.println("perchè conto così male??");
                player.setState(PlayerState.OFFLINE);
                System.out.println(player.getNickname() + player.getState());
                numPlayersPlaying = numPlayersPlaying - 1;
                if (numPlayersPlaying == 1) {
                    calculateRanking();
                    notifyGameEnd();
                    return; /*
                } else if (playerPlaying.equals(player)) {

                    System.out.println("primo if");
                    if (playersRoundIndex < numPlayers * 2 - 1) {
                        playersRoundIndex++;
                        System.out.println("secondo if");
                        if (!playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                            playerPlaying = playersRound[playersRoundIndex];
                            playerPlaying.setState(PlayerState.TURNSTARTED);
                            System.out.println("terzo if");
                            notifyStartTurn(playerPlaying);
                        } else {
                            playersRoundIndex++;
                            if (playersRoundIndex > (numPlayers * 2) - 1) {
                                playersRoundIndex = 0;
                                endRound();
                            } else if (!playersRound[playersRoundIndex].getState().equals(PlayerState.OFFLINE)) {
                                playerPlaying = playersRound[playersRoundIndex];
                                playerPlaying.setState(PlayerState.TURNSTARTED);
                                System.out.println("ultimo if");
                                notifyStartTurn(playerPlaying);
                            }
                        }
                    } else if (playersRoundIndex == ((numPlayers * 2) - 1)) {
                        playersRoundIndex = 0;
                        endRound();
                    }*/
                }
            }
        }
    }



    /**
     * METODI PER AGGIORNARE LA VIEW
     * @throws RemoteException
     */


    /**
     * Notifica un generico cambiamento, a tutti i giocatori, tranne quelli OFFLINE
     *
     * @throws RemoteException
     */
    public void notifyChangement() throws RemoteException {
        for (Player player : players) {
            try {
                if (!player.getState().equals(PlayerState.OFFLINE)) {
                    playerMap.get(player).onGameUpdate(this.matchClone());
                }
            } catch (RemoteException e) {
                exitPlayer(player);
            }
        }
    }

    /**
     * Notifica l'inizio della partita a tutti i giocatori, tranne quelli OFFLINE
     *
     * @throws RemoteException
     * @throws NotValidPlayException
     */
    private void notifyStartedMatch() throws RemoteException, NotValidPlayException {
        for (Player player : players) {
            try {
                if (!player.getState().equals(PlayerState.OFFLINE)) {
                    playerMap.get(player).onSchemeToChoose(this.matchClone());
                }
            } catch (RemoteException e) {
                exitPlayer(player);
            }
        }
    }

    /**
     * Notifica l'inizio del turno al giocatore, non può essere chiamata se il giocatore è OFFLINE
     *
     * @param player
     * @throws RemoteException
     */
    private void notifyStartTurn(Player player) throws RemoteException {
        try {
            playerMap.get(player).onSetPlaying();
        } catch (RemoteException e) {
            exitPlayer(player);
        }
    }

    /**
     * Notifica la fine del turno al giocatore
     *
     * @param player
     * @throws RemoteException
     */
    public void notifyEndTurn(Player player) throws RemoteException {
        try {
            playerMap.get(player).onTurnEnd();
        } catch (RemoteException e) {
            exitPlayer(player);
        }
    }

    /**
     * Notifica la fine della partita a tutti i giocatori, tranne quelli OFFLINE,
     * dopo aver notificato chiama il metodo per iniziare una nuova partita
     *
     * @throws RemoteException
     */
    public void notifyGameEnd() throws RemoteException {
        for (Player player : players) {
            try {
                if (!player.getState().equals(PlayerState.OFFLINE)) {
                    playerMap.get(player).onGameEnd(this.matchClone());
                }
            } catch (RemoteException e) {
                exitPlayer(player);
            }
        }
        endMatch();
    }

    /**
     * Notifica un messaggio di successo a tutti i giocatori, tranne quelli OFFLINE
     *
     * @param message
     * @throws RemoteException
     */
    public void notifySucces(String message) throws RemoteException {
        for (Player player : players) {
            try {
                if (!player.getState().equals(PlayerState.OFFLINE)) {
                    playerMap.get(player).onSuccess(message);
                }
            } catch (RemoteException e) {
                exitPlayer(player);
            }
        }
    }

    /**
     * Notifica il sollevamento di una eccezione nell'utilizzo di un dado
     *
     * @param player
     * @param message
     * @throws RemoteException
     */
    public void notifyNotValidUseDiceException(Player player, String message) throws RemoteException {
        try {
            playerMap.get(player).onNotValidUseDiceException(message);
        } catch (RemoteException e) {
            exitPlayer(player);
        }
    }

    /**
     * Notifica il sollevamento di un'eccezione nell'utilizzo di una ToolCard
     *
     * @param player
     * @param id
     * @param message
     * @throws RemoteException
     */
    public void notifyNotValidToolCardException(Player player, int id, String message) throws RemoteException {
        try {
            playerMap.get(player).onNotValidToolCardException(id, message);
        } catch (RemoteException e) {
            exitPlayer(player);
        }
    }

    /**
     * Notifica il sollevamento di un'eccezione nell'esecuzione di una mossa
     *
     * @param player
     * @param message
     * @throws RemoteException
     */
    public void notifyNotValidPlayException(Player player, String message) throws RemoteException {
        try {
            playerMap.get(player).onNotValidPlayException(message);
        } catch (RemoteException e) {
            exitPlayer(player);
        }
    }

    /**
     * Notifica il sollevamento di un'eccezione nella richiesta di login
     *
     * @param player
     * @param message
     * @throws RemoteException
     */
    public void notifyNotValidNicknameException(RemotePlayer player, String message) throws RemoteException {
        player.onNotValidNicknameException(message);
    }

    /**
     * Notifica il sollevamento di un'eccezione nel tentativo di connessione
     *
     * @param player
     * @param message
     * @throws RemoteException
     */
    public void notifyNotPossibleConnectionException(RemotePlayer player, String message) throws RemoteException {
        player.onNotPossibleConnectionException(message);
    }


    /**
     * Metodo necessario per creare un clone del match da serializzare e inviare alla View via socket
     */
    public Match matchClone() {
        Match matchClone = new Match();
        matchClone.setDraftPool(draftPool.cloneDraftPool());
        matchClone.setRoundTrack(roundTrack.cloneRoundTrack());
        ArrayList<Player> playersClone = new ArrayList<Player>();
        for (Player player : players) {
            playersClone.add(player.playerClone());
        }
        ArrayList<ToolCard> toolCardsClone = new ArrayList<ToolCard>();
        for (int i = 0; i < toolCards.size(); i++) {
            ToolCard toolCardCopy = toolCards.get(i);
            toolCardsClone.add(toolCardCopy);
        }
        matchClone.setToolCards(toolCardsClone);
        matchClone.setPlayers(playersClone);
        matchClone.setPublicObjectives(publicObjectives);
        matchClone.setRanking(this.getRanking());
        return matchClone;
    }

}



