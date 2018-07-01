package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Scheme;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ing.sw.util.Constants.DEFAULT_TITLE;
import static it.polimi.ing.sw.util.Constants.SAGRADA_ICO;

public class ChooseSchemeForm extends javax.swing.JFrame {

	private List<Scheme> schemeList;
	private VetrataPanel vetrataPanelA;
	private VetrataPanel vetrataPanelB;
	private VetrataPanel vetrataPanelC;
	private VetrataPanel vetrataPanelD;

	public ChooseSchemeForm( List<Scheme> schemeList ) {
		this.schemeList = schemeList;

		initComponents();
		setLocationRelativeTo( null );
		setIcons();
		setTexts();

		setVetrataPanel();
	}

	private void setVetrataPanel() {
		vetrataPanelA = new VetrataPanel( 1 );
		vetrataPanelA.fillScheme( schemeList.get( 0 ) );
		schemeCardPanel1.add( vetrataPanelA );
		vetrataPanelA.setBounds( 13, 49, 203, 287 );

		vetrataPanelB = new VetrataPanel( 2 );
		vetrataPanelB.fillScheme( schemeList.get( 1 ) );
		schemeCardPanel1.add( vetrataPanelB );
		vetrataPanelB.setBounds( 228, 49, 203, 287 );

		vetrataPanelC = new VetrataPanel( 3 );
		vetrataPanelC.fillScheme( schemeList.get( 2 ) );
		schemeCardPanel2.add( vetrataPanelC );
		vetrataPanelC.setBounds( 13, 49, 203, 287 );

		vetrataPanelD = new VetrataPanel( 4 );
		vetrataPanelD.fillScheme( schemeList.get( 3 ) );
		schemeCardPanel2.add( vetrataPanelD );
		vetrataPanelD.setBounds( 228, 49, 203, 287 );
	}

	private void setTexts() {
		buttonA.setText( "Select Scheme: " + schemeList.get( 0 ).getId() );
		buttonB.setText( "Select Scheme: " + schemeList.get( 1 ).getId() );
		buttonC.setText( "Select Scheme: " + schemeList.get( 2 ).getId() );
		buttonD.setText( "Select Scheme: " + schemeList.get( 3 ).getId() );

		nameSchemeLabel1.setText( String.valueOf( "ID: " + schemeList.get( 0 ).getId() ) );
		nameSchemeLabel2.setText( String.valueOf( "ID: " + schemeList.get( 1 ).getId() ) );
		nameSchemeLabel3.setText( String.valueOf( "ID: " + schemeList.get( 2 ).getId() ) );
		nameSchemeLabel4.setText( String.valueOf( "ID: " + schemeList.get( 3 ).getId() ) );

		diffSchemeLabel1.setText( String.valueOf( "Diff: " + schemeList.get( 0 ).getDifficulty() ) );
		diffSchemeLabel2.setText( String.valueOf( "Diff: " + schemeList.get( 1 ).getDifficulty() ) );
		diffSchemeLabel3.setText( String.valueOf( "Diff: " + schemeList.get( 2 ).getDifficulty() ) );
		diffSchemeLabel4.setText( String.valueOf( "Diff: " + schemeList.get( 3 ).getDifficulty() ) );
	}

