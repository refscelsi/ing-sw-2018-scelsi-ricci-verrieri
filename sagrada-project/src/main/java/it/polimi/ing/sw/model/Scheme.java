package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.util.Constants;

public class Scheme {

    private int id, difficulty;
    private Box boxes[][];
    private boolean isEmpty;


    public Scheme(int id, int difficulty, Box boxes[][]){
        this.id=id;
        this.difficulty=difficulty;
        this.boxes =boxes;
        this.isEmpty=true;
    }


    public Box[][] getBoxes() {
        return boxes;
    }


    public boolean checkFirst(Box box, Dice dice){
        int row=box.getX();
        int column=box.getY();

        if(row==0 || row==3 || column==0 || column==4){
            return (checkBox(box,dice));
        }
        else
            return false;
    }


    public boolean checkColor (Color color1, Color color2) {
        return (color1==color2);
    }


    public boolean checkShade (int shade1, int shade2) {
        return (shade1==shade2);
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di colore diverso

    public boolean checkBoxColor (Box box, Dice dice) {
        return checkColor(box.getColor(),Color.WHITE) || checkColor(box.getColor(),dice.getDiceColor());
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di sfumatura diversa

    public boolean checkBoxShade (Box box, Dice dice) {
        return checkShade(box.getShade(),0) || checkShade(box.getShade(),dice.getNumFacciaUp());
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di colore o sfumatura diversi

    public boolean checkBox(Box box, Dice dice){
        return checkBoxColor(box, dice) && checkBoxShade(box, dice);
    }


    // controlla che il dado venga piazzato in una casella [adiacente a una casella piena -> solo se adj=false
    // (adj=false serve per controlli di piazzamento, mentre adj=true serve per controlli di movimento)]
    // e senza toccare ortogonalmente un dado con colore o sfumatura uguali

    public boolean checkDiceAdjacent(Box box, Dice dice, boolean adj) {
        int row=box.getX();
        int column=box.getY();
        Boolean right, left, up, down, upRight, upLeft, downRight, downLeft;

        if (row==3)
            down=false;
        else
            down=boxes[row+1][column].isFull();

        if (row==0)
            up=false;
        else
            up=boxes[row-1][column].isFull();

        if (column==0)
            left=false;
        else
            left=boxes[row][column-1].isFull();

        if (column==4)
            right=false;
        else
            right=boxes[row][column+1].isFull();

        if (column==4 || row==0)
            upRight=false;
        else
            upRight=boxes[row-1][column+1].isFull();

        if (column==4 || row==3)
            downRight=false;
        else
            downRight=boxes[row+1][column+1].isFull();

        if (column==0 || row==0)
            upLeft=false;
        else
            upLeft=boxes[row-1][column-1].isFull();

        if (column==0 || row==3)
            downLeft=false;
        else
            downLeft=boxes[row+1][column-1].isFull();

        if (right || left || up || down || upRight || upLeft || downRight || downLeft || adj) {

            if (right) {
                right = checkOrthogonal(row, column + 1, dice);
                if (!right)
                    return false;
            }
            if (left) {
                left = checkOrthogonal(row, column - 1, dice);
                if (!left)
                    return false;
            }
            if (up) {
                up = checkOrthogonal(row - 1, column, dice);
                if (!up)
                    return false;
            }
            if (down) {
                down = checkOrthogonal(row+1, column, dice);
                if (!down)
                    return false;
            }
            return true;
        }

        return false;

    }


    // controlla che uno specifico dado che tocca ortogonalmente il dado che si vuole piazzare non abbia colore
    // o sfumatura uguali

    public Boolean checkOrthogonal(int i, int y, Dice dice) {
        if (checkColor(boxes[i][y].getDice().getDiceColor(),dice.getDiceColor()) || checkShade(boxes[i][y].getDice().getNumFacciaUp(),dice.getNumFacciaUp()))
            return false;
        return true;
    }


    // controlla tutte le restrizioni di piazzamento

    public Boolean checkPlacementRestrictions(Box box, Dice dice) throws NotValidException {
        if (isEmpty()) {
            if (!checkFirst(box, dice))
                throw new NotValidException("Devi inserire il primo dado in una casella del bordo dello schema!");
            else {
                if (!checkBox(box, dice)&&checkDiceAdjacent(box, dice, false))
                    throw new NotValidException("Non stai rispettando le restrizioni di piazzamento!");
            }
        }
        return true;
    }


    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }


    public boolean isEmpty(){
        this.isEmpty=true;
        for(int i=0;i<boxes.length;i++){
            for(int j=0;j<boxes[i].length;j++){
                if(boxes[i][j].isFull()){
                    setNotEmpty();
                }
            }
        }
        return this.isEmpty;
    }


    public void setNotEmpty(){
        this.isEmpty=false;
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


    public int countFreeBoxes() {
        int free = 0;
        for (int i=0; i<boxes.length; i++)
            for (int j=0; j<boxes[i].length; j++)
                if (!boxes[i][j].isFull())
                    free++;
        return free;
    }


    public Box getBox (int row, int col) {
        return boxes[row][col];
    }


}
