package Progetto;

import java.lang.reflect.Array;
import java.util.*;

public class Bag {
    private ArrayList<Dice> dices;
    private ArrayList<Dice> drawnDices;
    public Bag() {
        dices= new ArrayList<Dice>();
    }

    public void setDices(){

        Dice dice;

        for(int i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.RED);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.BLUE);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.YELLOW);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.GREEN);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.PURPLE);
            dices.add(dice);
        }
    }

    //estrae tot dadi casuali in base al numero dei giocatori
    public ArrayList<Dice> draw (int numPlayers){ //da sistemare --> subList+removeRange, non sono riuscita a usarle
        int bound=(numPlayers*2)+1;
        Collections.shuffle(dices);
        drawnDices= new ArrayList<Dice>();
        for(int i=0;i<bound;i++){
            drawnDices.add(dices.get(i));
        }
        for(int i=0;i<bound;i++){
            dices.remove(i);
        }
        return drawnDices;
    }

    public int getSize(){
        return dices.size();
    }
}
