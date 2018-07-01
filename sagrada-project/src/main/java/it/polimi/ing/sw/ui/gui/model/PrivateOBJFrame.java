package it.polimi.ing.sw.ui.gui.model;

import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

public class PrivateOBJFrame extends javax.swing.JWindow {

	private int playerId;
	private CardField cardField;

	private int screenX = 0;
	private int screenY = 0;
	private int myX = 0;
	private int myY = 0;

	private PrivateObjectiveCard privateObjective;

	public PrivateOBJFrame( PrivateObjectiveCard privateObjective ) {
		this.privateObjective = privateObjective;
		initComponents();
		setIcons();
		setLocationRelativeTo( null );
		String nameCard = String.valueOf( privateObjective.getId() );
		cardField = new CardField( nameCard, "po/", 250, 354 );
		privateOBJPanel.add( cardField );
		cardField.setBounds( 0, 0, 250, 354 );
	}

	private void setIcons() {
		/*ImageIcon icon;
		Image scaledImage;
		icon = new javax.swing.ImageIcon( getClass().getResource( SAGRADA_ICO ) );
		scaledImage = icon.getImage().getScaledInstance( 30, 30, Image.SCALE_DEFAULT );
		icon.setImage( scaledImage );
		sagradaIcon.setIcon( icon );
		sagradaIcon.repaint();*/

		cardTitleLabel.setText( "Private Objective" );
	}

	@SuppressWarnings ( "unchecked" )
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		panel = new javax.swing.JPanel();
		dragBarPanel = new javax.swing.JPanel();
		xLabel = new javax.swing.JLabel();
		sagradaIcon = new javax.swing.JLabel();
		cardTitleLabel = new javax.swing.JLabel();
		privateOBJPanel = new javax.swing.JPanel();

		setMaximumSize( new java.awt.Dimension( 250, 384 ) );
		setMinimumSize( new java.awt.Dimension( 250, 384 ) );

		panel.setBackground( new java.awt.Color( 255, 0, 255 ) );
		panel.setAlignmentX( 0.0F );
		panel.setAlignmentY( 0.0F );
		panel.setMaximumSize( new java.awt.Dimension( 250, 384 ) );
		panel.setMinimumSize( new java.awt.Dimension( 250, 384 ) );
		panel.setPreferredSize( new java.awt.Dimension( 250, 384 ) );
		panel.setLayout( null );

		dragBarPanel.setBackground( new java.awt.Color( 204, 204, 204 ) );
		dragBarPanel.setAlignmentX( 0.0F );
		dragBarPanel.setAlignmentY( 0.0F );
		dragBarPanel.setMaximumSize( new java.awt.Dimension( 250, 30 ) );
		dragBarPanel.setPreferredSize( new java.awt.Dimension( 250, 30 ) );
		dragBarPanel.addMouseMotionListener( new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged( java.awt.event.MouseEvent evt ) {
				dragBarPanelMouseDragged( evt );
			}
		} );
		dragBarPanel.addMouseListener( new java.awt.event.MouseAdapter() {
			public void mousePressed( java.awt.event.MouseEvent evt ) {
				dragBarPanelMousePressed( evt );
			}
		} );

		xLabel.setBackground( new java.awt.Color( 255, 0, 0 ) );
		xLabel.setFont( new java.awt.Font( "Dialog", 0, 18 ) ); // NOI18N
		xLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		xLabel.setText( "X" );
		xLabel.setOpaque( true );
		xLabel.addMouseListener( new java.awt.event.MouseAdapter() {
			public void mouseClicked( java.awt.event.MouseEvent evt ) {
				xLabelMouseClicked( evt );
			}

			public void mouseEntered( java.awt.event.MouseEvent evt ) {
				xLabelMouseEntered( evt );
			}

			public void mouseExited( java.awt.event.MouseEvent evt ) {
				xLabelMouseExited( evt );
			}
		} );

		sagradaIcon.setAlignmentX( 1.0F );
		sagradaIcon.setAlignmentY( 1.0F );
		sagradaIcon.setMaximumSize( new java.awt.Dimension( 24, 26 ) );
		sagradaIcon.setMinimumSize( new java.awt.Dimension( 24, 26 ) );
		sagradaIcon.setPreferredSize( new java.awt.Dimension( 24, 26 ) );

		cardTitleLabel.setForeground( new java.awt.Color( 0, 0, 0 ) );
		cardTitleLabel.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		cardTitleLabel.setText( "card Title" );

		javax.swing.GroupLayout dragBarPanelLayout = new javax.swing.GroupLayout( dragBarPanel );
		dragBarPanel.setLayout( dragBarPanelLayout );
		dragBarPanelLayout.setHorizontalGroup(
				dragBarPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( dragBarPanelLayout.createSequentialGroup()
								.addGap( 8, 8, 8 )
								.addComponent( sagradaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE )
								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
								.addComponent( cardTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE )
								.addGap( 18, 18, 18 )
								.addComponent( xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE ) )
		);
		dragBarPanelLayout.setVerticalGroup(
				dragBarPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addGroup( dragBarPanelLayout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
								.addComponent( xLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
								.addComponent( cardTitleLabel )
								.addComponent( sagradaIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE ) )
		);

		panel.add( dragBarPanel );
		dragBarPanel.setBounds( 0, 0, 250, 30 );

		privateOBJPanel.setAlignmentX( 0.0F );
		privateOBJPanel.setAlignmentY( 0.0F );
		privateOBJPanel.setMaximumSize( new java.awt.Dimension( 250, 354 ) );
		privateOBJPanel.setMinimumSize( new java.awt.Dimension( 250, 354 ) );
		privateOBJPanel.setPreferredSize( new java.awt.Dimension( 250, 354 ) );
		privateOBJPanel.setLayout( null );
		panel.add( privateOBJPanel );
		privateOBJPanel.setBounds( 0, 30, 250, 360 );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
						.addComponent( panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void xLabelMouseEntered( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_xLabelMouseEntered
		xLabel.setForeground( new java.awt.Color( 222, 222, 222 ) );
	}//GEN-LAST:event_xLabelMouseEntered

	private void xLabelMouseExited( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_xLabelMouseExited
		xLabel.setForeground( new java.awt.Color( 0, 0, 0 ) );
	}//GEN-LAST:event_xLabelMouseExited

	private void dragBarPanelMouseDragged( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_dragBarPanelMouseDragged
		int deltaX = evt.getXOnScreen() - screenX;
		int deltaY = evt.getYOnScreen() - screenY;

		setLocation( myX + deltaX, myY + deltaY );
	}//GEN-LAST:event_dragBarPanelMouseDragged

	private void xLabelMouseClicked( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_xLabelMouseClicked
		setVisible( false );
	}//GEN-LAST:event_xLabelMouseClicked

	private void dragBarPanelMousePressed( java.awt.event.MouseEvent evt ) {//GEN-FIRST:event_dragBarPanelMousePressed
		screenX = evt.getXOnScreen();
		screenY = evt.getYOnScreen();

		myX = getX();
		myY = getY();
	}//GEN-LAST:event_dragBarPanelMousePressed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel cardTitleLabel;
	private javax.swing.JPanel dragBarPanel;
	private javax.swing.JPanel panel;
	private javax.swing.JPanel privateOBJPanel;
	private javax.swing.JLabel sagradaIcon;
	private javax.swing.JLabel xLabel;
	// End of variables declaration//GEN-END:variables
}




