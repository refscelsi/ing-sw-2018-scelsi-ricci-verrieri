package progetto.model.toolCard;

import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;

public abstract class ToolCard {

    private int numOfTokens;
    private int id;

    public ToolCard () throws ToolCardException, NotValidException {
        numOfTokens = 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNumOfTokens () {
        return numOfTokens;
    }

    public void setNumOfTokens (int num) {
        numOfTokens = num;
    }

    public int getId(){
        return id;
    }

}
