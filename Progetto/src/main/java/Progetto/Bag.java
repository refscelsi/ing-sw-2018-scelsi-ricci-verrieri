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

    public Dice drow(){
        Collections.shuffle(dices);
        return dices.get(0);
    }

    public int getSize(){
        return dices.size();
    }
}
