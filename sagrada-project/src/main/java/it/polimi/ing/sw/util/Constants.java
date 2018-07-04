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

    /**
     * Metodi del PlayerInterface
     */

    public static final String JOINMATCH= "joinMatch";
    public static final String CHECKREADY= "checkAllReady";
    public static final String SETCHOSENSCHEME= "setChosenScheme";
    public static final String USEDICEREQUEST= "sendUseDiceRequest";
    public static final String ENDTURN= "endTurn";
    public static final String TOOLCARD= "useToolCard";
    public static final String STOPPLAYER= "stopPlayer";
    public static final String LOGIN="login";

    /**
     * JSON
     */
    public static final String METHOD="method";
    public static final String ID="id";
    public static final String INDEXDICE= "indexDice";
    public static final String ROW = "row";
    public static final String COL= "col";
    public static final String DESTROW= "destRow";
    public static final String DESTCOL = "destCol";
    public static final String NICKNAME= "nickname";
    public static final String OPERATION = "operation";




    /**
     * Metodi del RemotePlayer
     */
    public static final String CONNECT="connectSocket";
    public static final String ONSCHEMETOCHOOSE="onSchemeToChoose";
    public static final String ONSUCCES= "onSuccess";
    public static final String ONGAMEUPDATE= "onGameUpdate";
    public static final String ONTURNEND= "onTurnEnd";
    public static final String ONSETPLAYING= "onSetPlaying";
    public static final String ONOTHERINFOTOOLCARD="onOtherInfoToolCard";
    public static final String ONGAMEEND= "onGameEnd";
    public static final String ONNOTVALIDUSEDICEEXCEPTION="onNotValidUseDiceException";
    public static final String ONNOTVALIDTOOLCARDEXCEPTION ="onNotValidToolCardException";
    public static final String ONNOTVALIDPLAYEXCEPTION= "onNotValidPlayException";
    public static final String ONNOTVALIDNICKNAMEEXCEPTION ="onNotValidNicknameException";
    public static final String ONNOTPOSSIBLECONNECTIONEXCEPTION ="onNotPossibleConnectionException";
    public static final String ONLOGIN= "onLogin";

    //GUI
    public static final String PLAYER_STANDARD_NAME = "Player";
    public static final String IMAGE_PATH = "/img/dices/";
    public static final String SAGRADA_ICO = "/img/sagrada.png";
    public static final String SOCKET_CODE = "s";
    public static final String RMI_CODE = "r";
    public static final String DEFAULT_TITLE = "Sagrada Boardgame";
    public static final String NOT_A_DICE = "notADice";

}
