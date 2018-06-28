package it.polimi.ing.sw.controller.network.Socket;

//classe che riceve i pacchetti da socket e chiama i metodi di PlayerController

import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class PlayerControllerSocket  {
    ObjectInputStream in;
    Socket clientSocket;
    JSONParser parser= new JSONParser();
    JSONObject jsonObject;
    PlayerController controller;


    public PlayerControllerSocket(Socket clientSocket, PlayerController playerController) throws IOException, ToolCardException, NotValidException, NetworkException, NotValidPlayException {
        this.controller=playerController;
        this.clientSocket = clientSocket;
        handleSocket(clientSocket);
    }

    public void handleSocket(Socket socket) throws IOException, ToolCardException, NotValidException, NetworkException, NotValidPlayException {
        try(ObjectInputStream in= new ObjectInputStream( socket.getInputStream())) {
            while (true) {
                String input = (String) in.readObject();
                jsonObject = (JSONObject) parser.parse(input);
                ArrayList<String> data = (ArrayList<String>) jsonObject.get("data");
                System.out.println(data);
                handleInput(data);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void handleInput(ArrayList<String > data) throws RemoteException, NotValidException, ToolCardException, NotValidPlayException, NetworkException {
        String name=data.get(0);

        switch (name){
            case "joinMatch": controller.joinMatch();
            break;

            case "checkAllReady": controller.checkAllReady();
            break;

            case "setChosenScheme": controller.setChosenScheme(Integer.valueOf(data.get(1)));
            break;
        }

    }

}
