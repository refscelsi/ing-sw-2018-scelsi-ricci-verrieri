package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PlayerControllerTest {

	@Mock
	Match match;

	@Mock
	RemotePlayer remotePlayer;

	@Mock
	Timer timer;

	@Mock
	TimerTask lastTimer;

	@Mock
	Player player;

	private PlayerController playerController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks( this );
		playerController = new PlayerController( match, remotePlayer );
	}

	@Test
	public void login() throws RemoteException {
		Player fakePlayer = new Player( "coso" );
		when( match.getPlayer( any() ) ).thenReturn( fakePlayer );
		when( player.getNickname() ).thenReturn( "coso" );

		playerController.login( "coso" );

		verify( match, times( 1 ) ).getPlayer( eq( "coso" ) );
		verify(remotePlayer).onLogin( eq("coso") );
	}

	@Test
	public void startingMyTurn() {
		
	}

	@Test
	public void reconnectPlayer() {
	}

	@Test
	public void joinMatch() {
	}

	@Test
	public void checkAllReady() {
	}

	@Test
	public void setChosenScheme() {
	}

	@Test
	public void sendUseDiceRequest() {
	}

	@Test
	public void endTurn() {
	}

	@Test
	public void useToolCard() {
	}

	@Test
	public void stopPlayer() {
	}

	@Test
	public void timeout() {
	}
}