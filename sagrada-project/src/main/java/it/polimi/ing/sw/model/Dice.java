package it.polimi.ing.sw.model;

import java.util.Random;


public class Dice {
    private int  numFacciaUp;
    private Color diceColor;

    public Dice(){}

    // lancia il dado

    public int throwDice() {
        Random random = new Random();
        int indice = random.nextInt(5) + 1;//genero indice casuale
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


    @Override
    public String toString() {
        return "Colore:"+this.getDiceColor();
    }
}
