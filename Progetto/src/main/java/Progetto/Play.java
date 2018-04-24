package Progetto;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box,Dice dice){
        if(box.isEmpty()){
            if(box.getColor().equals(dice.getDiceColor()) || box.getColor().equals(Color.WHITE)){
                if(box.getShade()==dice.getNumFacciaUp()){

                    //check posizione (dado adiacente e dado di colore/sfumatura diversa) --> tutti metodi check che chiamo qui giusto?
                }
            }
            box.placeDice(dice);

        }

        else
            return;
    }


    public void useCard(){

    }
}