	@SuppressWarnings ( "unchecked" )
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		schemeCardPanel1 = new javax.swing.JPanel();
		nameSchemeLabel1 = new javax.swing.JLabel();
		diffSchemeLabel1 = new javax.swing.JLabel();
		nameSchemeLabel2 = new javax.swing.JLabel();
		diffSchemeLabel2 = new javax.swing.JLabel();
		nameSchemeCardLabel1 = new javax.swing.JLabel();
		buttonA = new javax.swing.JButton();
		buttonB = new javax.swing.JButton();
		schemeCardPanel2 = new javax.swing.JPanel();
		nameSchemeLabel3 = new javax.swing.JLabel();
		diffSchemeLabel3 = new javax.swing.JLabel();
		nameSchemeLabel4 = new javax.swing.JLabel();
		diffSchemeLabel4 = new javax.swing.JLabel();
		nameSchemeCardLabel2 = new javax.swing.JLabel();
		buttonC = new javax.swing.JButton();
		buttonD = new javax.swing.JButton();

		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );

		schemeCardPanel1.setBackground( new java.awt.Color( 204, 204, 204 ) );
		schemeCardPanel1.setBorder( javax.swing.BorderFactory.createLineBorder( new java.awt.Color( 0, 0, 0 ) ) );
		schemeCardPanel1.setLayout( null );

		nameSchemeLabel1.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeLabel1.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeLabel1.setText( "nameSchemeA" );
		schemeCardPanel1.add( nameSchemeLabel1 );
		nameSchemeLabel1.setBounds( 13, 29, 96, 14 );

		diffSchemeLabel1.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		diffSchemeLabel1.setForeground( new java.awt.Color( 51, 51, 51 ) );
		diffSchemeLabel1.setHorizontalAlignment( javax.swing.SwingConstants.RIGHT );
		diffSchemeLabel1.setText( "diff" );
		schemeCardPanel1.add( diffSchemeLabel1 );
		diffSchemeLabel1.setBounds( 146, 29, 70, 14 );

		nameSchemeLabel2.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeLabel2.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeLabel2.setText( "nameScemeB" );
		schemeCardPanel1.add( nameSchemeLabel2 );
		nameSchemeLabel2.setBounds( 230, 30, 96, 14 );

		diffSchemeLabel2.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		diffSchemeLabel2.setForeground( new java.awt.Color( 51, 51, 51 ) );
		diffSchemeLabel2.setHorizontalAlignment( javax.swing.SwingConstants.RIGHT );
		diffSchemeLabel2.setText( "diff" );
		schemeCardPanel1.add( diffSchemeLabel2 );
		diffSchemeLabel2.setBounds( 361, 29, 70, 14 );

		nameSchemeCardLabel1.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeCardLabel1.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeCardLabel1.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		nameSchemeCardLabel1.setText( "Scheme Card A" );
		schemeCardPanel1.add( nameSchemeCardLabel1 );
		nameSchemeCardLabel1.setBounds( 147, 13, 149, 14 );

		buttonA.setFont( new java.awt.Font( "Eras Demi ITC", 2, 12 ) ); // NOI18N
		buttonA.setText( "button A" );
		buttonA.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				buttonAActionPerformed( evt );
			}
		} );
		schemeCardPanel1.add( buttonA );
		buttonA.setBounds( 13, 342, 203, 24 );

		buttonB.setFont( new java.awt.Font( "Eras Demi ITC", 2, 12 ) ); // NOI18N
		buttonB.setText( "button B" );
		buttonB.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				buttonBActionPerformed( evt );
			}
		} );
		schemeCardPanel1.add( buttonB );
		buttonB.setBounds( 228, 342, 203, 24 );

		schemeCardPanel2.setBackground( new java.awt.Color( 204, 204, 204 ) );
		schemeCardPanel2.setBorder( javax.swing.BorderFactory.createLineBorder( new java.awt.Color( 0, 0, 0 ) ) );
		schemeCardPanel2.setLayout( null );

		nameSchemeLabel3.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeLabel3.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeLabel3.setText( "nameSchemeC" );
		schemeCardPanel2.add( nameSchemeLabel3 );
		nameSchemeLabel3.setBounds( 13, 29, 96, 14 );

		diffSchemeLabel3.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		diffSchemeLabel3.setForeground( new java.awt.Color( 51, 51, 51 ) );
		diffSchemeLabel3.setHorizontalAlignment( javax.swing.SwingConstants.RIGHT );
		diffSchemeLabel3.setText( "diff" );
		schemeCardPanel2.add( diffSchemeLabel3 );
		diffSchemeLabel3.setBounds( 143, 30, 70, 14 );

		nameSchemeLabel4.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeLabel4.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeLabel4.setText( "nameSchemeD" );
		schemeCardPanel2.add( nameSchemeLabel4 );
		nameSchemeLabel4.setBounds( 228, 29, 96, 14 );

		diffSchemeLabel4.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		diffSchemeLabel4.setForeground( new java.awt.Color( 51, 51, 51 ) );
		diffSchemeLabel4.setHorizontalAlignment( javax.swing.SwingConstants.RIGHT );
		diffSchemeLabel4.setText( "diff" );
		schemeCardPanel2.add( diffSchemeLabel4 );
		diffSchemeLabel4.setBounds( 361, 29, 70, 14 );

		nameSchemeCardLabel2.setFont( new java.awt.Font( "Eras Demi ITC", 1, 12 ) ); // NOI18N
		nameSchemeCardLabel2.setForeground( new java.awt.Color( 51, 51, 51 ) );
		nameSchemeCardLabel2.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		nameSchemeCardLabel2.setText( "Scheme Card B" );
		schemeCardPanel2.add( nameSchemeCardLabel2 );
		nameSchemeCardLabel2.setBounds( 140, 10, 149, 14 );

		buttonC.setFont( new java.awt.Font( "Eras Demi ITC", 2, 12 ) ); // NOI18N
		buttonC.setText( "button C" );
		buttonC.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				buttonCActionPerformed( evt );
			}
		} );
		schemeCardPanel2.add( buttonC );
		buttonC.setBounds( 13, 342, 203, 24 );

		buttonD.setFont( new java.awt.Font( "Eras Demi ITC", 2, 12 ) ); // NOI18N
		buttonD.setText( "button D" );
		buttonD.addActionListener( new java.awt.event.ActionListener() {
			public void actionPerformed( java.awt.event.ActionEvent evt ) {
				buttonDActionPerformed( evt );
			}
		} );
		schemeCardPanel2.add( buttonD );
		buttonD.setBounds( 228, 342, 203, 24 );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent( schemeCardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
								.addComponent( schemeCardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE ) )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( schemeCardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE )
						.addComponent( schemeCardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void buttonBActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_buttonBActionPerformed
		schemeSelected(schemeList.get( 1 ));
	}//GEN-LAST:event_buttonBActionPerformed

	private void buttonDActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_buttonDActionPerformed
		schemeSelected(schemeList.get( 3 ));
	}//GEN-LAST:event_buttonDActionPerformed

	private void buttonCActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_buttonCActionPerformed
		schemeSelected(schemeList.get( 2 ));
	}//GEN-LAST:event_buttonCActionPerformed

	private void buttonAActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_buttonAActionPerformed
		schemeSelected(schemeList.get( 0 ));
	}//GEN-LAST:event_buttonAActionPerformed

	private void schemeSelected(Scheme scheme){
		//TODO Mandare messaggio al controller con lo stato del match
	}
	private void setIcons() {
		setIconImage( Toolkit.getDefaultToolkit().getImage( getClass().getResource( SAGRADA_ICO ) ) );
		this.setTitle( DEFAULT_TITLE );
	}

	public static void main( String args[] ) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ( "Nimbus".equals( info.getName() ) ) {
					javax.swing.UIManager.setLookAndFeel( info.getClassName() );
					break;
				}
			}
		} catch ( ClassNotFoundException ex ) {
			java.util.logging.Logger.getLogger( ChooseSchemeForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( InstantiationException ex ) {
			java.util.logging.Logger.getLogger( ChooseSchemeForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( IllegalAccessException ex ) {
			java.util.logging.Logger.getLogger( ChooseSchemeForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		} catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
			java.util.logging.Logger.getLogger( ChooseSchemeForm.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater( new Runnable() {
			public void run() {
				List<Scheme> list = new ArrayList<>();
				SchemeListFileConverter schemeListFileConverter = new SchemeListFileConverter();
				list.add( schemeListFileConverter.readFromFile().get( 0 ) );
				list.add( schemeListFileConverter.readFromFile().get( 1 ) );
				list.add( schemeListFileConverter.readFromFile().get( 2 ) );
				list.add( schemeListFileConverter.readFromFile().get( 3 ) );
				new ChooseSchemeForm( list ).setVisible( true );
			}
		} );
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonA;
	private javax.swing.JButton buttonB;
	private javax.swing.JButton buttonC;
	private javax.swing.JButton buttonD;
	private javax.swing.JLabel diffSchemeLabel1;
	private javax.swing.JLabel diffSchemeLabel2;
	private javax.swing.JLabel diffSchemeLabel3;
	private javax.swing.JLabel diffSchemeLabel4;
	private javax.swing.JLabel nameSchemeCardLabel1;
	private javax.swing.JLabel nameSchemeCardLabel2;
	private javax.swing.JLabel nameSchemeLabel1;
	private javax.swing.JLabel nameSchemeLabel2;
	private javax.swing.JLabel nameSchemeLabel3;
	private javax.swing.JLabel nameSchemeLabel4;
	private javax.swing.JPanel schemeCardPanel1;
	private javax.swing.JPanel schemeCardPanel2;
	// End of variables declaration//GEN-END:variables
}
