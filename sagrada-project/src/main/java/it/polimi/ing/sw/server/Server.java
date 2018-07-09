package it.polimi.ing.sw.server;

import it.polimi.ing.sw.controller.ConnectionController;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerSocketServer;
import it.polimi.ing.sw.model.Match;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static it.polimi.ing.sw.util.Constants.SERVER_ADDRESS;
import static it.polimi.ing.sw.util.Constants.SOCKET_PORT;

/**
 * Classe che lancia il Server ed istanzia un Match.
 * Rimane in attesa di connessioni, sia in RMI che in Socket
 */

public class Server {
    public static void main(String args[]){
        if(null!=args[0]){
            SERVER_ADDRESS=args[0];
        }
        try {
            System.setProperty("java.rmi.server.hostname",SERVER_ADDRESS);
            Registry reg = LocateRegistry.createRegistry(1099);
            Match match= new Match();
            ConnectionController connectionController = new ConnectionController(match);
            reg.rebind("ConnectionController", (Remote) connectionController);
            new Thread(()->{
                try(ServerSocket serverSocket=new ServerSocket(SOCKET_PORT)){
                    while(true){
                        Socket clientSocket= serverSocket.accept();
                        PlayerControllerSocketServer controllerSocket = new PlayerControllerSocketServer(clientSocket, connectionController);
                        new Thread(controllerSocket).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Server on...");

    }
}

