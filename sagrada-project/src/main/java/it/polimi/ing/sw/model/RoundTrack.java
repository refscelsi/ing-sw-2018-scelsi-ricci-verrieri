package it.polimi.ing.sw.model;


public class RoundTrack {

    private DraftPool[] roundTrack;
    private int round = 0;

    public RoundTrack(){
        roundTrack = new DraftPool[10];
    }

    public void addDicesRound(DraftPool draftPool){
        roundTrack[round] = new DraftPool();
        roundTrack[round].addDraftPool(draftPool);
        round++;
    }

    public DraftPool[] getRoundTrack() {
        return roundTrack;
    }

    public DraftPool getDicesRound(int round){
        return roundTrack[round-1];
    }

    public int getNumberOfDices(int round) {
        return roundTrack[round-1].getSize();
    }

    public int getRoundTrackSize() {
        return round;
    }

    public int getMaxNumberOfDices() {
        int num, max = 0;
        for (int i=1; i<=round; i++) {
            num = getNumberOfDices(i);
            if (num > max)
                max = num;
        }

        return max;

    }

}

