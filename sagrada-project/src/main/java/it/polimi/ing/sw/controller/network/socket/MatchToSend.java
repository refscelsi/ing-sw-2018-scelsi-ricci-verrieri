package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Match;

import java.util.ArrayList;

public class MatchToSend {

    private Match match;
    private ArrayList<String> draftpoolDices;

    public MatchToSend(Match match) {

        this.match = match;
    }

    public void convertDicesDraftPool(){
        draftpoolDices =new ArrayList<String>();
        if(match.getDraftPool()!=null) {
            for (int i = 0; i < match.getDraftPool().getSize(); i++) {
                Gson gson = new Gson();
                String json = gson.toJson(new Dice(match.getDraftPool().getDice(i).getDiceColor(), match.getDraftPool().getDice(i).getNumFacciaUp()));
                draftpoolDices.add(json);
            }
        }
    }

    public String convertDraftPool(){
        if(match.getDraftPool()==null){
            return null;
        }else {
            convertDicesDraftPool();
            Gson gson = new Gson();
            String json = gson.toJson(new DraftPool(draftpoolDices));
            return json;
        }
    }

    public String convertRoundDices( int round){
        ArrayList<String> roundDices=new ArrayList<String>();

        for(int i=0;i<match.getRoundTrack().getNumberOfDices(round);i++){
            Gson gson=new Gson();
            String json=gson.toJson(new Dice(match.getRoundTrack().getDicesRound(round).getDice(i).getDiceColor(), match.getRoundTrack().getDicesRound(round).getDice(i).getNumFacciaUp()));
            roundDices.add(json);
        }
        Gson gson=new Gson();
        String json=gson.toJson(new RoundDices(roundDices));
        return json;
    }

    public String convertRoundTrack(){
        ArrayList<String> roundTrack=new ArrayList<String>();
        if(match.getRoundTrack()==null){
            return null;
        }
        else {
            for (int i = 0; i < match.getRoundTrack().getRoundTrackSize(); i++) {
                roundTrack.add(convertRoundDices(i));
            }
            Gson gson = new Gson();
            String json = gson.toJson(new RoundTrack(roundTrack));
            return json;
        }
    }



    public String convertMatch(){
        Gson gson=new Gson();
        String json=gson.toJson(new StringMatch(convertDraftPool(), convertRoundTrack()));
        return json;
    }



    class Dice {
        String color;
        String face;

        Dice(Color color, int face) {
            this.color = color.toString();
            this.face = String.valueOf(face);
        }
    }

    class DraftPool {
        ArrayList<String> dices;

        DraftPool(ArrayList<String> dices) {
            this.dices = dices;
        }
    }

    class RoundTrack{
        ArrayList<String> roundTrack;

        RoundTrack(ArrayList<String> roundTrack){
            this.roundTrack=roundTrack;
        }
    }
    class RoundDices{
        ArrayList<String> roundDices;

        RoundDices(ArrayList<String> roundDices){
            this.roundDices=roundDices;
        }
    }

    class StringMatch{
        String roundTrack;
        String draftPool;

        public StringMatch(String roundTrack, String draftPool){
            this.draftPool=draftPool;
            this.roundTrack=roundTrack;
        }
    }
}











