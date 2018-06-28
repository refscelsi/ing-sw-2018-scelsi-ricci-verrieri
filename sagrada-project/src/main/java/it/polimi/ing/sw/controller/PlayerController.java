package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.RMI.PlayerInterfaceRMI;
import it.polimi.ing.sw.controller.network.RMI.RemotePlayerRMI;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe che gestisce gli input dei client e implementa i metodi di PlayerInterfaceSocket (sulla rete)

public class PlayerController extends UnicastRemoteObject implements PlayerInterfaceRMI, Remote{
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayerRMI remotePlayerRMI;
    //stato del giocatore
    private PlayerState state;
    //tengo traccia del nickname nel caso lo stronzo si riconnettesse
    private String nickname;

    public PlayerController(Match match, RemotePlayerRMI remotePlayerRMI, Player player) throws RemoteException {
        super();
        this.match=match;
        this.remotePlayerRMI = remotePlayerRMI;
        this.player=player;
        this.nickname=player.getNickname();
        //this.state=player.getState();
    }

    /*public void setState(PlayerState state){
        this.state=player.getState();
    }*/

    public PlayerState getState() {
        return this.player.getState();
    }

    public RemotePlayerRMI getRemotePlayerRMI() {
        return this.remotePlayerRMI;
    }

    public String getNickname(){
        return this.nickname;
    }

    @Override
    public void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException {
        if(player.getState().equals(PlayerState.OFFLINE)){
            //gestisci lo stronzo che ritorna in partita
        }
        else if(player.getState().equals(PlayerState.INIZIALIZED)) {
            match.joinMatch();

        }
    }

    @Override
    public void checkAllReady() throws RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.READYTOPLAY)){
            match.checkAllReady();
        }
        else throw new NotValidPlayException("non va bene !");
    }


    @Override
    public void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException {
        if (player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
            match.chooseScheme(this.player,id);
        }
        else throw new NotValidPlayException("non puoi fare questa mossa ora!" + player.getState().toString());
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        //System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        switch (player.getState()){
            case USEDDICE: throw new NotValidPlayException("hai già usato un dado in questo turno!");
            case FINISHTURN: throw new NotValidPlayException("non puoi più fare mosse, passa il turno");
            case USEDTOOLCARD: match.useDice(player,indexOfDiceInDraftPool,row,col,true);
                                break;
            case TURNSTARTED:  match.useDice(player,indexOfDiceInDraftPool,row,col,false);
                                break;
            case ENDEDTURN: throw new NotValidPlayException("non è il tuo turno!");
            case READYTOPLAY: throw new NotValidPlayException("non puoi fare questa mossa ora");
            case INIZIALIZED: throw new NotValidPlayException("non puoi fare questa mossa ora");
            case OFFLINE: throw new NotValidPlayException("non puoi fare questa mossa ora");
            case SCHEMETOCHOOSE: throw new NotValidPlayException("non puoi fare questa mossa ora");
            case USEDDICETOOLCARD: throw new NotValidPlayException("non puoi fare questa mossa ora");
            case FIRSTSTEPTOOLCARD: throw new NotValidPlayException("non puoi fare questa mossa ora");

        }
    }

    //quando passo il turno poi sono pronto a giocare il prossimo turno--> tanto non sarò attivo quindi non potrò chiamare
    //i metodi. Oppure facciamo un altro stato per essere più sicuri e quando vieni notificato isPlaying passi allo stato READYTOPLAY???
    @Override
    public void endTurn() throws NetworkException, RemoteException, NotValidPlayException {
        //System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        if(player.getState().equals(PlayerState.READYTOPLAY)|| player.getState().equals(PlayerState.INIZIALIZED ) || player.getState().equals(PlayerState.OFFLINE)){
            throw new NotValidPlayException("finisci il turno caro!");
        }
        else {
            match.changePlayer();
            System.out.println("turno finito");
        }
    }

    @Override
    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
            match.useToolCard1(player,indexInDraftPool,operation,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
            match.useToolCard1(player ,indexInDraftPool,operation,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard234(player,id,sourceRow,sourceCol,destRow,destCol,false);
            System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());

        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard234(player,id,sourceRow,sourceCol,destRow,destCol,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard5(player,indexInDraftPool, round, indexInRound,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard5(player,indexInDraftPool, round, indexInRound,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard6(player,indexInDraftPool,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard6(player,indexInDraftPool,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }


    @Override
    public void useToolCard78(int id) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard78(player,id,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard78(player,id,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void sendUseToolCard9Request(int dice, int row, int col) throws NetworkException, NotValidException, RemoteException, NotValidPlayException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard9(player,dice,row,col,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard9(player,dice,row,col,true);
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard10(int dice) throws NetworkException, RemoteException, NotValidPlayException, NotValidException {
        if(player.getState().equals(PlayerState.TURNSTARTED)){
            match.useToolCard10(player,dice,false);
        }
        else if(player.getState().equals(PlayerState.USEDDICE)){
            match.useToolCard10(player,dice,true);
            System.out.println("aiutoo" );
            endTurn();
        }
        else throw new NotValidPlayException("Non puoi usare questa carta");
    }

    @Override
    public void useToolCard11(int dice) throws NetworkException, RemoteException, NotValidPlayException, NotValidException {

    }


}
