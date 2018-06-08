package it.polimi.ing.sw.server;

import it.polimi.ing.sw.server.exceptions.NotValidNicknameException;


import java.rmi.Remote;

public interface ServerInterface extends Remote{

    public void login(String nickname) throws NotValidNicknameException;

    public void joinMatch();

    public void exitMatch();

}
