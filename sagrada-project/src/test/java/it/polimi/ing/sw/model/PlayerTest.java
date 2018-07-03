package it.polimi.ing.sw.model;

import it.polimi.ing.sw.controller.PlayerState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.management.PlatformLoggingMXBean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;


    @Before
    public void setUp() throws Exception {
        player=new Player("prova");

    }

    @Test
    public void isOnline() {
        player.setOnline();
        assertTrue(player.isOnline());
    }

    @Test
    public void getNumOfToken() {
    }

    @Test
    public void getState() {
    }

    @Test
    public void getNickname() {
        assertTrue(player.getNickname().equals("prova"));
    }

    @Test
    public void getScore() {
    }

    @Test
    public void getColor() {
    }

    @Test
    public void getSchemesToChoose() {
    }

    @Test
    public void getPrivateObjective() {
    }

    @Test
    public void getScheme() {
    }

    @Test
    public void setScore() {
    }

    @Test
    public void isLogged() {
        player.setLogged(true);
        assertTrue(player.isLogged());
    }

    @Test
    public void setNumOfToken() {
        player.setNumOfToken(5);
        assertTrue(player.getNumOfToken()==5);
    }

    @Test
    public void setPrivateObjective() {
    }

    @Test
    public void setScheme() {

    }

    @Test
    public void setColor() {
    }

    @Test
    public void setSchemesToChoose() {
    }

    @Test
    public void setState() {
        player.setState(PlayerState.FINISHTURN);
        assertTrue(player.getState().equals(PlayerState.FINISHTURN));
    }

    @Test
    public void setLogged() {
    }

    @Test
    public void setNickname() {
        player.setNickname("prova2");
        assertTrue(player.getNickname()=="prova2");
    }

    @Test
    public void setOnline() {
        player.setOnline();
        assertTrue(player.isOnline());
    }

    @Test
    public void setOffline() {
        player.setOffline();
        assertTrue(!player.isOnline());
    }

    @Test
    public void playerClone() {
        player.setNumOfToken(5);
        player.setState(PlayerState.FINISHTURN);
        Player player1=new Player("pippo");
        player1=player.playerClone();
        assertTrue(player1.getNickname()==player.getNickname());
        assertTrue(player1.getState().equals(player.getState()));
        assertTrue(player1.getNumOfToken()==player.getNumOfToken());
    }
}