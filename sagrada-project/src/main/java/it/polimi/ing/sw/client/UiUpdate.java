package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;

//interfaccia remota, implementata da PlayerController, che riceve gli aggiornamenti dal Model
public interface UiUpdate {

    void onLogin(String message);

    void onSuccess(String message);

    void onActionNotValid(String errorCode);

    void onChooseNetwork (String message);

    void onTurnStart(Match match, String nickname);

    void onPlaceDiceNotValid(NotValidException e);

    void onGameUpdate(Match match, String nickname);

    void onGameEnd(Match match);

    void onSchemeToChoose(Match match, String nickname, String message);

    void onUseToolCardNotValid(int id, Match match, String e);

    void onOtherInfoToolCard(int id, Match match);

}
