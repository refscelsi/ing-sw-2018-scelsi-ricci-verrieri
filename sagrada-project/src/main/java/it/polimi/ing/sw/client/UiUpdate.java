package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.*;

import java.rmi.Remote;
import java.util.ArrayList;

//interfaccia remota, implementata da PlayerController, che riceve gli aggiornamenti dal Model
public interface UiUpdate {

    public void onLogin(String message);

    public void onSuccess(String message);

    public void onActionNotValid (String errorCode);

    public void onTurnStarted (Match match, int index);

    public void onGameUpdate (Match match, int index);

    public void onGameEnd (Match match);

    public void onSchemeToChoose (Match match, int index);

}
