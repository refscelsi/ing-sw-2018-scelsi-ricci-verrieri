package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.toolCard.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe serializzabile contentente le informazioni necessarie al client, quindi il nome del metodo chiamato,
 * ed eventuali parametri.
 *
 * L'attributo Match si riferirà eventualmente ad un clone del Match.
 */

public class MessageFromServer implements Serializable {
    private String method;
    private Match match;
    private String message;
    private int id;

    /**
     * Diversi costruttori in base al tipo di messaggio e di parametri da inviare
     *
     */

    public MessageFromServer(String method){
        this.method=method;
    }

    public MessageFromServer(String method, Match match){
        this.method=method;
        this.match=match;
    }
    public MessageFromServer(String method, String message){
        this.method=method;
        this.message=message;
    }

    public MessageFromServer(String method, String message, int id){
        this.method=method;
        this.message=message;
        this.id=id;
    }

    /**
     * Metodi SET per settare i parametri
     *
     */


    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodi GET necessari Lato Client per estrapolare le informazioni serializzate
     *
     */

    public Match getMatch() {
        return this.match;
    }

    public String getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }


}
