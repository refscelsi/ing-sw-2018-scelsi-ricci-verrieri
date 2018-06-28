package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;

//interfaccia remota, implementata da PlayerController, che riceve gli aggiornamenti dal Model
public interface UiUpdate {

    void onLogin(String message);

    void onSuccess(String message);

    void onActionNotValid(String errorCode);

    //public void onChooseNetwork (String message);

    void onTurnStart(Match match, String nickname);

    void onPlaceDiceNotValid(NotValidException e);

    void onTurnEnd();

    void onGameUpdate(Match match, String nickname);

    void onGameEnd(Match match);

    void onSchemeToChoose(Match match, String nickname, String message);

    void onUseToolCard1NotValid(Match match, NotValidException e);

    void onUseToolCard234NotValid(int id, Match match, NotValidException e);

    void onOtherInfoToolCard4(Match match);

    void onToolCard6(Match match);

    void onUseToolCard9NotValid(Match match, NotValidException e);

    void onOtherInfoToolCard11(Match match);

    void onUseToolCard11bNotValid(Match match, NotValidException e);
}
