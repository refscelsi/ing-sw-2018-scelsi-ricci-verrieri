package it.polimi.ing.sw.ui.gui.model;

import it.polimi.ing.sw.model.Dice;

class FloatingDiceFrame extends javax.swing.JWindow {

	private int dimX;
	private int dimY;

	private DiceGUI diceGUI;

	FloatingDiceFrame( Dice dice, int dimX, int dimY ) {
		this.dimY = dimY;
		this.dimX = dimX;

		initComponents();

		diceGUI = new DiceGUI( false, dimX, dimY );
		add( diceGUI );
		diceGUI.setBounds( 0, 0, dimX, dimY );

		diceGUI.setDice( dice );
	}

	private void initComponents() {

		setMaximumSize( new java.awt.Dimension( dimX, dimY ) );
		setMinimumSize( new java.awt.Dimension( dimX, dimY ) );
		setOpacity( 0.5F );
		getContentPane().setLayout( null );

		pack();
	}

	public Dice getDice() {
		return diceGUI.getBox().getDice();
	}
}
