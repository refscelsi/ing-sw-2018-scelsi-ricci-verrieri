package Progetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bag {
    private ArrayList<Dice> dices;

    public Bag(){
        this.dices= new ArrayList<Dice>();
    }

    public void setDices(){
        for(int i=0; i<18; i++){
            Dice dice=new Dice();
            dice.setDiceColor(Color.RED);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            Dice dice=new Dice();
            dice.setDiceColor(Color.BLUE);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            Dice dice=new Dice();
            dice.setDiceColor(Color.YELLOW);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            Dice dice=new Dice();
            dice.setDiceColor(Color.GREEN);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            Dice dice=new Dice();
            dice.setDiceColor(Color.PURPLE);
            dices.add(dice);
        }
    }

    public ArrayList<dice> draw (int numPlayers){
        Collections.shuffle(dices);
        ArrayList<Dice> drawnDices;
        drawnDices = dices.subList(0, (numPlayers*2)+1);
        dices.removeRange(0, (numPlayers*2)+1);
        return drawnDices;
    }

    public int getSize(){
        return dices.size();
    }
}
