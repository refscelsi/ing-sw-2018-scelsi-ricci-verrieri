package it.polimi.ing.sw.model;

import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player;
	private String nickname;
	private Scheme scheme;
	private PlayerState state;
	private Box[][] boxes;
	private Color color;
	private PrivateObjectiveCard privateObjectiveCard1;


	@Before
	public void setUp() throws Exception {
		nickname = "nickname";
		player = new Player( nickname );
		boxes = new Box[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				boxes[i][j] = new Box( i, j );
			}
		}
		scheme = new Scheme( 60, 4, boxes );
		state = PlayerState.READYTOPLAY;
		color = Color.GREEN;
		privateObjectiveCard1 = new PrivateObjectiveCard( Color.GREEN );

	}

	@Test
	public void isOnline() {
		player.setOnline();
		assertTrue( player.isOnline() );
	}

	@Test
	public void getNumOfToken() {
		player.setNumOfToken( scheme.getDifficulty() );
		assertEquals( player.getNumOfToken(), scheme.getDifficulty() );

	}

	@Test
	public void getState() {
		player.setState( state );
		assertEquals( player.getState(), PlayerState.READYTOPLAY );
	}

	@Test
	public void getNickname() {

		assertEquals( player.getNickname(), nickname );
	}

	@Test
	public void getScore() {
		player.setScore( 7 );
		assertEquals( 7, player.getScore() );
	}

	@Test
	public void getColor() {
		player.setColor( color );
		assertEquals( player.getColor(), color );
	}

	@Test
	public void getSchemesToChoose() {
		ArrayList<Scheme> schemes = new ArrayList<Scheme>();
		schemes.add( scheme );
		schemes.add( scheme );
		player.setSchemesToChoose( schemes );
		assertEquals( 2, player.getSchemesToChoose().size() );
	}

	@Test
	public void getPrivateObjective() {
		player.setPrivateObjective( privateObjectiveCard1 );
		assertEquals( player.getPrivateObjective(), privateObjectiveCard1 );
	}

	@Test
	public void getScheme() {
		player.setScheme( scheme );
		assertEquals( player.getScheme(), scheme );
	}

	@Test
	public void setScore() {
		player.setScore( 4 );
		assertEquals( 4, player.getScore() );
	}

   /* @Test
    public void isLogged() {
        player.setLogged(true);
        assertTrue(player.isLogged());
    }*/

	@Test
	public void setNumOfToken() {
		player.setNumOfToken( 5 );
		assertEquals( 5, player.getNumOfToken() );
	}

	@Test
	public void setPrivateObjective() {
		player.setPrivateObjective( privateObjectiveCard1 );
		assertEquals( player.getPrivateObjective().getColor(), Color.GREEN );
	}


	@Test
	public void setScheme() {
		player.setScheme( scheme );
		assertEquals( player.getScheme(), scheme );
	}

	@Test
	public void setColor() {
		player.setColor( color );
		assertEquals( player.getColor(), color );
	}

	@Test
	public void setSchemesToChoose() {
	}

	@Test
	public void setState() {
		player.setState( PlayerState.READYTOPLAY );
		assertEquals( player.getState(), PlayerState.READYTOPLAY );
	}

   /* @Test
    public void setLogged() {
        player.setLogged(true);
        assertTrue(player.isLogged());
    }*/

	@Test
	public void setNickname() {
		player.setNickname( "prova2" );
		assertSame( "prova2", player.getNickname() );
	}

	@Test
	public void setOnline() {
		player.setOnline();
		assertTrue( player.isOnline() );
	}

	@Test
	public void setOffline() {
		player.setOffline();
		assertTrue( !player.isOnline() );
	}

	@Test
	public void playerClone() {
		player.setNumOfToken( 5 );
		player.setState( PlayerState.READYTOPLAY );
		player.setScheme( scheme );
		player.setPrivateObjective( privateObjectiveCard1 );
		Player player1 = new Player( "pippo" );
		player1 = player.playerClone();
		assertSame( player1.getNickname(), player.getNickname() );
		assertEquals( player1.getState(), player.getState() );
		assertEquals( player1.getPrivateObjective(), player.getPrivateObjective() );
		assertEquals( player1.getScheme(), player.getScheme() );
		assertEquals( player1.getNumOfToken(), player.getNumOfToken() );
	}
}