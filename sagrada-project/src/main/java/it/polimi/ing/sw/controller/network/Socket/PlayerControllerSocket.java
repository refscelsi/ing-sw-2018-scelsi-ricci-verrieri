package it.polimi.ing.sw.controller.network.Socket;

//classe che riceve i pacchetti da socket e chiama i metodi di PlayerController

import java.net.Socket;

public class PlayerControllerSocket {

    public PlayerControllerSocket(Socket clientSocket){
        handleSocket(clientSocket);
    }

    public void handleSocket(Socket socket){
        System.out.println("ci siamo ");
    }


}
