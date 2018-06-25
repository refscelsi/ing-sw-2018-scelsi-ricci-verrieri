package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.io.Serializable;

public class Scheme implements Serializable {

    private int id, idRetro, difficulty;
    private Box boxes[][];
    private boolean isEmpty;


    public Scheme( int id, int difficulty, Box boxes[][] ) {
        this.id = id;
        this.difficulty = difficulty;
        this.boxes = boxes;
        this.isEmpty = true;
    }


    public Box[][] getBoxes() {
        return boxes;
    }


    public boolean checkFirst( Box box, Dice dice ) {
        int row = box.getX();
        int column = box.getY();

        if ( row == 0 || row == Constants.NUM_ROWS-1 || column == 0 || column == Constants.NUM_COLS-1 ) {
            return (checkBox( box, dice ));
        } else
            return false;
    }


    public boolean checkColor( Color color1, Color color2 ) {
        return (color1 == color2);
    }


    public boolean checkShade( int shade1, int shade2 ) {
        return (shade1 == shade2);
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di colore diverso

    public boolean checkBoxColor( Box box, Dice dice ) {
        return checkColor( box.getColor(), Color.WHITE ) || checkColor( box.getColor(), dice.getDiceColor() );
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di sfumatura diversa

    public boolean checkBoxShade( Box box, Dice dice ) {
        return checkShade( box.getShade(), 0 ) || checkShade( box.getShade(), dice.getNumFacciaUp() );
    }


    // controlla che la il dado venga piazzato su una casella che non richieda un dado di colore o sfumatura diversi

    public boolean checkBox( Box box, Dice dice ) {
        return checkBoxColor( box, dice ) && checkBoxShade( box, dice );
    }


    // fa i controlli sulle caselle adiacenti alla casella dove si vuole piazzare il dado
    // op = 0: controllo di piazzamento di un dado
    // op = 1: controllo di movimento di un dado
    // op = 2: controllo per la toolcard 9

    public boolean checkIfHasDiceAdjacent( Box box, Dice dice, int op ) {
        int row = box.getX();
        int column = box.getY();
        Boolean right, left, up, down, upRight, upLeft, downRight, downLeft;

        if ( row == Constants.NUM_ROWS-1 )
            down = false;
        else
            down = boxes[row + 1][column].isFull();

        if ( row == 0 )
            up = false;
        else
            up = boxes[row - 1][column].isFull();

        if ( column == 0 )
            left = false;
        else
            left = boxes[row][column - 1].isFull();

        if ( column == Constants.NUM_COLS-1 )
            right = false;
        else
            right = boxes[row][column + 1].isFull();

        if ( column == Constants.NUM_COLS-1 || row == 0 )
            upRight = false;
        else
            upRight = boxes[row - 1][column + 1].isFull();

        if ( column == Constants.NUM_COLS-1 || row == Constants.NUM_ROWS-1 )
            downRight = false;
        else
            downRight = boxes[row + 1][column + 1].isFull();

        if ( column == 0 || row == 0 )
            upLeft = false;
        else
            upLeft = boxes[row - 1][column - 1].isFull();

        if ( column == 0 || row == Constants.NUM_ROWS-1 )
            downLeft = false;
        else
            downLeft = boxes[row + 1][column - 1].isFull();

        switch (op) {

            case 0: {

                if ( right || left || up || down || upRight || upLeft || downRight || downLeft ) {

                    if ( right ) {
                        right = checkOrthogonal( row, column + 1, dice );
                        if ( !right )
                            return false;
                    }
                    if ( left ) {
                        left = checkOrthogonal( row, column - 1, dice );
                        if ( !left )
                            return false;
                    }
                    if ( up ) {
                        up = checkOrthogonal( row - 1, column, dice );
                        if ( !up )
                            return false;
                    }
                    if ( down ) {
                        down = checkOrthogonal( row + 1, column, dice );
                        if ( !down )
                            return false;
                    }
                    return true;
                }
                break;
            }

            case 1: {
                if ( right ) {
                    right = checkOrthogonal( row, column + 1, dice );
                    if ( !right )
                        return false;
                }
                if ( left ) {
                    left = checkOrthogonal( row, column - 1, dice );
                    if ( !left )
                        return false;
                }
                if ( up ) {
                    up = checkOrthogonal( row - 1, column, dice );
                    if ( !up )
                        return false;
                }
                if ( down ) {
                    down = checkOrthogonal( row + 1, column, dice );
                    if ( !down )
                        return false;
                }
                return true;
            }

            case 2:

                if ( right || left || up || down || upRight || upLeft || downRight || downLeft )
                    return false;
                else
                    return true;

        }

        return false;

    }

    // controlla che uno specifico dado che tocca ortogonalmente il dado che si vuole piazzare non abbia colore
    // o sfumatura uguali

    public Boolean checkOrthogonal( int i, int y, Dice dice ) {
        if ( checkColor( boxes[i][y].getDice().getDiceColor(), dice.getDiceColor() ) || checkShade( boxes[i][y].getDice().getNumFacciaUp(), dice.getNumFacciaUp() ) )
            return false;
        return true;
    }

    // controlla tutte le restrizioni di piazzamento e se sono rispettate piazza il dado

    public void placeDice( Box box, Dice dice ) throws NotValidException {
        if ( isEmpty() ) {
            System.out.println("primo dado");
            if ( !checkFirst( box, dice ) )
                throw new NotValidException( "Devi inserire il primo dado in una casella del bordo dello schema!" );
            else
                setNotEmpty();
        } else {
            if ( box.isFull() || !checkBox( box, dice ) && !checkIfHasDiceAdjacent( box, dice, 0 ) )
                throw new NotValidException( "Non stai rispettando le restrizioni di piazzamento!" );
        }

        box.placeDice( dice );
    }

    public void setBoxes( Box[][] boxes ) {
        this.boxes = boxes;
    }

    public boolean isEmpty() {
        
        this.isEmpty = true;
        for (int i = 0; i < Constants.NUM_ROWS; i++) {
            for (int j = 0; j < Constants.NUM_COLS; j++) {
                if ( boxes[i][j].isFull() ) {
                    setNotEmpty();
                }
            }
        }
        return this.isEmpty;
    }

    public void setNotEmpty() {
        this.isEmpty = false;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getId() {
        return id;
    }

    public int getIdRetro() {
        return idRetro;
    }

    public void setDifficulty( short difficulty ) {
        this.difficulty = difficulty;
    }

    public void setId( short id ) {
        this.id = id;
    }

    public void setIdRetro(int idRetro) {
        this.idRetro = idRetro;
    }

    public int countFreeBoxes() {
        int free = 0;
        for (int i = 0; i < Constants.NUM_ROWS; i++)
            for (int j = 0; j < Constants.NUM_COLS; j++)
                if ( !boxes[i][j].isFull() )
                    free++;
        return free;
    }

    public Box getBox( int row, int col ) {
        return boxes[row][col];
    }

}
