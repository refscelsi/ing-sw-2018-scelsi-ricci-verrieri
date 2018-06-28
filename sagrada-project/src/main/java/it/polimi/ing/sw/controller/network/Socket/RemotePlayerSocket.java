package it.polimi.ing.sw.controller.network.Socket;

import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;

import java.rmi.RemoteException;


//classe che viene chiamata dal model, impacchetta i dati per passarli in Socket
public class RemotePlayerSocket implements RemotePlayer {
    @Override
    public void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException {

    }

    @Override
    public void onSuccess(String message) throws RemoteException {

    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {

    }

    @Override
    public void onTurnEnd() throws RemoteException {

    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {

    }

    @Override
    public void onPlayerLogged() throws RemoteException {

    }

    @Override
    public void onSetPlaying() throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard4(Match match) throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard11(Match match) throws RemoteException {

    }
}
