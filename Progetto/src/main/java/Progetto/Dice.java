package Progetto;

public class Dice {
    private short id, numFacciaUp;
    private boolean riserva, usatoSuSchema;
    //aggiungere colore

    public Dice(short id, short numFacciaUp,boolean riserva, boolean usatoSuSchema /*colore*/){
        this.id=id;
        this.numFacciaUp=numFacciaUp;
        this.riserva=riserva;
        this.usatoSuSchema=usatoSuSchema;
        //colore
    }

    public void setId(short id) {
        this.id = id;
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
