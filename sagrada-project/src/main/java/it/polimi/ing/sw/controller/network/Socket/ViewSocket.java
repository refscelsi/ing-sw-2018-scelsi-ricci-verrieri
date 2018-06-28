package it.polimi.ing.sw.controller.network.Socket;

//classe che viene chiamata da RemotePlayerSocket, prende i pacchetti, li spacchetta e chiama i metodi sulla view

import it.polimi.ing.sw.client.View;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ViewSocket  {
    private ObjectInputStream in;
    private Socket serverSocket;

    public ViewSocket(Socket serverSocket, ObjectInputStream objectInputStream){
        this.serverSocket=serverSocket;
        this.in= objectInputStream;
    }


}
