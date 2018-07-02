package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.Random;


public class Dice implements Serializable{
    private int  numFacciaUp;
    private Color diceColor;

    public Dice(){}

    public Dice(int numFacciaUp, Color color) {
        this.numFacciaUp = numFacciaUp;
        diceColor = color;
    }

    // lancia il dado

    public int throwDice() {
        Random random = new Random();
        int indice = random.nextInt(6) + 1;//genero indice casuale
        setNumFacciaUp(indice);
        return indice; //ritorno valore dell'indice casuale
    }

    public void setDiceColor(Color color){
        this.diceColor=color;
    }

    public Color getDiceColor() {
        return diceColor;
    }

    public int getNumFacciaUp() {
        return numFacciaUp;
    }

    public void setNumFacciaUp(int numFacciaUp) {
        this.numFacciaUp = numFacciaUp;
    }

    public void setDice(int numFacciaUp, Color color) {
        this.numFacciaUp = numFacciaUp;
        diceColor = color;
    }


    @Override
    public String toString() {
        return "Colore:"+this.getDiceColor();
    }
}
