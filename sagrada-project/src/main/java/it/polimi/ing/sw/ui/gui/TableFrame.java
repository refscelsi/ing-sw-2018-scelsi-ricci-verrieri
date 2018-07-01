package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Scheme;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ing.sw.util.Constants.SAGRADA_ICO;

public class TableFrame extends javax.swing.JFrame {

	private static Match match;
	private static List<CardField> toolCardList, objCardList;
	private static RoundTrack roundTrack;
	private static VetrataPanel player1;
	private static VetrataPanel player2;
	private static VetrataPanel player3;
	private static VetrataPanel player4;
	private DiceFieldPanel diceFieldPanel;
	private SumPlayerPanel sumPlayer1;
	private SumPlayerPanel sumPlayer2;
	private SumPlayerPanel sumPlayer3;
	private SumPlayerPanel sumPlayer4;
	private static int dimXcard;
	private static int dimYcard;

	public TableFrame( Match match ) {
		this.match = match;

		toolCardList = new ArrayList<>();
		objCardList = new ArrayList<>();

		dimXcard = 173;
		dimYcard = 245;

		initComponents();
		setLocationRelativeTo( null );
		setIcons();
		setName( "tableFrame" );

		addVetratePlayers();
		addSumsPlayer();
		addRoundTrack();
		addDiceFieldPAnel();

		//TODO WORKS
		//TEST addDICES al dice panel
		List<Dice> dices = new ArrayList<>();
		Dice testDice = new Dice();
		testDice.setNumFacciaUp( 5 );
		testDice.setDiceColor( it.polimi.ing.sw.model.Color.RED );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		dices.add( testDice );
		diceFieldPanel.setDices( dices );

        /*
        //TODO WORKS
        //TEST carta singola
        CardField cf = new CardField();
        tableFramePanel.add(cf);
        cf.setBounds(0, 0, 173, 245);
        */

		/*//TODO WORKS
		//TEST campi carte
		String ids1[] = {"disabled.png", "tc02.png", "tc03.png"};
		setToolCards( ids1 );
		String ids2[] = {"po01.png", "po02.png", "po03.png"};
		setOBJCards( ids2 );*/

        /*//TODO WORKS
        //TEST set dice
        Dice testDice = new Dice();
        testDice.setNumFacciaUp(5);
        testDice.setDiceColor(it.polimi.ing.sw.model.Color.RED);
        roundTrack.getDiceGUIList().get(4).setDice(testDice);*/

        /*//TODO WORKS
        //TEST disablecard
        toolCardList.get(1).disableToolCard();*/

		//TODO WORKS
		//TEST fillScheme
		SchemeListFileConverter schemeListFileConverter = new SchemeListFileConverter();
		Scheme scheme = schemeListFileConverter.readFromFile().get( 0 );
		player1.fillScheme( scheme );

		//TODO WORKS
		//TEST set nome player
		//player1.setPlayerNameLabel("pisello");

		//TODO WORKS
		//TEST set tokens by code
		toolCardList.get( 2 ).addToken( 1 );
		toolCardList.get( 2 ).addToken( 2 );
	}

	public static Match getMatch() {
		return match;
	}

	public static void updateMatch( Match match ) {

		roundTrack.setDraftPool( match.getRoundTrack().getRoundTrack() );

		List<String> ids = new ArrayList<>();
		ids.set( 0, match.getPublicObjectives().get( 0 ).getName() );
		ids.set( 1, match.getPublicObjectives().get( 1 ).getName() );
		ids.set( 2, match.getPublicObjectives().get( 2 ).getName() );
		setOBJCards( ids );

		//TODO setup set private obj card

		ids.set( 0, String.valueOf( match.getToolCards().get( 0 ).getId() ) );
		ids.set( 1, String.valueOf( match.getToolCards().get( 1 ).getId() ) );
		ids.set( 2, String.valueOf( match.getToolCards().get( 2 ).getId() ) );
		/*
		ShowPrivateObjectiveCard priv = new ShowPrivateObjectiveCard( match.getPlayer( nickname ).getPrivateObjective() );

		ShowToolCards tool = new ShowToolCards( match.getToolCards() );
		System.out.println( "Hai " + match.getPlayer( nickname ).getNumOfToken() + " segnalini favore" );
		ShowDraftPool draft = new ShowDraftPool( match.getDraftPool() );
		ShowScheme scheme = new ShowScheme( match.getPlayer( nickname ).getScheme() );*/
	}

