package it.polimi.ing.sw.controller.exceptions;


/**
 * Eccezione che viene lanciata quando non è possibile la connessione; ad esempio se la partita è già iniziata.
 */

public class NotPossibleConnectionException extends Exception {
    public NotPossibleConnectionException(String message) {
        super(message);
    }
}
