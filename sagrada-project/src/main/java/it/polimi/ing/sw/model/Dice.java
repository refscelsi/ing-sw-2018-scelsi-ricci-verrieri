package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe che rappresenta un generico Dado
 */

public class Dice implements Serializable{
    private int  numFacciaUp;
    private Color diceColor;

    public Dice(){}

    public Dice(int numFacciaUp, Color color) {
        this.numFacciaUp = numFacciaUp;
        diceColor = color;
    }


    /**
     * Metodo che simula il lancio di un dado
     * @return un numero intero casuale tra 1 e 6
     */
    public int throwDice() {
        Random random = new Random();
        int indice = random.nextInt(6) + 1;
        setNumFacciaUp(indice);
        return indice;
    }

    /**
     * Metodi SET
     *
     */

    public void setDiceColor(Color color){
        this.diceColor=color;
    }

    public void setNumFacciaUp(int numFacciaUp) {
        this.numFacciaUp = numFacciaUp;
    }

    public void setDice(int numFacciaUp, Color color) {
        this.numFacciaUp = numFacciaUp;
        diceColor = color;
    }

    /**
     * Metodi GET
     *
     */

    public Color getDiceColor() {
        return diceColor;
    }

    public int getNumFacciaUp() {
        return numFacciaUp;
    }

    /**
     * Metodo che clona un dado
     * @return un nuovo oggetto dado con le stesse caratteristiche del dado clonato
     */
    public Dice cloneDice(){
        Dice diceClone=new Dice();
        diceClone.setDice(this.numFacciaUp, this.diceColor);
        return diceClone;
    }



}
