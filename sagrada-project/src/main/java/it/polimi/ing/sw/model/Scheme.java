package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.io.Serializable;

public class Scheme implements Serializable {

    private int id, idRetro, difficulty;
    private Box boxes[][];
    private boolean isEmpty;



    // METODI COSTRUTTORI


    public Scheme(){}


    public Scheme( int id, int difficulty, Box boxes[][] ) {
        this.id = id;
        this.difficulty = difficulty;
        this.boxes = boxes;
        this.isEmpty = true;
    }



    // METODI SETTERS


    public void setDifficulty( int difficulty ) {
        this.difficulty = difficulty;
    }


    public void setId( int id ) {
        this.id = id;
    }


    public void setIdRetro(int idRetro) {
        this.idRetro = idRetro;
    }


    public void setBoxes( Box[][] boxes ) {
        this.boxes = boxes;
    }


    public void setNotEmpty() {
        this.isEmpty = false;
    }



    // METODI GETTERS


    public Box[][] getBoxes() {
        return boxes;
    }


    public int getDifficulty() {
        return difficulty;
    }


    public int getId() {
        return id;
    }


    public Box getBox( int row, int col ) {
        return boxes[row][col];
    }


    public int getIdRetro() {
        return idRetro;
    }


    public Boolean getIsEmpty() {
        return isEmpty;

    }


    /**
     *
     * Conta il numero di caselle vuote dello schema
     *
     * @return    il numero delle caselle vuote
     *
     */
    public int countFreeBoxes() {
        int free = 0;
        for (int i = 0; i < Constants.NUM_ROWS; i++)
            for (int j = 0; j < Constants.NUM_COLS; j++)
                if ( !boxes[i][j].isFull() )
                    free++;
        return free;
    }


    /**
     *
     * Controlla se lo schema è vuoto, cioè non contiene nessun dado
     *
     * @return    true se lo schema è vuoto
     *
     */
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


    /**
     *
     * Controlla se la casella in questione è una casella del bordo dello schema
     *
     * @param  row   la riga della casella
     * @param  col   la colonna della casella
     * @param  dice  il dado che si vuole piazzare nella casella
     * @return       true se la casella appartiene al bordo dello schema
     *
     */
    public boolean checkFirst( int row, int col, Dice dice ) {
        if ( row == 0 || row == Constants.NUM_ROWS-1 || col == 0 || col == Constants.NUM_COLS-1 ) {
            return (checkBox( row, col, dice ));
        } else
            return false;
    }


    /**
     *
     * Controlla se due colori sono uguali
     *
     * @param  color1   uno dei due colori
     * @param  color2   l'altro colore
     * @return       true se i due colori sono uguali
     *
     */
    public boolean checkColor( Color color1, Color color2 ) {
        return (color1 == color2);
    }


    /**
     *
     * Controlla se due sfumature sono uguali
     *
     * @param  shade1   una delle due sfumature
     * @param  shade2   l'altra sfumatura
     * @return       true se le due sfumature sono uguali
     *
     */
    public boolean checkShade( int shade1, int shade2 ) {
        return (shade1 == shade2);
    }


    /**
     *
     * Controlla che la il dado venga piazzato su una casella che non richieda un dado di colore diverso
     *
     * @param  row   la riga della casella
     * @param  col   la colonna della casella
     * @param  dice  il dado che si vuole piazzare nella casella
     * @return       true se il controllo va a buon fine
     *
     */
    public boolean checkBoxColor( int row, int col, Dice dice ) {
        return checkColor( boxes[row][col].getColor(), Color.WHITE ) || checkColor( boxes[row][col].getColor(), dice.getDiceColor() );
    }


    /**
     *
     * Controlla che la il dado venga piazzato su una casella che non richieda un dado di sfumatura diversa
     *
     * @param  row   la riga della casella
     * @param  col   la colonna della casella
     * @param  dice  il dado che si vuole piazzare nella casella
     * @return       true se il controllo va a buon fine
     *
     */
    public boolean checkBoxShade( int row, int col, Dice dice ) {
        return checkShade( boxes[row][col].getShade(), 0 ) || checkShade( boxes[row][col].getShade(), dice.getNumFacciaUp() );
    }


