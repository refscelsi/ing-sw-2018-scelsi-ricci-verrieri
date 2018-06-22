package it.polimi.ing.sw.server;

import it.polimi.ing.sw.controller.Game;
import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//classe che fa partire il server e farà partire il timer
//crea subito un match e un controller, sarà poi il controller una volta che ha ricevuto 4 connessioni a fare match.start()
//oppure facciamo che il server crea la partita solo dopo aver ricevuto 4 connessioni?? da decidere

public class Server {
    public static void main(String args[]){
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Match match= new Match();
            Game gameController = new Game(match);
            reg.rebind("Game", (Remote) gameController);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Server on...");
    }
}
