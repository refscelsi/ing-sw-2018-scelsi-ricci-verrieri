package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;
import com.google.gson.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.String.valueOf;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json
public class PlayerControllerInterfaceSocket implements PlayerControllerInterface {
    private MatchToSend match;
    private View view;
    private final Socket clientSocket;
    private final ObjectOutputStream out;



    public PlayerControllerInterfaceSocket(String nickname, View view) throws IOException {
        this.clientSocket= new Socket("localhost", Constants.SOCKET_PORT);
        this.out= new ObjectOutputStream(clientSocket.getOutputStream());
        this.view=view;
        new Thread(()->{
            serverUpdateHandler();
        }).start();

		Gson gson = new Gson();
		String json = gson.toJson( new Data( "connectSocket", new ArrayList<>( Collections.singletonList( nickname ) ) ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

    private void serverUpdateHandler() {
        try {
            ObjectInputStream in= new ObjectInputStream(clientSocket.getInputStream());
            Boolean condition = true;
            while (condition){
                this.match=(MatchToSend) in.readObject();
                String method= match.getMethod();
                handleUpdate(method);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate(String method) throws RemoteException {
		switch (method) {
			case Constants.ONTURNEND: {
				view.onTurnEnd();
				break;
			}
			case Constants.ONSUCCES: {
				try {
					view.onSuccess( match.getMessage() );
				} catch ( RemoteException e ) {
					e.printStackTrace();
				}
				break;
			}
			case Constants.ONSETPLAYING: {
				view.onSetPlaying();
				break;
			}
			case Constants.ONSCHEMETOCHOOSE: {
				view.onSchemeToChoose( match.getMatch() );
				break;
			}
			case Constants.ONGAMEUPDATE: {
				view.onGameUpdate( match.getMatch() );
				break;
			}
			case Constants.ONGAMEEND: {
				view.onGameEnd( match.getMatch() );
				break;
			}
		}
	}

	public void joinMatch() throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson( new Data( "joinMatch", null ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void checkAllReady() {
		Gson gson = new Gson();
		String json = gson.toJson( new Data( valueOf( Constants.CHECKREADY ), null ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void setChosenScheme( int id ) throws NetworkException, NotValidPlayException {
		Gson gson = new Gson();
		ArrayList<String> par = new ArrayList<>( Arrays.asList( "" + id ) );
		String json = gson.toJson( new Data( valueOf( Constants.SETCHOSENSCHEME ), par ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void sendUseDiceRequest( int indexOfDiceInDraftPool, int row, int col ) throws NetworkException, NotValidException, NotValidPlayException {
		Gson gson = new Gson();
		ArrayList<String> par = new ArrayList<>( Arrays.asList( "" + indexOfDiceInDraftPool + row + col ) );
		String json = gson.toJson( new Data( Constants.USEDICEREQUEST, par ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void endTurn() throws NetworkException, NotValidPlayException {
		Gson gson = new Gson();
		String json = gson.toJson( new Data( Constants.ENDTURN, null ) );
		try {
			out.writeObject( json );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException {
        Gson gson= new Gson();
        ArrayList<String> par=new ArrayList<>(Arrays.asList("" + indexOfDiceInDraftPool+row+col));
        String json=gson.toJson(new Data(Constants.USEDICEREQUEST, par));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endTurn() throws NetworkException, NotValidPlayException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.ENDTURN, null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.TOOLCARD, null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





