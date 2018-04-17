package Progetto;

import java.util.*;
public class App{
    public static void main( String[] args ){
        System.out.println( "Test Github4" );
        Random r = new Random();
        int id = r.nextInt(5000);
        Match m=new Match(id);

        m.startMatch();
    }
}
