package it.polimi.ing.sw.model.exceptions;

/**
 * Eccezione scatenata quando sussiste un errore di comunicazione tra client e
 * server.
 */
public class NetworkException extends Exception {

    public NetworkException(String message) {
        super(message);
    }


}
