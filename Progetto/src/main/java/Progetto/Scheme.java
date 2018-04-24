package Progetto;

public class Scheme {
    private int id, difficulty;
    private Box boxes[][];

    public Scheme(int id, int difficulty, Box boxes[][]){
        this.id=id;
        this.difficulty=difficulty;
        this.boxes =boxes;
    }
    public Box[][] getboxes() {
        return boxes;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getId() {
        return id;
    }

    public void setboxes(Box[][] boxes
    ) {
        this.boxes = boxes;
    }

    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }

    public void setId(short id) {
        this.id = id;
    }


}
