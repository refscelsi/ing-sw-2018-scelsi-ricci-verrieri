package Progetto;

public class Scheme {
    private short id, difficulty;
    private Box caselle [];

    public Scheme(short id, short difficulty, Box caselle[]){
        this.id=id;
        this.difficulty=difficulty;
        this.caselle=caselle;
    }

    public Box[] getCaselle() {
        return caselle;
    }

    public short getDifficulty() {
        return difficulty;
    }

    public short getId() {
        return id;
    }

    public void setCaselle(Box[] caselle) {
        this.caselle = caselle;
    }

    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    public void setId(short id) {
        this.id = id;
    }
}
