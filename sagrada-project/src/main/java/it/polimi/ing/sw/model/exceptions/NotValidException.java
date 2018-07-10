
package it.polimi.ing.sw.model.exceptions;



/**
 * Eccezione che viene lanciata quando il giocatore esegue una mossa in modo incorretto. Ad esempio se tenta di
 * piazzare un dado su una casella piena.
 */

public class NotValidException extends Exception {
    public NotValidException(String message) {
        super(message);
    }
}
