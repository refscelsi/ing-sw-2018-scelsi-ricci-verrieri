package it.polimi.ing.sw.controller;

/**
 * Possibili stati del giocatore
 */

public enum PlayerState {
    READYTOPLAY, /*ENDEDTURN,*/ USEDDICE, USEDTOOLCARD, INIZIALIZED,
    /*FINISHTURN,*/ SCHEMETOCHOOSE, USEDDICETOOLCARD, FIRSTSTEPTOOLCARD, TURNSTARTED,
}
