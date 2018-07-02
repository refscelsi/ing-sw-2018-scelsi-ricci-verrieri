package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.ui.cli.ShowRoundTrack;
import it.polimi.ing.sw.ui.gui.toolCardsActrionFrames.ToolCard1ActionForm;
import it.polimi.ing.sw.util.Constants;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI implements UiUpdate {

	public Scanner scanner = new Scanner( System.in );
	public String inText;

	private TableFrame tableFrame;
	private View controller;

	public GUI( View controller ) {
		this.controller = controller;
	}

	public View getController() throws RemoteException {
		if ( controller == null )
			controller = new View();
		return controller;
	}

	public void chooseNetwork( String message ) throws RemoteException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ( "Nimbus".equals( info.getName() ) ) {
					javax.swing.UIManager.setLookAndFeel( info.getClassName() );
					break;
				}
			}
		} catch ( ClassNotFoundException ex ) {
			java.util.logging.Logger.getLogger( ToolCard1ActionForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( InstantiationException ex ) {
			java.util.logging.Logger.getLogger( ToolCard1ActionForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( IllegalAccessException ex ) {
			java.util.logging.Logger.getLogger( ToolCard1ActionForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
			java.util.logging.Logger.getLogger( ToolCard1ActionForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		}

		NewPlayerForm newPlayerForm = new NewPlayerForm( getController() );
		newPlayerForm.setVisible( true );
	}

	public void chooseScheme( Match match, String nickname, String message ) throws RemoteException {
		ArrayList<Scheme> schemes = match.getPlayer( nickname ).getSchemesToChoose();
		showSchemesToChoose( schemes );
	}

	public void showSchemesToChoose( ArrayList<Scheme> schemes ) throws RemoteException {
		ChooseSchemeForm chooseSchemeForm = new ChooseSchemeForm( schemes, getController() );
		chooseSchemeForm.setVisible( true );
	}

	/**
	 * Scelta dell'azione da parte del giocatore
	 */
	public void chooseAction( Match match, String nickname ) throws RemoteException {
		//TODO add action handling

		onGameUpdate( match, nickname );
	}


	/////////////////////////////////////////////////////////////////////////////////////////
	// Connessione e disconnessione del Client --> da fare probabilmente
	/////////////////////////////////////////////////////////////////////////////////////////

    /*public void clientConnection() {
        PlayerController controller = getController();
        controller.clientConnection();
    }

    public void clientDisconnection() {
        PlayerController controller = getController();
        controller.clientDisconnection();
    }*/

	/////////////////////////////////////////////////////////////////////////////////////////
	// Scelta D: posizionare un dado sullo schema
	/////////////////////////////////////////////////////////////////////////////////////////

	public void handleUseDice( Match match, boolean toolCard9 ) {

		//handled by GUI

	}


	public void retryPlaceDice() throws RemoteException {
		JOptionPane.showMessageDialog( null,
				"Invalid placing dice action, retry or do something different.",
				"Placing dice error",
				JOptionPane.ERROR_MESSAGE );

		onGameUpdate( controller.getMatch(), controller.getNickname() );
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Scelta T: utilizzare una toolcard
	/////////////////////////////////////////////////////////////////////////////////////////

	public void handleUseToolCard( Match match ) throws RemoteException {
		//gestito da gui
	}

	public void useToolCard( int id, Match match ) throws RemoteException {
		switch (id) {
			case 5:
				useToolCard5( match );
				break;
			case 7:
				break;
			case 9:
				useToolCard9( match );
				break;
			case 10:
				useToolCard10( match );
				break;
			case 11:
				useToolCard11( match );
				break;
			case 12:
				useToolCard12( match );
				break;
			default:
				break;
		}
	}




	public void useToolCard5( Match match ) throws RemoteException {
		int dice, round, indexInRound;
		boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
		if ( !roundTrackIsFull ) {
			System.out.println( "Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round" );
			chooseAction( match, controller.getNickname() );
		} else {
			do {
				System.out.println( "Digita l'indice del dado della riserva che vuoi scambiare, tra 1 e " + match.getDraftPool().getSize() );
				dice = scanner.nextInt();
			} while (dice < 1 || dice > match.getDraftPool().getSize());
			do {
				System.out.println( "Digita il numero di round a cui appartiene il dado con cui vuoi scambiarlo, tra 1 e " + match.getRoundTrack().getRoundTrackSize() );
				round = scanner.nextInt();
			} while (round < 1 || round > match.getRoundTrack().getRoundTrackSize());
			do {
				System.out.println( "Digita l'indice del dado nel round che hai scelto, tra 0 e " + (match.getRoundTrack().getNumberOfDices( round ) - 1) );
				indexInRound = scanner.nextInt();
			} while (indexInRound < 0 || indexInRound > match.getRoundTrack().getNumberOfDices( round ) - 1);
			controller.useToolCard( 5, dice - 1, -1, round, indexInRound, -1, -1 );
		}
	}




	public void useToolCard9( Match match ) {
		handleUseDice( match, true );
	}


	public void useToolCard10( Match match ) throws RemoteException {
		int dice;
		do {
			System.out.println( "Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		controller.useToolCard( 10, dice - 1, -1, -1, -1, -1, -1 );
	}

	public void useToolCard11( Match match ) throws RemoteException {
		int dice;
		do {
			System.out.println( "Digita l'indice del dado che vuoi riporre nel sacchetto, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		controller.useToolCard( 11, dice - 1, -1, -1, -1, -1, -1 );
	}

	public void useToolCard12( Match match ) throws RemoteException {
		boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
		if ( !roundTrackIsFull ) {
			System.out.println( "Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round" );
			chooseAction( match, controller.getNickname() );
		} else {
			useToolCard23412( 12 );
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Scelta E: terminare il turno
	/////////////////////////////////////////////////////////////////////////////////////////

	public void endTurn() throws RemoteException {
		controller.endTurn();
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi che invoca PlayerController su UiUpdate
	/////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void onLogin( String message ) {
		//managed by gui
	}

	@Override
	public void onActionNotValid( String errorCode ) {
		System.out.println( errorCode );

		JOptionPane.showMessageDialog( null,
				errorCode,
				"Not valid Action",
				JOptionPane.ERROR_MESSAGE );
	}

	@Override
	public void onChooseNetwork( String message ) throws RemoteException {
		chooseNetwork( message );
	}

	@Override
	public void onTurnStart( Match match, String nickname ) throws RemoteException {
		chooseAction( match, nickname );
	}

	@Override
	public void onPlaceDiceNotValid( NotValidException e ) throws RemoteException {
		System.out.println( e );
		retryPlaceDice();
	}

	@Override
	public void onGameUpdate( Match match, String nickname ) throws RemoteException {
		if ( tableFrame == null ) {
			tableFrame = new TableFrame( match, getController() );
			tableFrame.setVisible( true );
		}

		TableFrame.updateMatch( match );
	}

	@Override
	public void onGameEnd( Match match ) {
		//TODO fare tabellone di fine gioco

		ShowRoundTrack roundTrack = new ShowRoundTrack( match.getRoundTrack() );   //TODO: mettere pedine su roundtrack
		for (int i = 0; i < match.getNumPlayers(); i++) {
			System.out.print( i + 1 + ") " + match.getRanking().get( i ).getNickname() + " con " );
			System.out.println( match.getRanking().get( i ).getScore() + " punti" );
		}
	}

	@Override
	public void onSchemeToChoose( Match match, String nickname, String message ) throws RemoteException {
		chooseScheme( match, nickname, message );
	}

	@Override
	public void onUseToolCardNotValid( int id, Match match, String errorCode ) throws RemoteException {
		System.out.println( errorCode );
		onGameUpdate( match, controller.getNickname() );
		JOptionPane.showMessageDialog( null,
				errorCode,
				"Not valid Action",
				JOptionPane.ERROR_MESSAGE );
	}

	@Override
	public void onOtherInfoToolCard( int id, Match match ) throws RemoteException {
		switch (id) {
			case 4: {
				System.out.println( "Primo dado mosso correttamente, ora muovi il secondo" );
				useToolCard23412( 4 );
			}
			break;
			case 6: {
				System.out.println( "Ora digita la casella dove posizionare il dado" );
				retryPlaceDice();
			}
			break;
			case 11: {
				int dice, row, col;
				do {
					System.out.println( "Digita il valore del nuovo dado, tra 1 e 6" );
					dice = scanner.nextInt();
				} while (dice < 1 || dice > 6);
				do {
					System.out.println( "Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS );
					row = scanner.nextInt();
				} while (row < 1 || row > Constants.NUM_ROWS);
				do {
					System.out.println( "Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS );
					col = scanner.nextInt();
				} while (col < 1 || col > Constants.NUM_COLS);
				controller.useToolCard( 11, -1, dice, row - 1, col - 1, -1, -1 );
			}
			break;
			case 12: {
				int choice;
				do {
					System.out.println( "Primo dado mosso correttamente, digita 0 se non vuoi spostare più dadi o 1 se vuoi spostarne un altro" );
					choice = scanner.nextInt();
				} while (choice != 0 && choice != 1);
				if ( choice == 0 )
					controller.useToolCard( 12, -1, -1, -1, -1, -1, -1 );
				else
					useToolCard23412( 12 );
			}
		}
	}

	@Override
	public void onSuccess( String message ) {
		System.out.println( message );
		// todo popup successo?
	}

}