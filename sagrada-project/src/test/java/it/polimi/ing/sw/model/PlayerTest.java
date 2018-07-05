package it.polimi.ing.sw.model;

import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.shortThat;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;
    private String  nickname;
    private Scheme scheme;
    private PlayerState state;
    private Box[][] boxes;
    private Color color;
    private PrivateObjectiveCard privateObjectiveCard1;


    @Before
    public void setUp() throws Exception {
        nickname="nickname";
        player=new Player(nickname);
        boxes = new Box[4][5];
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                boxes[i][j]=new Box(i,j);
            }
        }
        scheme = new Scheme(60, 4, boxes);
        state=PlayerState.READYTOPLAY;
        color=Color.GREEN;
        privateObjectiveCard1 = new PrivateObjectiveCard(Color.GREEN);

    }

    @Test
    public void isOnline() {
        player.setOnline();
        assertTrue(player.isOnline());
    }

    @Test
    public void getNumOfToken() {
        player.setNumOfToken(scheme.getDifficulty());
        assertTrue(player.getNumOfToken()==scheme.getDifficulty());

    }

    @Test
    public void getState() {
        player.setState(state);
        assertTrue(player.getState().equals(PlayerState.READYTOPLAY));
    }

    @Test
    public void getNickname() {

        assertTrue(player.getNickname().equals(nickname));
    }

    @Test
    public void getScore() {
        player.setScore(7);
        assertTrue(player.getScore()==7);
    }

    @Test
    public void getColor() {
        player.setColor(color);
        assertTrue(player.getColor().equals(color));
    }

    @Test
    public void getSchemesToChoose() {
        ArrayList<Scheme> schemes= new ArrayList<Scheme>();
        schemes.add(scheme);
        schemes.add(scheme);
        player.setSchemesToChoose(schemes);
        assertTrue(player.getSchemesToChoose().size()==2);
    }

    @Test
    public void getPrivateObjective() {
        player.setPrivateObjective(privateObjectiveCard1);
        assertTrue(player.getPrivateObjective().equals(privateObjectiveCard1));
    }

    @Test
    public void getScheme() {
        player.setScheme(scheme);
        assertTrue(player.getScheme().equals(scheme));
    }

    @Test
    public void setScore() {
        player.setScore(4);
        assertTrue(player.getScore()==4);
    }

   /* @Test
    public void isLogged() {
        player.setLogged(true);
        assertTrue(player.isLogged());
    }*/

    @Test
    public void setNumOfToken() {
        player.setNumOfToken(5);
        assertTrue(player.getNumOfToken()==5);
    }

    @Test
    public void setPrivateObjective() {
        player.setPrivateObjective(privateObjectiveCard1);
        assertTrue(player.getPrivateObjective().getColor().equals(Color.GREEN));
    }


    @Test
    public void setScheme() {
        player.setScheme(scheme);
        assertTrue(player.getScheme().equals(scheme));
    }

    @Test
    public void setColor() {
        player.setColor(color);
        assertTrue(player.getColor().equals(color));
    }

    @Test
    public void setSchemesToChoose() {
    }

    @Test
    public void setState() {
        player.setState(PlayerState.READYTOPLAY);
        assertTrue(player.getState().equals(PlayerState.READYTOPLAY));
    }

   /* @Test
    public void setLogged() {
        player.setLogged(true);
        assertTrue(player.isLogged());
    }*/

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
        player.setState(PlayerState.READYTOPLAY);
        player.setScheme(scheme);
        player.setPrivateObjective(privateObjectiveCard1);
        Player player1=new Player("pippo");
        player1=player.playerClone();
        assertTrue(player1.getNickname()==player.getNickname());
        assertTrue(player1.getState().equals(player.getState()));
        assertTrue(player1.getPrivateObjective().equals(player.getPrivateObjective()));
        assertTrue(player1.getScheme().equals(player.getScheme()));
        assertTrue(player1.getNumOfToken()==player.getNumOfToken());
    }
}