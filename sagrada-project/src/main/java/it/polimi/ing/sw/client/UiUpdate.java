package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;


/**
 * Interfaccia implementata sia dalla CLI che dalla GUI, contiene i metodi di
 * aggiornamento chiamati da View alla user interface utilizzata dal giocatore.
 * In caso di utilizzo di CLI i metodi sono direttamente implementati dalla CLI, in caso di GUI
 * i metodi sono direttamente implementati da GUI.
 */

public interface UiUpdate {

    void onLogin(String message);

    void onSuccess(String message);

    void onActionNotValid(String errorCode);

    void onChooseNetwork (String message);

    void onTurnStart(Match match, String nickname);

    void onPlaceDiceNotValid(String message);

    void onGameUpdate(Match match, String nickname);

    void onGameEnd(Match match);

    void onSchemeToChoose(Match match, String nickname, String message);

    void onUseToolCardNotValid(int id, Match match, String message);

    //void onUseToolCard12IIStepNotValid(Match match, String e);

    void onOtherInfoToolCard(int id, Match match);

    void onPlayerDisconnection(String message, String nickname);

}
