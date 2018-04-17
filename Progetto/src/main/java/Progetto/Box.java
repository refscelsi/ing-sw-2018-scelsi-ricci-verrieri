package Progetto;

public class Box {
    private short x,y,idDado,idSchema,restrizioneNumero;
    //da aggiungere colore

    public Box(short x, short y, short idDado, short idSchema, short restrizioneNumero /* colore*/){
        this.x=x;
        this.y=y;
        this.idDado=idDado;
        this.idSchema=idSchema;
        this.restrizioneNumero=restrizioneNumero;
        // colore
    }

    public short getIdDado() {
        return idDado;
    }

    public short getIdSchema() {
        return idSchema;
    }

    public short getRestrizioneNumero() {
        return restrizioneNumero;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public void setIdDado(short idDado) {
        this.idDado = idDado;
    }

    public void setIdSchema(short idSchema) {
        this.idSchema = idSchema;
    }

    public void setRestrizioneNumero(short restrizioneNumero) {
        this.restrizioneNumero = restrizioneNumero;
    }

    public void setX(short x) {
        this.x = x;
    }

    public void setY(short y) {
        this.y = y;
    }


}
