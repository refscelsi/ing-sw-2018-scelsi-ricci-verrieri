package it.polimi.ing.sw.server;

import it.polimi.ing.sw.controller.LoginController;
import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.Socket.PlayerControllerSocket;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static it.polimi.ing.sw.util.Constants.SOCKET_PORT;
//classe che fa partire il server e farà partire il timer
//crea subito un match e un controller, sarà poi il controller una volta che ha ricevuto 4 connessioni a fare match.start()
//oppure facciamo che il server crea la partita solo dopo aver ricevuto 4 connessioni?? da decidere

public class Server {
    public static void main(String args[]){
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Match match= new Match();
            LoginController loginControllerController = new LoginController(match);
            reg.rebind("LoginController", (Remote) loginControllerController);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Server on...");

        /*new Thread(()->{
            try(ServerSocket serverSocket=new ServerSocket(SOCKET_PORT)){
                while(true){
                    socket clientSocket= serverSocket.accept();
                    new Thread(()->{
                        try {
                            new PlayerControllerSocket(clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ToolCardException e) {
                            e.printStackTrace();
                        } catch (NotValidException e) {
                            e.printStackTrace();
                        } catch (NetworkException e) {
                            e.printStackTrace();
                        } catch (NotValidPlayException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}

