package Progetto;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private ArrayList<Dice> dices;

    public Bag(){
        for(int i=0; i<18; i++){
            Dice dice=new dice();
            dice.setDiceColor(Color.RED);
            dices[i]=dice;

        }
    }

    public Dice drow(){

    }
}
