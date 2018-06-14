package it.polimi.ing.sw.server;


public class NotValidNicknameException extends RuntimeException {

    public NotValidNicknameException(String message){
        super(message);
    }

}
