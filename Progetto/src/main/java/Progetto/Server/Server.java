package Progetto.Server;

import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.Match;
import Progetto.Server.Controller.TurnController;
import Progetto.Server.Exceptions.NotValidNicknameException;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Server implements ServerInterface{
    private Match match;
    private ArrayList<String> userList;
    private TurnController turnController;
    private int port = 1099;

    public Server(){
        match= new Match();
        userList= new ArrayList<String >();
    }

    Registry registry;

    public void startMatch() throws ToolCardException, NotValidException {
        if(userList.size()== 4){ //oppure timeout scaduto
            match.initializeTable();
            match.inizializeToolCard();
            match.inizializePlayer(); //cosa fa esattamente questo metodo?
        }

    }

    public void startServer() {
        {
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                registry.bind("userController", (Remote) this);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
            try {
                UnicastRemoteObject.exportObject(this, port);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean checkNickname(String nickname){
        for(String s: userList){
            if(s.equals(nickname)){
                return false;
            }
        }
        return true;
    }


    @Override
    public void login(String nickname) throws NotValidNicknameException {
        if(checkNickname(nickname)){
            userList.add(nickname);
            match.createNewPlayer(nickname);
        }
    }

    @Override
    public void joinMatch() {
    }

    @Override
    public void exitMatch() {

    }























    /*private Match match;
    private TurnController turnController;
    private View view;

    public Server(View view) {
        this.view = view;
    }

    public void newPlayer(String nickname) {
        match.createNewPlayer(nickname);
    }

    public void run() throws ToolCardException, NotValidException {

        match = new Match();

        //aspetto per un certo tempo durante il quale mi arrivano i nomi dei giocatori dalla view e creo i giocatori:

        match.initializeTable();
        match.inizializePlayer();

        //ora sono inizializzati tavolo e giocatori, quindi posso iniziare il match

        for (int j = 0; j < 10; j++) {

            match.startRound();
            this.turnController =new TurnController(this.getMatch(),this.getView(),match.getPlayerPlaying());

            //è iniziato un round e ha giocato il primo giocatore. Il primo giocatore passa il round (metodo changePlayer del ServerContoller):

            for (int i = 1; i < match.getNumPlayers() * 2; i++)
                match.changePlayer();

            match.endRound();
        }

        match.endMatch();

    }

    public Match getMatch() {
        return match;
    }

    public View getView() {
        return view;
    }*/
}
