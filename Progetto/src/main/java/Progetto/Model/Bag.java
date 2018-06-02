package progetto.model;
import java.util.*;

public class Bag {
    private ArrayList<Dice> dices;
    private DraftPool draftPool;

    public Bag() {
        dices = new ArrayList<Dice>();
        draftPool = new DraftPool();
    }

    //inizializza il sacchetto coi dati
    public void setDices(){
        Dice dice;

        for(int i=0; i<18; i++){
            dice=new Dice();
            dice.setDiceColor(Color.RED);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.BLUE);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.YELLOW);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.GREEN);
            dices.add(dice);
        }
        for(int i=0; i<18;i++){
            dice=new Dice();
            dice.setDiceColor(Color.PURPLE);
            dices.add(dice);
        }
    }

    public Dice drawDice(){
        Collections.shuffle(dices);
        Dice dice=dices.get(0);
        dices.remove(dices.get(0));
        return dice;
    }
    //estrae tot dadi casuali in base al numero dei giocatori e li inserisce nella riserva
    public DraftPool draw (int numPlayers){ //da sistemare --> subList+removeRange, non sono riuscita a usarle
        int bound=(numPlayers*2)+1;
        Collections.shuffle(dices);
        for(int i=0;i<bound;i++){
            draftPool.addDice(this.drawDice());
        }
        return draftPool;
    }

    public int getSize(){
        return dices.size();
    }
}
