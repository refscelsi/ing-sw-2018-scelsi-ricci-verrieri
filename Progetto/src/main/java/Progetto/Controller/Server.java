package Progetto.Controller;

import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.Match;

public class Server {

    private Match match;

    public void newPlayer (String nickname) {
        match.createNewPlayer(nickname);
    }

    public /*static*/ void main(String[] args)  throws ToolCardException, NotValidException {

        match = new Match();
        //View view = new View()
        //ServerController serverController = new ServerController(match, view)

        //aspetto per un certo tempo durante il quale mi arrivano i nomi dei giocatori dalla view e creo i giocatori:

        match.initializeTable();
        match.inizializePlayer();

        //ora sono inizializzati tavolo e giocatori, quindi posso iniziare il match

        for (int j = 0; j < 10; j++) {

            match.startRound();

            //è iniziato un round e ha giocato il primo giocatore. Il primo giocatore passa il round (metodo changePlayer del ServerContoller):

            for (int i = 1; i < match.getNumPlayers() * 2; i++)
                match.changePlayer();

        }

        match.endMatch();

    }
}
