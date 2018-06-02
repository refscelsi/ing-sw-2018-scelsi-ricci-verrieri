package progetto.model.toolCard;

import progetto.model.Dice;
import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;

public class PinzaSgrossatrice extends ToolCard {
    final int id=1;

    public PinzaSgrossatrice()throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, char operation) { //gestire eccezioni per 1 e 6 --> valori non validi
        int value = dice.getNumFacciaUp();

        switch(operation){
            case '+':
                if(value<6 && value>0){
                    dice.setNumFacciaUp(value++);
                }
                break;

            case '-':
                if(value>2 && value<7){
                    dice.setNumFacciaUp(value--);
                }
                break;
        }
    }
}