package it.polimi.ing.sw.controller.exceptions;



/**
 * Eccezione che viene lanciata quando il giocatore non può eseguire la mossa desiderata; ad esempio se vuole
 * piazzare un dado ma ne ha già piazzato uno nello stesso turno o se vuole utilizzare una carta utensile
 * ma non ha abbastanza segnalini favore.
 */

public class NotValidPlayException extends Exception {
    public NotValidPlayException(String message) {
        super(message);
    }
}
