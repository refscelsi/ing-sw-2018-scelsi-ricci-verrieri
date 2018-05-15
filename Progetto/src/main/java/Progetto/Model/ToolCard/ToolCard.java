package Progetto.Model.ToolCard;
import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public abstract class ToolCard {

    private int numOfTokens;

    public ToolCard () throws ToolCardException, NotValidException {
        numOfTokens = 1;
    }

    public int getNumOfTokens () {
        return numOfTokens;
    }

    public void setNumOfTokens (int num) {
        numOfTokens = num;
    }

}
