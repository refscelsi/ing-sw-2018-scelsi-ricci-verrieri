package it.polimi.ing.sw.network.server;

import it.polimi.ing.sw.network.server.exceptions.*;

import java.rmi.Remote;

public interface ServerInterface extends Remote{

    public void login(String nickname, RemotePlayer remotePlayer ) throws NotValidNicknameException;

    public void joinMatch();

    public void exitMatch();

}
