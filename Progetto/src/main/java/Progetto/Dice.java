package Progetto;

public class Dice {
    private short  numFacciaUp;
    private boolean riserva, usatoSuSchema;
    private Color diceColor;

    public Dice( short numFacciaUp,boolean riserva, boolean usatoSuSchema, Color diceColor){
        this.id=id;
        /*this.numFacciaUp=numFacciaUp;
        this.riserva=riserva;
        this.usatoSuSchema=usatoSuSchema;*/
        this.diceColor=diceColor;
    }

    public void setId(short id) {
        this.id = id;
    }
    public void setDiceColor(Color color){
        this.diceColor=color;
    }

    public short getId() {
        return id;
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
}
