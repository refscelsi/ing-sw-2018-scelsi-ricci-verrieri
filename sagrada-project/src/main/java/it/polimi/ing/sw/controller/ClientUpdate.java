package it.polimi.ing.sw.controller;

import java.rmi.Remote;

//interfaccia remota, implementata da ClientController, che riceve gli aggiornamenti dal Model
public interface ClientUpdate extends Remote {

    public void update(Object update);
}
