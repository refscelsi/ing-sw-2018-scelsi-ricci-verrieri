package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.rmi.Remote;
import java.util.ArrayList;

//interfaccia remota, implementata da PlayerController, che riceve gli aggiornamenti dal Model
public interface UiUpdate {

    public void onLogin (String message);

    public void onSuccess (String message);

    public void onActionNotValid (String errorCode);

    public void onChooseNetwork (String message);

    public void onTurnStart (Match match, String nickname);

    public void onPlaceDiceNotValid (NotValidException e);

    public void onTurnEnd ();

    public void onGameUpdate (Match match, String nickname);

    public void onGameEnd (Match match);

    public void onSchemeToChoose (Match match, String nickname, String message);

    public void onUseToolCard1NotValid (Match match, NotValidException e);

    public void onUseToolCard234NotValid(int id, Match match, NotValidException e);

    public void onOtherInfoToolCard4(Match match);
}
