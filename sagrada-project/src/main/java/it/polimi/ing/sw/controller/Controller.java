package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller implements ControllerInterface{

    private Match match;

    //mi serve un array per salvare i giocatori

    public Controller(Match match){
        this.match=match;
    }


    @Override
    public int sendLoginRequest(String nickname) throws NotValidNicknameException, NetworkException {
        return 0;
    }

    @Override
    public void setChosenScheme(int index, int id) throws NetworkException {

    }
    //è lecita questa implementazione o gli oggetti devo tirarli fuori nel match??
    @Override
    public void sendUseDiceRequest(int index, int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException {
        match.useDice(match.getPlayer(index).getScheme().getBoxes()[row][col], match.getDraftPool().getDice(indexOfDiceInDraftPool),match.getPlayer(index));
    }

    @Override
    public void removeDice(int index, int row, int col) throws NetworkException {
        match.getPlayer(index).getScheme().getBoxes()[row][col].removeDice();
    }

    @Override
    public void endTurn(int index) throws NetworkException {

    }
}