	private void addSumsPlayer() {
		sumPlayer1 = new SumPlayerPanel();
		backgroundTableFRameLabel.add( sumPlayer1 );
		sumPlayer1.setBounds( 10, 10, 85, 80 );

		sumPlayer2 = new SumPlayerPanel();
		backgroundTableFRameLabel.add( sumPlayer2 );
		sumPlayer2.setBounds( 105, 10, 85, 80 );

		sumPlayer3 = new SumPlayerPanel();
		backgroundTableFRameLabel.add( sumPlayer3 );
		sumPlayer3.setBounds( 830, 10, 85, 80 );

		sumPlayer4 = new SumPlayerPanel();
		backgroundTableFRameLabel.add( sumPlayer4 );
		sumPlayer4.setBounds( 925, 10, 85, 80 );
	}

	private void addVetratePlayers() {
		player1 = new VetrataPanel( 1 );
		backgroundTableFRameLabel.add( player1 );
		player1.setBounds( 780, 430, 230, 290 );

		player2 = new VetrataPanel( 2 );
		backgroundTableFRameLabel.add( player2 );
		player2.setBounds( 780, 100, 230, 290 );

		player3 = new VetrataPanel( 3 );
		backgroundTableFRameLabel.add( player3 );
		player3.setBounds( 10, 100, 230, 290 );

		player4 = new VetrataPanel( 4 );
		backgroundTableFRameLabel.add( player4 );
		player4.setBounds( 10, 430, 230, 290 );
	}

	private void addDiceFieldPAnel() {
		diceFieldPanel = new DiceFieldPanel();
		backgroundTableFRameLabel.add( diceFieldPanel );
		diceFieldPanel.setBounds( 250, 100, 520, 140 );
	}

	private void addRoundTrack() {
		roundTrack = new RoundTrack();
		backgroundTableFRameLabel.add( roundTrack );
		roundTrack.setBounds( 207, 20, 610, 70 );
	}

	private void setIcons() {
		setIconImage( Toolkit.getDefaultToolkit().getImage( getClass().getResource( SAGRADA_ICO ) ) );
		this.setTitle( "Sagrada Boardgame" );
	}

	@SuppressWarnings ( "unchecked" )
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		tableFramePanel = new javax.swing.JPanel();
		objCardPanel = new javax.swing.JPanel();
		toolCardPanel = new javax.swing.JPanel();
		backgroundTableFRameLabel = new javax.swing.JLabel();

		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
		setMinimumSize( new java.awt.Dimension( 1024, 768 ) );
		setResizable( false );

		tableFramePanel.setBackground( new java.awt.Color( 102, 102, 102 ) );
		tableFramePanel.setLayout( null );

		javax.swing.GroupLayout objCardPanelLayout = new javax.swing.GroupLayout( objCardPanel );
		objCardPanel.setLayout( objCardPanelLayout );
		objCardPanelLayout.setHorizontalGroup(
				objCardPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGap( 0, 519, Short.MAX_VALUE )
		);
		objCardPanelLayout.setVerticalGroup(
				objCardPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGap( 0, 245, Short.MAX_VALUE )
		);

		tableFramePanel.add( objCardPanel );
		objCardPanel.setBounds( 250, 500, 519, 245 );

