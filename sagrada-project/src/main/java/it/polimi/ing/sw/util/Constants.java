package it.polimi.ing.sw.util;

public class Constants {

    // Generali
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 5;
    public static final int NUM_ROUNDS = 3;
    public static final int MAX_PLAYERS = 4;

    // Connessione
    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SOCKET_PORT = 1097;
    public static final int RMI_PORT = 1099;
    public static final int MAX_CONNECTION_ATTEMPTS = 4;
    public static final int CONNECTION_RETRY_SECONDS = 2;
    public static final boolean START_AS_SERVER_IF_CLIENT_CONNECTION_FAILS = false;

    //metodi PlayerInterface

    public static final String JOINMATCH= "joinMatch";
    public static final String CHECKREADY= "checkAllReady";
    public static final String SETCHOSENSCHEME= "setChosenScheme";
    public static final String USEDICEREQUEST= "sendUseDiceRequest";
    public static final String ENDTURN= "endTurn";
    public static final String TOOLCARD1= "sendUseToolCard1Request";
    public static final String TOOLCARD234 = "sendUseToolCard234Request";
    public static final String TOOLCARD5 ="useToolCard5";
    public static final String TOOLCARD6 ="useToolCard6";
    public static final String TOOLCARD78= "useToolCard78";
    public static final String TOOLCARD9 ="sendUseToolCard9Request";
    public static final String TOOLCARD10= "useToolCard10";
    public static final String TOOLCARD11= "useToolCard11";

    //metodi PlayerController
    public static final String ONSCHEMETOCHOOSE="onSchemeToChoose";
    public static final String ONSUCCES= "onSuccess";
    public static final String ONGAMEUPDATE= "onGameUpdate";
    public static final String ONTURNEND= "onTurnEnd";
    public static final String ONPLAYERLOGGED= "onPlayerLogged";
    public static final String ONSETPLAYING= "onSetPlaying";
    public static final String ONOTHERINFOTOOLCARD4="onOtherInfoToolCard4";
    public static final String ONOTHERINFOTOOLCARD11= "onOtherInfoToolCard11";
    public static final String ONOTHERINFOTOOLCARD12= "onOtherInfoToolCard12";
    public static final String ONGAMEEND= "onGameEnd";



}
