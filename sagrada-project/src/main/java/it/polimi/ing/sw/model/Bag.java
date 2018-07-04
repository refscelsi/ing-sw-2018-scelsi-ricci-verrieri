package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.*;
import java.util.Random;

/**
 * Classe che rappresenta il Sacchetto da cui estrarre i dadi
 */

public class Bag{

    private ArrayList<Dice> dices = new ArrayList<Dice>();;

    /**
     * Quando viene istanziato un oggetto di questa classe viene inizializzato già con i 90 dadi dei diversi colori
     */
    public Bag() {

        Dice dice;
        int i;

        for(i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.RED);
            dices.add(dice);
        }
        for(i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.BLUE);
            dices.add(dice);
        }
        for(i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.YELLOW);
            dices.add(dice);
        }
        for(i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.GREEN);
            dices.add(dice);
        }
        for(i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.PURPLE);
            dices.add(dice);
        }
    }


    public int getSize(){
        return dices.size();
    }

    /**
     * Metodo che simula l'estrazione e il lancio di un dado e lo lancia
     * @return, ritorna un dado di un determinato colore e di un determinato numero
     */

    public Dice drawDice(){
        Collections.shuffle(dices);
        Dice dice=dices.get(0);
        dices.remove(dices.get(0));
        dice.throwDice();
        return dice;
    }


    /**
     * Metodo per l'estrazione di un certo numero di dadi casuali in base al numero dei giocatori,
     * i dati estratti vengono aggiunti ad un oggetto di tipo Riserva, che viene ritornata dal metodo
     * @param numPlayers
     * @return
     */

    public DraftPool draw (int numPlayers){
        int bound=(numPlayers*2)+1;
        DraftPool draftPool = new DraftPool();
        Dice dice;
        for(int i=0;i<bound;i++){
            dice = drawDice();
            draftPool.addDice(dice);
        }
        return draftPool;
    }

    public void addDice (Dice dice) {
        dices.add(dice);
    }

}
