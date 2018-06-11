package it.polimi.ing.sw;

import it.polimi.ing.sw.model.*;

public class App
{
    public static void main( String[] args )
    {
        Bag bag = new Bag();
        bag.setDices();
        ShowDraftPool show = new ShowDraftPool(bag.draw(4));
        RoundTrack roundTrack = new RoundTrack();
        Dice dice1 = new Dice();
        dice1.setDiceColor(Color.RED);
        dice1.setNumFacciaUp(4);
        roundTrack.addDice(0,dice1);
        Dice dice2 = new Dice();
        dice2.setDiceColor(Color.GREEN);
        dice2.setNumFacciaUp(3);
        roundTrack.addDice(0,dice2);
        Dice dice3 = new Dice();
        dice3.setDiceColor(Color.BLUE);
        dice3.setNumFacciaUp(1);
        roundTrack.addDice(0,dice3);
        //ShowRoundTrack roundtr = new ShowRoundTrack(roundTrack);
        /*CLI prova = new CLI();
        prova.printSquare(5, 2, true, Color.GREEN);*/
    }
}


