package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class PinzaSgrossatrice extends ToolCard {

    final int id=1;


    public PinzaSgrossatrice() throws ToolCardException, NotValidException {
        super();
    }


    public void execute(Dice dice, char operation) throws ToolCardException {
        int value = dice.getNumFacciaUp();

        switch(operation){
            case '+':
                if(value<6 && value>0)
                    dice.setNumFacciaUp(value++);
                else
                    throw new ToolCardException("Non puoi cambiare un 6 in 1");
                break;

            case '-':
                if(value>1 && value<7)
                    dice.setNumFacciaUp(value--);
                else
                    throw new ToolCardException("Non puoi cambiare un 1 in 6");
                break;
        }
    }

}