package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe che gestisce gli input dei client e implementa i metodi di PlayerInterface (sulla rete)

public class PlayerController extends UnicastRemoteObject implements PlayerInterface, Remote{
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayer remotePlayer;
    //stato del giocatore
    private PlayerState state;
    //tengo traccia del nickname nel caso lo stronzo si riconnettesse
    private String nickname;

    public PlayerController(Match match, RemotePlayer remotePlayer, Player player) throws RemoteException {
        super();
        this.match=match;
        this.remotePlayer=remotePlayer;
        this.player=player;
        this.nickname=player.getNickname();
        this.state=PlayerState.INIZIALIZED;
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
    }

    public void setState(PlayerState state){
        this.state=state;
    }

    public PlayerState getState() {
        return this.state;
    }

    public RemotePlayer getRemotePlayer() {
        return this.remotePlayer;
    }

    public String getNickname(){
        return this.nickname;
    }

    @Override
    public void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        if(state.equals(PlayerState.OFFLINE)){
            //gestisci lo stronzo che ritorna in partita
        }
        else if(state.equals(PlayerState.INIZIALIZED)) {
            setState(PlayerState.SCHEMETOCHOOSE);
            match.joinMatch();

        }
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
    }

    @Override
    public void checkAllReady() throws RemoteException, NotValidPlayException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        if(state.equals(PlayerState.READYTOPLAY)){
            match.checkAllReady();
        }
        else throw new NotValidPlayException("non va bene !");
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
    }


    @Override
    public void setChosenScheme(int id) throws NetworkException, RemoteException , NotValidPlayException{
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        if (state.equals(PlayerState.SCHEMETOCHOOSE)) {
            match.chooseScheme(this.player,id);
            setState(PlayerState.READYTOPLAY);
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        }
        else throw new NotValidPlayException("non puoi fare questa mossa ora!" + state.toString());
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        System.out.println("aiuto"+ row+col);
        switch (state){
            case USEDDICE: throw new NotValidPlayException("hai già usato un dado in questo turno!");
            case FINISHTURN: throw new NotValidPlayException("non puoi più fare mosse, passa il turno");
            case USEDTOOLCARD: //devo cfare un caso per ogni toolcard per vedere se lo puoi fare o no;
            case READYTOPLAY:  {//faccio un controllo che sia veramente attivo?? --> mi serve un isPlaying dalla view
                match.useDice(player, indexOfDiceInDraftPool, row, col);
                setState(PlayerState.USEDDICE);
                break;
            }
            default: throw new NotValidPlayException("non puoi questa mossa ora");
        }
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
    }

    //quando passo il turno poi sono pronto a giocare il prossimo turno--> tanto non sarò attivo quindi non potrò chiamare
    //i metodi. Oppure facciamo un altro stato per essere più sicuri e quando vieni notificato isPlaying passi allo stato READYTOPLAY???
    @Override
    public void endTurn() throws NetworkException, RemoteException, NotValidPlayException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ state.toString());
        if(state.equals(PlayerState.CHOOSENTOOLCARD)|| state.equals(PlayerState.INIZIALIZED)){
            throw new NotValidPlayException("finisci il turno caro!");
        }
        else {
            match.changePlayer();
            setState(PlayerState.READYTOPLAY);
        }
    }

    @Override
    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        if(state.equals(PlayerState.READYTOPLAY)){
            match.useToolCard1(indexInDraftPool,operation);
            setState(PlayerState.USEDTOOLCARD);
        }
        else if(state.equals(PlayerState.USEDDICE)){
            match.useToolCard1(indexInDraftPool,operation);
            setState(PlayerState.FINISHTURN);
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, RemoteException{

    }

    @Override
    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, RemoteException {

    }

    @Override
    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, RemoteException {

    }

    @Override
    public void useToolCard78(int id) throws NetworkException, NotValidException, RemoteException {

    }


}
