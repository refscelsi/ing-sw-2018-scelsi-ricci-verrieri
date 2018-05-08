package Progetto;

import Progetto.Model.Bag;
import Progetto.Model.Dice;
import Progetto.Model.Match;
import Progetto.Model.ObjectiveCard;
import Progetto.Model.ObjectiveCardDeck;

public class App {
    public static void main( String[] args ){
        System.out.println( "Ciao sono bellissima" );
        /*Random r = new Random();
        int id = r.nextInt(5000);
        int numPlayer=4;
        Match m=new Match(id,numPlayer);*/
        GUI g=new GUI();
        g.setVisible(true);

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


        //Match match = new Match(00);
        //match.inizializePlayer();

        //m.startMatch();
    }
}
