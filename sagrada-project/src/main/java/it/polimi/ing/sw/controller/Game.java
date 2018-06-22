package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.client.RemoteView;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import static sun.audio.AudioPlayer.player;

public class Game {

    private Match match;

    //mi serve un array per salvare i giocatori

    public Game(Match match){
        this.match=match;
    }

    public void login(String nickname, RemoteView observer){
        match.createNewPlayer(nickname);
        //match.addClientUpdate(observer);

        new PlayerController( this.match, match.getPlayer(0));
    }

}
