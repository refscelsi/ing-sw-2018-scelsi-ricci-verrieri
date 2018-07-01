package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.objectiveCard.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectiveCardDeck implements Serializable {
	private ArrayList<ObjectiveCard> deck;
	private ArrayList<ObjectiveCard> drawnCards;

	public ObjectiveCardDeck() {
		deck = new ArrayList<>();
		drawnCards = new ArrayList<>();
		setDeck();
	}

	public void setDeck() {
		ObjectiveCard oc = new DifferentColorRow();

		oc.setScore( 6 );
		oc.setName( "Colori diversi - Riga" );
		oc.setDescription( "Righe senza colori ripetuti" );
		oc.setId( 0 );
		deck.add( oc );

		oc = new DifferentColorColumn();
		oc.setScore( 5 );
		oc.setName( "Colori diversi - Colonna" );
		oc.setDescription( "Colonne senza colori ripetuti" );
		oc.setId( 1 );
		deck.add( oc );

		oc = new DifferentShadeRow();
		oc.setScore( 5 );
		oc.setName( "Sfumature diverse - Riga" );
		oc.setDescription( "Righe senza sfumature ripetute" );
		oc.setId( 2 );
		deck.add( oc );

		oc = new DifferentShadeColumn();
		oc.setScore( 4 );
		oc.setName( "Sfumature diverse - Colonna" );
		oc.setDescription( "Colonne senza sfumature ripetute" );
		oc.setId( 3 );
		deck.add( oc );

		oc = new Shades( 1 );
		oc.setScore( 2 );
		oc.setName( "Sfumature Chiare" );
		oc.setDescription( "Set di 1 & 2 ovunque" );
		oc.setId( 4 );
		deck.add( oc );

		oc = new Shades( 3 );
		oc.setScore( 2 );
		oc.setName( "Sfumature Medie" );
		oc.setDescription( "Set di 3 & 4 ovunque" );
		oc.setId( 5 );
		deck.add( oc );

		oc = new Shades( 5 );
		oc.setScore( 2 );
		oc.setName( "Sfumature Scure" );
		oc.setDescription( "Set di 5 & 6 ovunque" );
		oc.setId( 6 );
		deck.add( oc );

		oc = new DifferentShades();
		oc.setScore( 5 );
		oc.setName( "Sfumature Diverse" );
		oc.setDescription( "Set di dadi di ogni valore ovunque" );
		oc.setId( 7 );
		deck.add( oc );

		oc = new DifferentColors();
		oc.setScore( 4 );
		oc.setName( "Varietà di Colore" );
		oc.setDescription( "Set di dadi di ogni colore ovunque" );
		oc.setId( 8 );
		deck.add( oc );

		oc = new ColoredDiagonals();
		oc.setScore( 0 );
		oc.setName( "Diagonali Colorate" );
		oc.setDescription( "Numero di dadi dello stesso colore diagonalmente adiacenti" );
		oc.setId( 9 );
		deck.add( oc );
	}

	public ArrayList<ObjectiveCard> drawObjectiveCard() {
		Collections.shuffle( deck );
		for (int i = 0; i < 3; i++)
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
