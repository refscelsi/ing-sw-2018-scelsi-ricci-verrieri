package it.polimi.ing.sw.server;


public class NotValidNicknameException extends Exception {
    public NotValidNicknameException(String message){
        super(message);
    }
}
