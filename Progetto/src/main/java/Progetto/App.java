package Progetto;

import Progetto.Controller.ServerController;
import Progetto.Model.Match;
import Progetto.View.Menu;

import javax.swing.text.View;

public class App {
    public static Menu menu;

    public static void main( String[] args ){

        Match match = new Match();
        //View view = new View()
        //ServerController serverController = new ServerController(match, view)

        //aspetto per un certo tempo durante il quale mi arrivano i nomi dei giocatori dalla view, li passo al controller e creo i giocatori

        match.initializeTable();
        match.inizializePlayer();

        //ora sono inizializzati tavolo e giocatori, quindi posso iniziare il match

        for (int j=0; j<10; j++) {

            match.startRound();

            //è iniziato un round e ha giocato il primo giocatore. Il primo giocatore passa il round (metodo changePlayer del ServerContoller):

            for (int i = 1; i < match.getNumPlayers() * 2; i++)
                match.changePlayer();

        }

        match.endMatch();


        //System.out.println( "Ciao sono bellissima" );
        /*Random r = new Random();
        int id = r.nextInt(5000);
        int numPlayer=4;
        Match m=new Match(id,numPlayer);*/

        /*
        GUI g=new GUI();
        g.setVisible(true);
        */
        //################################### classe vera

        menu =new Menu();
        menu.setVisible(true);

        //#################################### cose per testing
        /*
        Bag bag= new Bag();
        bag.setDices();
        int i=bag.getSize();
        System.out.println(i);

        System.out.println("Colore"+bag.draw(4));
        System.out.println(bag.getSize());

        Dice dice= new Dice();
        System.out.println(dice.throwDice());

        ObjectiveCardDeck deck = new ObjectiveCardDeck();
        deck.setDeck();
        deck.drawObjectiveCard(3,true);
        System.out.println(deck.getDrawnCardsSize());

*/
        //Match match = new Match(00);
        //match.inizializePlayer();

        //m.startMatch();









    }
}

