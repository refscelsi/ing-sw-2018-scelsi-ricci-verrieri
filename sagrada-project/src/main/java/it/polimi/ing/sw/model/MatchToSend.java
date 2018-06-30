package it.polimi.ing.sw.model;

import com.google.gson.Gson;
import it.polimi.ing.sw.controller.common.MatchInterface;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class MatchToSend {

    private MatchInterface match;
    private ArrayList<Integer> draftPool;

    public MatchToSend(MatchInterface match){
        this.match=match;
        this.draftPool=new ArrayList<Integer>();
    }

    /*Gson gson= new Gson();
    String json= gson.toJson(new Dice(match.getDraftPool().getDice(0).getDiceColor(), match.getDraftPool().getDice(0).getNumFacciaUp()));
    Gson gson1= new Gson();
    String json1= gson.toJson(new DraftPool(json));

    class Dice{
        String color;
        String face;

        Dice(Color color, int face){
            this.color=color.toString();
            this.face= String.valueOf(face);
        }

    class DraftPool{
            ArrayList<Gson> dices;

            DraftPool(ArrayList<Gson> dices){
                this.dices=dices;
            }
    }*/
    }








    

