package Progetto;

public class App {
    public static void main( String[] args ){
        System.out.println( "Ciao sono bellissima" );
        /*Random r = new Random();
        int id = r.nextInt(5000);
        int numPlayer=4;
        Match m=new Match(id,numPlayer);*/

        Bag bag= new Bag();
        bag.setDices();
        int i=bag.getSize();
        System.out.print(i);

        System.out.println("Colore"+bag.draw(4));
        System.out.println(bag.getSize());

        Dice dice= new Dice();
        System.out.println(dice.throwDice());

        //m.startMatch();
    }
}
