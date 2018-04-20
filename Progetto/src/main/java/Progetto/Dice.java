package Progetto;

public class Dice {
    private short  numFacciaUp;
    private boolean riserva, usatoSuSchema;
    private Color diceColor;

    /*public Dice( short numFacciaUp,boolean riserva, boolean usatoSuSchema, Color diceColor){
        this.numFacciaUp=numFacciaUp;
        this.riserva=riserva;
        this.usatoSuSchema=usatoSuSchema;
        this.diceColor=diceColor;
    }*/


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
