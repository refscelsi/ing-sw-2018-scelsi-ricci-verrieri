package Progetto.Server;

import Progetto.Server.Exceptions.NotValidNicknameException;

import java.rmi.Remote;

public interface ServerInterface extends Remote{

    public void login(String nickname) throws NotValidNicknameException;

    public void joinMatch();

    public void exitMatch();

}