		javax.swing.GroupLayout toolCardPanelLayout = new javax.swing.GroupLayout( toolCardPanel );
		toolCardPanel.setLayout( toolCardPanelLayout );
		toolCardPanelLayout.setHorizontalGroup(
				toolCardPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGap( 0, 519, Short.MAX_VALUE )
		);
		toolCardPanelLayout.setVerticalGroup(
				toolCardPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGap( 0, 245, Short.MAX_VALUE )
		);

		tableFramePanel.add( toolCardPanel );
		toolCardPanel.setBounds( 250, 250, 519, 245 );

		backgroundTableFRameLabel.setBackground( new java.awt.Color( 153, 153, 153 ) );
		backgroundTableFRameLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		backgroundTableFRameLabel.setIcon( new javax.swing.ImageIcon( getClass().getResource( "/img/sagrada.png" ) ) ); // NOI18N
		backgroundTableFRameLabel.setAlignmentY( 0.0F );
		tableFramePanel.add( backgroundTableFRameLabel );
		backgroundTableFRameLabel.setBounds( 0, 0, 1024, 768 );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( tableFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( tableFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE )
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void tornaMenu() {
		this.setVisible( false );
		App.menu.setVisible( true );
	}

	private void setToolCards( String[] id ) {
		toolCardPanel.setLayout( null );
		for (int i = 0; i < 3; i++) {
			CardField schemeCard = new CardField( id[i], "tc/", dimXcard, dimYcard );
			toolCardPanel.add( schemeCard );
			toolCardList.add( schemeCard );
			schemeCard.setBounds( i * (173), 0, 173, 245 );
		}
	}

	private static void setOBJCards( List<String> id ) {
		objCardPanel.setLayout( null );
		for (int i = 0; i < 3; i++) {
			CardField schemeCard = new CardField( id.get( i ), "po/", dimXcard, dimYcard );
			objCardPanel.add( schemeCard );
			objCardList.add( schemeCard );
			schemeCard.setBounds( i * (173), 0, 173, 245 );
		}
	}

	public static void main( String args[] ) {
		/* Set the Nimbus look and feel */

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ( "Nimbus".equals( info.getName() ) ) {
					javax.swing.UIManager.setLookAndFeel( info.getClassName() );
					break;
				}
			}
		} catch ( ClassNotFoundException ex ) {
			//java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch ( InstantiationException ex ) {
			//java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch ( IllegalAccessException ex ) {
			//java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
			//java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/* Create and display the form */

		Match match = new Match();
		java.awt.EventQueue.invokeLater( new Runnable() {
			public void run() {
				new TableFrame( match ).setVisible( true );
			}
		} );
	}

	public static void updateDice( int idPlayer, Dice dice, int i, int j ) {
		switch (idPlayer) {
			case 1:
				player1.setDice( i, j, dice );
				break;
			case 2:
				player2.setDice( i, j, dice );
				break;
			case 3:
				player3.setDice( i, j, dice );
				break;
			case 4:
				player4.setDice( i, j, dice );
				break;
		}
	}

	public static void setCurrentComponentName( String nameComponentEntered, Boolean isAdiceGui ) {
		currentComponentName = nameComponentEntered;
		TableFrame.isAdiceGui = isAdiceGui;
	}

	public static String getCurrentComponentName() {
		if ( isAdiceGui ) {
			return currentComponentName;

		} else {
			return NOT_A_DICE;
		}
	}

	public static void setIsAdiceGui( Boolean isAdiceGui ) {
		TableFrame.isAdiceGui = isAdiceGui;
	}

	public static final String NOT_A_DICE = "notADice";
	private static Boolean isAdiceGui;
	private static String currentComponentName;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel backgroundTableFRameLabel;
	private static javax.swing.JPanel objCardPanel;
	private javax.swing.JPanel tableFramePanel;
	private javax.swing.JPanel toolCardPanel;
	// End of variables declaration//GEN-END:variables
}
