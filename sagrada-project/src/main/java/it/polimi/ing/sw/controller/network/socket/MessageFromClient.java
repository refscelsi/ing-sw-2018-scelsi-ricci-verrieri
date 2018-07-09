package it.polimi.ing.sw.controller.network.socket;

/**
 * Classe di supporto per la creazione di stringhe Json che vengono inviate dal Client(PlayerControllerSocketClient)
 * al Server (PlayerControllerSocketServer)
 * in caso di connesione Socket
 */

public class MessageFromClient {
    /**
     * lista di possibili informazioni da inserire nel messaggio, necessari per
     * il recupero delle informazioni lato Server
     */
    String method;
    String nickname;
    int indexDice;
    int row;
    int col;
    int destRow;
    int destCol;
    int id;
    int dice;
    int operation;

    /**
     * Diversi costruttori in base al tipo di messaggio che devo mandare
     */


    public MessageFromClient(String method, String nickname){
        this.method = method;
        this.nickname=nickname;
    }

    public MessageFromClient(String method, int indexDice, int row, int col) {
        this.method = method;
        this.indexDice=indexDice;
        this.row=row;
        this.col=col;
    }

    public MessageFromClient(String method){
        this.method=method;
    }

    public MessageFromClient(String method, int id){
        this.method=method;
        this.id=id;
    }

    public MessageFromClient(String method, int id, int dice, int operation, int row, int col, int destRow, int destCol) {
        this.method = method;
        this.id=id;
        this.dice=dice;
        this.operation=operation;
        this.row=row;
        this.col=col;
        this.destRow=destRow;
        this.destCol=destCol;
    }


}

