package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.*;

import java.rmi.Remote;
import java.util.ArrayList;

//interfaccia remota, implementata da ClientController, che riceve gli aggiornamenti dal Model
public interface ClientUpdate extends Remote {

    public void onActionNotValid (String errorCode);

    public void onTurnStarted (Match match, int index);

    public void onGameUpdate (Match match, int index);

    public void onGameEnd (Match match);

    public void onSchemeToChoose (Match match, int index);

}
