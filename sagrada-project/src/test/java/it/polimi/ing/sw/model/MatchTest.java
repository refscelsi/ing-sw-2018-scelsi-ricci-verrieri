package it.polimi.ing.sw.model;

import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.controller.PlayerState;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/*public class MatchTest {

    private Match match;


    @Before
    public void before() {
        match = new Match();
    }


    @Test
    public void getPlayer() {
        try {
            // deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Ari", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Ricky", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Gino", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            match.login("Tadde", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        match.getPlayer("Ludo").setState(PlayerState.OFFLINE);
        try {
            // deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(match.getPlayer("Ludo") != null && match.getPlayer("Tadde") == null);
    }

    @Test
    public void getPlayersRoundIndex() {
    }

    @Test
    public void getDraftPool() {
    }

    @Test
    public void getPublicObjectives() {
    }

    @Test
    public void getRoundTrack() {
    }

    @Test
    public void getRanking() {
    }

    @Test
    public void getPlayerPlaying() {
    }

    @Test
    public void getNumPlayers() {
    }

    @Test
    public void getToolCards() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void getOtherPlayers() {
    }

    @Test
    public void setColorOfPawns() {
    }

    @Test
    public void setRoundTrack() {
    }

    @Test
    public void setDraftPool() {
    }

    @Test
    public void setPlayers() {
    }

    @Test
    public void setToolCards() {
    }

    @Test
    public void setPublicObjectives() {
    }

    @Test
    public void setRanking() {
    }

    @Test
    public void login() {
        try {
            // deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Ari", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Ricky", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            match.login("Gino", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            match.login("Tadde", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        match.getPlayer("Ludo").setState(PlayerState.OFFLINE);
        try {
            // deve andare a buon fine
            match.login("Ludo", new View());
        } catch (NotValidNicknameException e) {
            System.out.println(e.getMessage());
        } catch (NotPossibleConnectionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(match.getNumPlayers()==4 && match.getPlayer("Ludo").getState()!=PlayerState.OFFLINE);
    }

    @Test
    public void checkNickname() {
        ArrayList<Player> players = new ArrayList<Player>();
        Player p1 = new Player("Ludo");
        Player p2 = new Player("Ari");
        Player p3 = new Player("Ricky");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        match.setPlayers(players);
        assertTrue(!match.checkNickname("Ludo") && match.checkNickname("Gino"));
    }

    @Test
    public void checkReconnection() {
    }

    @Test
    public void joinMatch() {
    }

    @Test
    public void checkAllReady() {
    }

    @Test
    public void startMatch() {
    }

    @Test
    public void initializeTable() {
    }

    @Test
    public void inizializePlayers() {
    }

    @Test
    public void endMatch() {
    }

    @Test
    public void startRound() {
    }

    @Test
    public void changePlayersRound() {
    }

    @Test
    public void createRoundPlayers() {
    }

    @Test
    public void changePlayer() {
    }

    @Test
    public void endRound() {
    }

    @Test
    public void calculateScore() {
    }

    @Test
    public void calculateRanking() {
    }

    @Test
    public void useDice() {
    }

    @Test
    public void changePlayerStateAfterUseDice() {
    }

    @Test
    public void chooseScheme() {
    }

    @Test
    public void checkToken() {
    }

    @Test
    public void findToolCard() {
    }

    @Test
    public void getIfFirstTurn() {
    }

    @Test
    public void useToolCard() {
    }

    @Test
    public void changePlayerStateAfterToolCard() {
    }

    @Test
    public void notifyUsedToolCard() {
    }

    @Test
    public void exitPlayer() {
    }

    @Test
    public void notifyChangement() {
    }

    @Test
    public void notifyEndTurn() {
    }

    @Test
    public void notifyGameEnd() {
    }

    @Test
    public void notifySucces() {
    }

    @Test
    public void notifyNotValidUseDiceException() {
    }

    @Test
    public void notifyNotValidToolCardException() {
    }

    @Test
    public void notifyNotValidPlayException() {
    }

    @Test
    public void notifyNotValidNicknameException() {
    }

    @Test
    public void notifyNotPossibleConnectionException() {
    }

    @Test
    public void matchClone() {
    }
    }*/
