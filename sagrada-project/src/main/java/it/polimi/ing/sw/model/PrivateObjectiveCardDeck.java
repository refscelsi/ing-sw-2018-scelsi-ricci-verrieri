package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrivateObjectiveCardDeck implements Serializable {
	private ArrayList<PrivateObjectiveCard> deck;
	private ArrayList<PrivateObjectiveCard> drawnCards;

	public PrivateObjectiveCardDeck() {
		deck = new ArrayList<>();
		drawnCards = new ArrayList<>();
		setDeck();
	}

	public void setDeck() {
		PrivateObjectiveCard oc = new PrivateObjectiveCard( Color.RED );
		oc.setName( "Sfumature Rosse" );
		oc.setDescription( "Somma dei valori su tutti i dadi rossi" );
		oc.setId( 10 );
		deck.add( oc );

		oc = new PrivateObjectiveCard( Color.YELLOW );
		oc.setName( "Sfumature Gialle" );
		oc.setDescription( "Somma dei valori su tutti i dadi gialli" );
		oc.setId( 11 );
		deck.add( oc );

		oc = new PrivateObjectiveCard( Color.GREEN );
		oc.setName( "Sfumature Verdi" );
		oc.setDescription( "Somma dei valori su tutti i dadi verdi" );
		oc.setId( 12 );
		deck.add( oc );

		oc = new PrivateObjectiveCard( Color.BLUE );
		oc.setName( "Sfumature Blu" );
		oc.setDescription( "Somma dei valori su tutti i dadi blu" );
		oc.setId( 13 );
		deck.add( oc );

		oc = new PrivateObjectiveCard( Color.PURPLE );
		oc.setName( "Sfumature Viola" );
		oc.setDescription( "Somma dei valori su tutti i dadi viola" );
		oc.setId( 14 );
		deck.add( oc );
	}

	public ArrayList<PrivateObjectiveCard> drawObjectiveCard( int numPlayers ) {
		Collections.shuffle( deck );
		for (int i = 0; i < numPlayers; i++)
			drawnCards.add( deck.get( i ) );
		return drawnCards;
	}

	public int getSize() {
		return deck.size();
	}

	public int getDrawnCardsSize() {
		return drawnCards.size();
	}
}

