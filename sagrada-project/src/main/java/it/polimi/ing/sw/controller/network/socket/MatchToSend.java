package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.util.Constants;

import java.util.ArrayList;

public class MatchToSend {

    private Match match;

    public MatchToSend(Match match) {

        this.match = match;
    }

    class StringMatch{
        String roundTrack;
        String draftPool;
        String players;
        String PublicObjectiveCards;

        public StringMatch(String roundTrack, String draftPool, String players, String publicObjectiveCards){
            this.draftPool=draftPool;
            this.roundTrack=roundTrack;
            this.players=players;
            this.PublicObjectiveCards= publicObjectiveCards;
        }
    }

    //metodo per creare il match da mandare

    public String convertMatch(){
        String draftPool= convertDraftPool();
        String roundTrack= convertRoundTrack();
        String players= convertPlayers();
        String objectiveCards= convertPublicObjectives();
        Gson gson=new Gson();
        String json=gson.toJson(new StringMatch(roundTrack,draftPool,players,objectiveCards));
        return json;
    }

    public String convertStartMatch(){
        Gson gson=new Gson();
        return gson.toJson(new StringMatch(convertRoundTrack(),convertDraftPool(),convertStartPlayers(), convertPublicObjectives()));
    }




    //metodi per convertire gli elementi

    //DRAFTPOOL
    public ArrayList<String> convertDicesDraftPool(){
    ArrayList<String> draftpoolDices =new ArrayList<String>();
        for (int i = 0; i < match.getDraftPool().getSize(); i++) {
            Gson gson = new Gson();
            String json = gson.toJson(new Dice(match.getDraftPool().getDice(i).getDiceColor(), match.getDraftPool().getDice(i).getNumFacciaUp()));
            draftpoolDices.add(json);
        }
        return draftpoolDices;
    }
    public String convertDraftPool(){
        if(match.getDraftPool()==null){
            return null;
        }else {
            ArrayList<String> dices=convertDicesDraftPool();
            Gson gson = new Gson();
            String json= gson.toJson(new DraftPool(match.getDraftPool()));
            return json;
        }
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
        DraftPool draftPool;
        DraftPool()
    }

    //RUNDTRACK
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
        if(match.getRoundTrack().getRoundTrackSize()==0){
            return null;
        }
        else {
            for (int i = 0; i < match.getRoundTrack().getRoundTrackSize(); i++) {
                roundTrack.add(convertRoundDices(i));
            }
            Gson gson = new Gson();
            String json=gson.toJson(new RoundTrack(roundTrack));
            return json;
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

    //PUBLICOBCARD
    public String convertPublicObjectiveCard(int index){
        Gson gson= new Gson();
        String json=gson.toJson(new PublicObjectiveCard(match.getPublicObjectives().get(index).getDescription(),match.getPublicObjectives().get(index).getName(),match.getPublicObjectives().get(index).getScore()));
        return json;
    }

    public String convertPublicObjectives(){
        ArrayList<String> cards= new ArrayList<>();

        if(match.getPublicObjectives()==null){
            return null;
        }
        else {
            for (int i=0; i<match.getPublicObjectives().size(); i++){
                cards.add(convertPublicObjectiveCard(i));
            }
        }
        Gson gson=new Gson();
        String json= gson.toJson(new PublicObjectives(cards));
        return json;
    }
    class PublicObjectives{
        ArrayList<String> cards;
        PublicObjectives(ArrayList<String> cards){ this.cards=cards;}
    }
    class PublicObjectiveCard{
        String description;
        String name;
        String score;
        PublicObjectiveCard(String description, String name,int score){
            this.description=description;
            this.name=name;
            this.score=String.valueOf(score);
        }
    }

    //SCHEMI

    public String convertScheme(Scheme scheme){
        Gson gson=new Gson();
        String json=gson.toJson(new StringScheme(scheme.getId(),scheme.getBoxes()));
        return json;
    }
    class StringScheme{
        String id;
        Box[][]boxes;
        StringScheme(int id, Box[][] boxes){
            this.id= String.valueOf(id);
            this.boxes=boxes;
        }

    }



    //GIOCATORI
    public String convertPlayer(int index){
        Gson gson=new Gson();
        return gson.toJson(new StringPlayer(match.getPlayers().get(index).getNickname(), convertScheme(match.getPlayers().get(index).getScheme())));
    }

    public String convertStartPlayer(int index){
        Gson gson=new Gson();
        return gson.toJson(new StringStartPlayer(match.getPlayers().get(index).getNickname(), convertSchemeToChoose(match.getPlayers().get(index))));
    }

    public ArrayList<String> convertSchemeToChoose(Player player){
        ArrayList<String> schemes=new ArrayList<>();
        for(Scheme scheme: player.getSchemesToChoose()){
            schemes.add(convertScheme(scheme));
        }
        return schemes;
    }
    public String convertPlayers(){
        ArrayList<String> players= new ArrayList<>();
        if(match.getPlayers()==null){
            return null;
        }
        else{
            for(Player player: match.getPlayers()){
                Gson gson= new Gson();
                players.add(gson.toJson(new StringPlayer(player.getNickname(), convertScheme(player.getScheme()))));
            }
            Gson gson=new Gson();
            return gson.toJson(new Players(players));
        }
    }
    public String convertStartPlayers() {
        ArrayList<String> players= new ArrayList<>();
        if(match.getPlayers()==null){
            return null;
        }
        else{
            for(Player player: match.getPlayers()){
                Gson gson= new Gson();
                players.add(gson.toJson(new StringStartPlayer(player.getNickname(),convertSchemeToChoose(player))));
            }
            Gson gson=new Gson();
            return gson.toJson(new Players(players));
        }
    }
    class StringPlayer{
        String nickname;
        String scheme;
        StringPlayer(String nickname,String scheme){
            this.nickname=nickname;
            this.scheme=scheme;
        }
    }
    class StringStartPlayer{
        String nickname;
        ArrayList<String> schemeToChoose;
        StringStartPlayer(String nickname, ArrayList<String> schemeToChoose){
            this.nickname=nickname;
            this.schemeToChoose=schemeToChoose;
        }
    }
    class Players{
        ArrayList<String> players;
        Players(ArrayList<String> players){
            this.players=players;
        }
    }


    //classi per la costruzione dei json







}







//TODO TOOLCARD E CARTE PRIVATE



