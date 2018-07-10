package it.polimi.ing.sw.model.exceptions;



/**
 * Eccezione che viene lanciata quando un giocatore si logga con un nickname già in uso da un altro giocatore.
 */

public class NotValidNicknameException extends Exception {

    public NotValidNicknameException(String message) {
        super(message);
    }

}
