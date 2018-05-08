package addscheme;
import java.util.Random;


public class Dice {
    private int  numFacciaUp;
    private boolean riserva, usatoSuSchema;
    private Color diceColor;

    public Dice(){}

    //tira il dado
    public int throwDice(){
        int possibleValues[]={1,2,3,4,5,6}; //valori possibili del dado
        Random random=new Random();
        int indice=random.nextInt(6);//genero indice casuale
        setNumFacciaUp(possibleValues[indice]);
        return possibleValues[indice]; //ritorno valore dell'indice casuale
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

    //servono davvero questi metodi?
    public boolean isRiserva() {return riserva; }
    public boolean isUsatoSuSchema() {
        return usatoSuSchema;
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
