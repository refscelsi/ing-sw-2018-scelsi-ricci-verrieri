package Progetto;
import java.util.Random;


public class Dice {
    private short  numFacciaUp;
    private boolean riserva, usatoSuSchema;
    private Color diceColor;

    public Dice(){}

    //tira il dado
    public int throwDice(){
        int possibleValues[]={1,2,3,4,5,6}; //valori possibili del dado
        Random random=new Random();
        int indice=random.nextInt(6); //genero indice casuale
        return possibleValues[indice]; //ritorno valore dell'indice casuale
    }

    public void setDiceColor(Color color){
        this.diceColor=color;
    }

    public Color getDiceColor() {
        return diceColor;
    }

    public boolean isRiserva() {
        return riserva;
    }

    public boolean isUsatoSuSchema() {
        return usatoSuSchema;
    }

    public short getNumFacciaUp() {
        return numFacciaUp;
    }

    public void setNumFacciaUp(short numFacciaUp) {
        this.numFacciaUp = numFacciaUp;
    }

    public void setRiserva(boolean riserva) {
        this.riserva = riserva;
    }

    public void setUsatoSuSchema(boolean usatoSuSchema) {
        this.usatoSuSchema = usatoSuSchema;
    }

    @Override
    public String toString() {
        return "Colore:"+this.getDiceColor();
    }
}
