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

    public boolean checkBox(Box box, Dice dice){
        if(box.getColor().equals(Color.WHITE)){
            return true;
        }
        else if(box.getColor().equals(dice.getDiceColor())){
            return true;
        }
        else if(box.getShade()==dice.getNumFacciaUp()){
            return true;
        }
        else
            return false;
    }

    public boolean checkPosition(Box box){
        int row=box.getX();
        int column=box.getY();

        if(!(boxes[row-1][column].isEmpty() && boxes[row-1][column-1].isEmpty() && boxes[row-1][column+1].isEmpty() &&
                boxes[row+1][column-1].isEmpty() && boxes[row+1][column+1].isEmpty() && boxes[row+1][column].isEmpty()
                && boxes[row][column-1].isEmpty() && boxes[row][column+1].isEmpty())) {
            return true;
        }
        return false;
    } //manca tutta gestione bordi e angoli

    public boolean checkAdjacent(Box box,Dice dice){
        int row=box.getX();
        int column=box.getY();

        if(!(boxes[row-1][column].isEmpty())) {
            if (boxes[row - 1][column].getColor().equals(dice.getDiceColor()) || boxes[row - 1][column].getShade() == dice.getNumFacciaUp())
                return false;
        }
        else if(!(boxes[row+1][column].isEmpty())) {
            if (boxes[row+1][column].getColor().equals(dice.getDiceColor()) || boxes[row+1][column].getShade() == dice.getNumFacciaUp())
                return false;
        }
        else if(!(boxes[row][column-1].isEmpty())) {
            if (boxes[row][column - 1].getColor().equals(dice.getDiceColor()) || boxes[row][column - 1].getShade() == dice.getNumFacciaUp())
                return false;
        }
        else if(!(boxes[row][column+1].isEmpty())) {
            if (boxes[row][column+1].getColor().equals(dice.getDiceColor()) || boxes[row][column+1].getShade() == dice.getNumFacciaUp())
                return false;
        }
        return true;
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