    /**
     *
     * Controlla che la il dado venga piazzato su una casella che non richieda un dado di colore o sfumatura diversi
     *
     * @param  row   la riga della casella
     * @param  col   la colonna della casella
     * @param  dice  il dado che si vuole piazzare nella casella
     * @return       true se il controllo va a buon fine
     *
     */
    public boolean checkBox( int row , int col, Dice dice ) {
        return checkBoxColor( row, col, dice ) && checkBoxShade(row,col, dice );
    }


    /**
     *
     * Fa i controlli sulle caselle adiacenti alla casella dove si vuole piazzare il dado:
     * op = 0: controllo di piazzamento di un dado
     * op = 1: controllo di movimento di un dado
     * op = 2: controllo per la toolcard 9
     *
     * @param  row      la riga della casella
     * @param  column   la colonna della casella
     * @param  dice     il dado che si vuole piazzare nella casella
     * @param  op       op = 0: controllo di piazzamento di un dado
     *                  op = 1: controllo di movimento di un dado
     *                  op = 2: controllo per la toolcard 9
     * @return       true se il controllo va a buon fine
     *
     */
    public boolean checkIfHasDiceAdjacent( int row, int column, Dice dice, int op ) {
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


    /**
     *
     * Controlla che uno specifico dado che tocca ortogonalmente il dado che si vuole piazzare non abbia colore
     * o sfumatura uguali
     *
     * @param  i      la riga della casella dove è posizionato questo specifico dado
     * @param  y      la colonna della casella dove è posizionato questo specifico dado
     * @param  dice   il dado che si vuole piazzare
     * @return        true se il controllo va a buon fine
     *
     */
    public Boolean checkOrthogonal( int i, int y, Dice dice ) {
        if ( checkColor( boxes[i][y].getDice().getDiceColor(), dice.getDiceColor() ) || checkShade( boxes[i][y].getDice().getNumFacciaUp(), dice.getNumFacciaUp() ) )
            return false;
        return true;
    }


    /**
     *
     * Controlla tutte le restrizioni di piazzamento e se sono rispettate piazza il dado
     *
     * @param  row      la riga della casella dove è posizionato questo specifico dado
     * @param  col      la colonna della casella dove è posizionato questo specifico dado
     * @param  dice     il dado che si vuole piazzare
     *
     */
    public void placeDice( int row, int col, Dice dice ) throws NotValidException {
        if ( isEmpty() ) {
            if ( !checkFirst( row,col,dice ) )
                throw new NotValidException( "Devi inserire il primo dado in una casella del bordo dello schema, rispettando le condizioni di piazzamento!" );
            else
                setNotEmpty();
        } else {
            if(boxes[row][col].isFull()){
                throw new NotValidException("la casella è piena caro");
            }
            else if (!checkBox( row, col, dice)) {
                throw new NotValidException("Non stai rispettando le restrizioni di piazzamento!");
            }
            else if(!checkIfHasDiceAdjacent( row, col, dice, 0 )){
                throw new NotValidException("Non stai rispettando le restrizioni di piazzamento! ");
            }
        }

        boxes[row][col].placeDice( dice );
    }

    /**
     *
     * Crea una copia dello schema (per passarlo dal server al client attraverso Socket)
     *
     * @return    la copia dello schema
     *
     */
    public Scheme schemeClone(){
        Scheme schemeClone=new Scheme();
        schemeClone.setIdRetro(this.idRetro);
        schemeClone.setId(this.id);
        schemeClone.setDifficulty(this.difficulty);
        Box[][] boxesClone=new Box[4][5];
        for (int i = 0; i < Constants.NUM_ROWS; i++) {
            for (int j = 0; j < Constants.NUM_COLS; j++) {
                if (!boxes[i][j].isFull()) {
                    Box boxClone=boxes[i][j].cloneBox();
                    boxesClone[i][j]=boxClone;
                }
                else
                { Box boxClone=boxes[i][j].cloneBox();
                    Dice diceClone= boxes[i][j].getDice().cloneDice();
                    boxClone.placeDice(diceClone);
                    boxesClone[i][j]=boxClone;
                }
            }
        }
        schemeClone.setBoxes(boxesClone);
        return schemeClone;
    }

}
