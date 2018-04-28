package Progetto.Model;

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
        else if(box.getColor().equals(dice.getDiceColor()) || box.getShade()==dice.getNumFacciaUp()) {
            return true;
        }
        else
            return false;
    }

    public boolean checkDiceAdjacent(Box box){
        int row=box.getX();
        int column=box.getY();

        if(row==0 || row==3 || column==0 || column==4){
            return checkBorder(box);
        }
        else
            return ((boxes[row][column+1].isFull()) || (boxes[row][column-1].isFull())|| (boxes[row+1][column].isFull()) ||
                    (boxes[row-1][column].isFull()) || (boxes[row+1][column+1].isFull()) || (boxes[row+1][column-1].isFull()) ||
                    (boxes[row-1][column+1].isFull()) || (boxes[row-1][column-1].isFull()));
    }

    public boolean checkBorder(Box box){
        int row=box.getX();
        int column=box.getY();

        if(row==0){
            if(column==0){
                return (boxes[row+1][column].isFull() || boxes[row+1][column+1].isFull() || boxes[row][column+1].isFull());
            }
            else if(column==4){
                return (boxes[row+1][column].isFull() || boxes[row+1][column-1].isFull() || boxes[row][column-1].isFull());
            }
            else
                return (boxes[row+1][column].isFull() || boxes[row+1][column-1].isFull() || boxes[row][column-1].isFull()
                        || boxes[row][column+1].isFull() || boxes[row+1][column+1].isFull());
        }
        else if(row==3){
            if(column==0){
                return (boxes[row-1][column].isFull() || boxes[row-1][column+1].isFull() || boxes[row][column+1].isFull());
            }
            else if(column==4){
                return (boxes[row-1][column].isFull() || boxes[row-1][column-1].isFull() || boxes[row][column-1].isFull());
            }
            else
                return (boxes[row-1][column].isFull() || boxes[row-1][column-1].isFull() || boxes[row][column-1].isFull()
                        || boxes[row][column+1].isFull() || boxes[row+1][column+1].isFull());
        }
        else if(column==0 &&(row==1 || row==2)){
            return (boxes[row-1][column].isFull() || boxes[row+1][column].isFull() || boxes[row][column+1].isFull()
                    || boxes[row-1][column+1].isFull() || boxes[row+1][column+1].isFull());
        }

        else if (column==4 &&(row==1 || row==2)){
            return (boxes[row-1][column].isFull() || boxes[row+1][column].isFull() || boxes[row][column-1].isFull()
                    || boxes[row-1][column-1].isFull() || boxes[row+1][column-1].isFull());
        }
        else
            return false;
    }

    public boolean checkOrthogonal(Box box, Dice dice){
        int row=box.getX();
        int column=box.getY();
        //dal primo in alto in senso orario
        Box box1=boxes[row][column+1];
        Box box2=boxes[row+1][column];
        Box box3=boxes[row][column-1];
        Box box4=boxes[row-1][column];

        if(box1.isFull()){
            return (!((box1.getDice().getDiceColor().equals(dice.getDiceColor()))||(box1.getDice().getNumFacciaUp()==dice.getNumFacciaUp())));
        }
        else if(box2.isFull()){
            return (!((box2.getDice().getDiceColor().equals(dice.getDiceColor()))||(box2.getDice().getNumFacciaUp()==dice.getNumFacciaUp())));
        }
        else if(box3.isFull()){
            return (!((box3.getDice().getDiceColor().equals(dice.getDiceColor()))||(box3.getDice().getNumFacciaUp()==dice.getNumFacciaUp())));
        }
        else if(box4.isFull()){
            return (!((box4.getDice().getDiceColor().equals(dice.getDiceColor()))||(box4.getDice().getNumFacciaUp()==dice.getNumFacciaUp())));
        }
        else
            return false;
    }

    public void setboxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public int getDifficulty() {
        return difficulty;
    }
    public int getId() {
        return id;
    }
    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }
    public void setId(short id) {
        this.id = id;
    }


}
