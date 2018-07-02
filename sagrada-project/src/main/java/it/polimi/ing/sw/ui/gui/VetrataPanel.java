package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;

public class VetrataPanel extends javax.swing.JPanel {

	private DiceGUI dices[][] = new DiceGUI[4][5];
	private Scheme scheme;
	private int idPlayer;
	private View controller;

	private static final int DIM_XDICE = 45;
	private static final int DIM_YDICE = 45;
	private int dimX;
	private int dimY;
	private int deltaX;

	public VetrataPanel(int idPlayer, View controller, int dimX, int dimY) {
		this.dimX=dimX;
		this.dimY=dimY;
		this.idPlayer=idPlayer;
		this.controller=controller;
		if ( 230==dimX ){
			deltaX=17;
		}else {
			deltaX=4;
		}
		initComponents();

		setUpSchemeField();
	}

	public void setDice( int i, int j, Dice dice ) {
		dices[i][j].setDice( dice );
	}

	public DiceGUI[][] getDices() {
		return dices;
	}

	private void setUpSchemeField() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				dices[i][j] = new DiceGUI( true, DIM_XDICE, DIM_YDICE );
				dices[i][j].setController( controller );
				imageLabel.add( dices[i][j] );
				dices[i][j].setBounds( deltaX + i * (DIM_XDICE + 5), 30 + j * (DIM_YDICE + 5), DIM_XDICE, DIM_YDICE );
				dices[i][j].setName( String.valueOf( idPlayer ).concat( String.valueOf( i ) ).concat( String.valueOf( j ) ) );
			}
		}
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		playerNameLabel = new javax.swing.JLabel();
		imageLabel = new javax.swing.JLabel();

		setAlignmentX( 0.0F );
		setAlignmentY( 0.0F );
		setLayout( null );

		playerNameLabel.setFont( new java.awt.Font( "Franklin Gothic Medium Cond", 1, 12 ) ); // NOI18N
		playerNameLabel.setForeground( new java.awt.Color( 255, 255, 255 ) );
		playerNameLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		playerNameLabel.setText( "ProvaProvaa" );
		add( playerNameLabel );
		playerNameLabel.setBounds( 60, 12, 110, 20 );

		imageLabel.setIcon( new javax.swing.ImageIcon( getClass().getResource("/img/texture.jpg") ) ); // NOI18N
		imageLabel.setAlignmentY( 0.0F );
		add( imageLabel );
		imageLabel.setBounds( 0, 0, dimX, 300 );
	}// </editor-fold>//GEN-END:initComponents

	public void fillScheme( Scheme scheme ) {
		this.scheme = scheme;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				dices[i][j].setBox( scheme.getBox( i, j ) );
			}
		}
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setPlayerNameLabel( String name ) {
		playerNameLabel.setText( name );
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel imageLabel;
	private javax.swing.JLabel playerNameLabel;
	// End of variables declaration//GEN-END:variables
}
