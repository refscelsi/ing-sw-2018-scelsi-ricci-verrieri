package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.ui.cli.*;
import it.polimi.ing.sw.util.Constants;

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
	public void chooseAction( Match match, String nickname ) {
		boolean ok;
		System.out.print( "Digita: \n- D se vuoi posizionare un dado sul tuo schema; \n- T se vuoi utilizzare una carta utensile; \n- I se vuoi visualizzare le informazioni degli altri giocatori; \n- E se vuoi terminare il tuo turno; \n- Q se vuoi uscire dalla partita.\n" );

		do {
			ok = true;
			inText = scanner.nextLine();

			switch (inText.toLowerCase()) {
				case "q":
					System.out.println( "Sei sicuro che vuoi uscire dalla partita? Digita S per sì o N per no." );
					if ( scanner.nextLine().toLowerCase().equalsIgnoreCase( "s" ) ) {
						// TODO: gestire terminazione corretta del programma!
						System.out.println( "Uscendo dalla partita..." );
						System.exit( 0 );
					}
					break;
				case "d":
					handleUseDice( match, false );
					break;
				case "t":
					handleUseToolCard( match );   //TODO: metodi per le carte utensili
					break;

				case "e":
					endTurn();
					break;
				default:
					System.out.println( "Scelta non valida" );
					ok = false;
					break;
			}
		} while (!ok);
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

		/*
		int dice, row, col;
		do {
			System.out.println( "Digita l'indice del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		do {
			System.out.println( "Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS );
			row = scanner.nextInt();
		} while (row < 1 || row > Constants.NUM_ROWS);
		do {
			System.out.println( "Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS );
			col = scanner.nextInt();
		} while (col < 1 || col > Constants.NUM_COLS);
//*/
//		if ( toolCard9 )
//			controller.useToolCard( 9, dice - 1, -1, row - 1, col - 1, -1, -1 );
//		else
//			controller.useDice( dice - 1, row - 1, col - 1 );

	}


	public void retryPlaceDice() {
		//TODO popup di errore

		//TODO reset prec status
		int row, col;
		do {
			System.out.println( "Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS );
			row = scanner.nextInt();
		} while (row < 1 || row > Constants.NUM_ROWS);
		do {
			System.out.println( "Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS );
			col = scanner.nextInt();
		} while (col < 1 || col > Constants.NUM_COLS);
		controller.useDice( -1, row - 1, col - 1 );
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Scelta T: utilizzare una toolcard
	/////////////////////////////////////////////////////////////////////////////////////////

	public void handleUseToolCard( Match match ) {
		int num;
		do {
			System.out.println( "Digita il numero della carta utensile che vuoi utilizzare, tra 1 e 3" );
			num = scanner.nextInt();
		} while (num < 1 || num > 3);
		int id = match.getToolCards().get( num - 1 ).getId();
		useToolCard( id, match );
	}

	public void useToolCard( int id, Match match ) {
		switch (id) {
			case 1:
				useToolCard1( match );
				break;
			case 2:
			case 3:
			case 4:
				useToolCard23412( id );
				break;
			case 5:
				useToolCard5( match );
				break;
			case 6:
				useToolCard6( match );
				break;
			case 7:
			case 8:
				controller.useToolCard( id, -1, -1, -1, -1, -1, -1 );
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


	public void useToolCard1( Match match ) {
		int dice, operation;
		do {
			System.out.println( "Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		do {
			System.out.println( "Digita 0 se vuoi aumentare il numero del dado di 1, 1 se vuoi diminuirlo" );
			operation = scanner.nextInt();
		} while ((operation != 0) && (operation != 1));
		controller.useToolCard( 1, dice - 1, operation, -1, -1, -1, -1 );
	}


	public void useToolCard23412( int id ) {
		int sourceRow, sourceCol, destRow, destCol;
		do {
			System.out.println( "Digita il numero della riga dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_ROWS );
			sourceRow = scanner.nextInt();
		} while (sourceRow < 1 || sourceRow > Constants.NUM_ROWS);
		do {
			System.out.println( "Digita il numero della colonna dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_COLS );
			sourceCol = scanner.nextInt();
		} while (sourceCol < 1 || sourceCol > Constants.NUM_COLS);
		do {
			System.out.println( "Digita il numero della riga dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_ROWS );
			destRow = scanner.nextInt();
		} while (destRow < 1 || destRow > Constants.NUM_ROWS);
		do {
			System.out.println( "Digita il numero della colonna dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_COLS );
			destCol = scanner.nextInt();
		} while (destCol < 1 || destCol > Constants.NUM_COLS);
		controller.useToolCard( id, -1, -1, sourceRow - 1, sourceCol - 1, destRow - 1, destCol - 1 );
	}


	public void useToolCard5( Match match ) {
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


	public void useToolCard6( Match match ) {
		int dice;
		do {
			System.out.println( "Digita l'indice del dado che vuoi tirare, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		controller.useToolCard( 6, dice - 1, -1, -1, -1, -1, -1 );
	}


	public void useToolCard9( Match match ) {
		handleUseDice( match, true );
	}


	public void useToolCard10( Match match ) {
		int dice;
		do {
			System.out.println( "Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		controller.useToolCard( 10, dice - 1, -1, -1, -1, -1, -1 );
	}

	public void useToolCard11( Match match ) {
		int dice;
		do {
			System.out.println( "Digita l'indice del dado che vuoi riporre nel sacchetto, tra 1 e " + match.getDraftPool().getSize() );
			dice = scanner.nextInt();
		} while (dice < 1 || dice > match.getDraftPool().getSize());
		controller.useToolCard( 11, dice - 1, -1, -1, -1, -1, -1 );
	}

	public void useToolCard12( Match match ) {
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

	public void endTurn() {
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
		//TODO popup not valid action
	}

	@Override
	public void onChooseNetwork( String message ) throws RemoteException {
		chooseNetwork( message );
	}

	@Override
	public void onTurnStart( Match match, String nickname ) {
		chooseAction( match, nickname );
	}

	@Override
	public void onPlaceDiceNotValid( NotValidException e ) {
		System.out.println( e );
		retryPlaceDice();
	}

	@Override
	public void onGameUpdate( Match match, String nickname ) throws RemoteException {
		if ( tableFrame == null ) {
			tableFrame = new TableFrame( match, getController() );
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
	public void onUseToolCardNotValid( int id, Match match, String e ) {
		System.out.println( e );
		switch (id) {
			case 6:
				onOtherInfoToolCard( 6, match );    // perché tanto la 6 può lanciare una notValidException solo nel secondo step
				break;
			case 11:
				onOtherInfoToolCard( 11, match );   // perché tanto la 11 può lanciare una notValidException solo nel secondo step
				break;
			case 12:
				onOtherInfoToolCard( 12, match );   //TODO: distingui eccezione se ti trovi nel primo step o nel secondo
				break;
			default:
				useToolCard( id, match );
				break;
		}
	}

	@Override
	public void onOtherInfoToolCard( int id, Match match ) {
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