
package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.model.toolCard.*;
import it.polimi.ing.sw.controller.ClientUpdate;
//import it.polimi.ing.sw.server.Observable;

import java.util.Random;
import java.util.*;

public class Match {

    private static int last_id=0; // a cosa serve?
    private int id;
    private int numPlayers=0, numRound=0;
    private ArrayList<Player> players;
    private int[] playersRound;
    private int firstPlayer;   // primo giocatore del round. Se sono 4 giocatori può essere 0, 1, 2 o 3
    private int playerPlaying; // indice dell'array playersRound -> giocatore che sta giocando il turno
    private Bag bag;
    private ArrayList<ObjectiveCard> publicObjectives;
    private ArrayList<ToolCard> toolCards;
    private String np1;
    private DraftPool draftPool;
    private RoundTrack roundTrack;

    //array di clientObserver che mi serve per notificare la view dei cambiamenti avvenuti
    private ArrayList<ClientUpdate> playerUpdates;

    public Match() {
        id = last_id;
        last_id++;
    }

    public Match(String text1, int i, String text2) {
    }


    public Player getPlayerPlaying(){
        return players.get(playerPlaying);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }

    public void startMatch() throws ToolCardException, NotValidException {
        inizializePlayer();
        initializeTable();
    }

    public void startRound() {
        draftPool = bag.draw(numPlayers);
        firstPlayer ++;
        numRound ++;
        if (firstPlayer >= numPlayers)
            firstPlayer = 0;
        playerPlaying=firstPlayer;
        //changeRound(firstPlayer);
        //notifyNewRound(players.get(firstPlayer), draftPool);
    }

    public void endRound(){
        roundTrack.addDicesRound(draftPool);
        /*for(Dice dice : draftPool.getDraftPool()){
            roundTrack.addDice(numRound,dice);
        }*/
        changeRound(firstPlayer);
    }

    public void createNewPlayer (String nickname) {
        numPlayers++;
        players.add(new Player(nickname));
    }

    public void inizializePlayer(){
        //Bisogna distinguere se si tratta di una partita locale o su un server

        //##iniz var
        SchemeCardDeck schemeCardDeck = new SchemeCardDeck();
        ArrayList<SchemeCard> schemes = new ArrayList<SchemeCard>();
        players = new ArrayList<Player>();
        playersRound = new int[numPlayers*2];
        int possibleNumbers[] = {1,2,3,4};
        int i = 0;
        int order, oldOrder;
        String nickname;
        String splayer = "Player";
        int k = 0;
        PrivateObjectiveCardDeck privateObjectiveCardDeck = new PrivateObjectiveCardDeck();
        ArrayList<PrivateObjectiveCard> privateObjectives = new ArrayList<PrivateObjectiveCard>();
        privateObjectives = privateObjectiveCardDeck.drawObjectiveCard(numPlayers);
        firstPlayer=-1;
        playerPlaying=-1;

        //#### Locale

        while(i<numPlayers){

            players.get(i).setPrivateObjective(privateObjectives.get(i));
            schemes = schemeCardDeck.drawSchemeCard();

            do {
                Random random = new Random();
                order = random.nextInt(numPlayers); // order sarà uguale a 0, 1, 2 o 3 (se ho 4 giocatori)
                if (possibleNumbers[order]<=numPlayers&&possibleNumbers[order]!=0) {

                    // Ricky questa cosa dei 4 nomi non l'ho capita perché l'hai fatta così

                    if (k==0){
                        nickname=np1;
                    } else
                        nickname=splayer.concat(String.valueOf(k));
                    k++;
                    players.get(i).setOrderInRound(possibleNumbers[order]);
                    i++;
                }
                oldOrder = possibleNumbers[order];
                possibleNumbers[order] = 0;
            } while (oldOrder>numPlayers||oldOrder==0);

            //notifyChoiseScheme(schemes,players.get(i));

        }

        //# svuoto la ram
        splayer=null;
        nickname=null;
        possibleNumbers= null;

        //#### fine locale

        //#### server

        //da implementare

        //#### fine server

        //############################################################################### cose che boh

    }



    public void initializeTable() throws ToolCardException, NotValidException {
        //ripescare dal db le carte
        //caricarle in adeguate strutture dati
        //costruire le coppie di schemi e le carte scheme --> chiamo metodo createSchemeCard()

        // creo il sacchetto dei dadi ed estraggo le 3 carte obiettivo pubblico

        bag = new Bag();
        ObjectiveCardDeck objectiveCardDeck = new ObjectiveCardDeck();
        publicObjectives = new ArrayList<ObjectiveCard>();
        publicObjectives = objectiveCardDeck.drawObjectiveCard();
        System.out.println(publicObjectives.size());
        inizializeToolCard();
        int i=bag.getSize();
        System.out.println(i);

        System.out.println("Colore"+bag.draw(4));
        System.out.println(bag.getSize());

        Dice dice= new Dice();
        System.out.println(dice.throwDice());
        //notifyPublicObjectivesChoosen(publicObjectives);

    }

    public void inizializeToolCard() throws ToolCardException, NotValidException {
        toolCards=new ArrayList<ToolCard>();
        ArrayList<Integer> values= new ArrayList<Integer>(); //meglio usare random ma posso estrarne 3 diversi??
        for(int i=1; i<13;i++){
            values.add(i);
        }
        Collections.shuffle(values);
        for(int i=0;i<3;i++){
            int id=values.get(i);
            addCard(id);
        }
    }

    //aggiungo le toolcard
    public void addCard(int id) throws ToolCardException, NotValidException {
        switch (id){
            case 1:
                PinzaSgrossatrice pinzaSgrossatrice=new PinzaSgrossatrice();
                pinzaSgrossatrice.setName("Pinza Sgrossatrice");
                pinzaSgrossatrice.setDescription("Dopo aver scelto un dado, aumenta o dominuisci il valore del dado scelto di 1 (non puoi cambiare un 6 in 1 o un 1 in 6).");
                toolCards.add(pinzaSgrossatrice);
                break;
            case 2:
                PennelloPerEglomise pennelloPerEglomise= new PennelloPerEglomise();
                pennelloPerEglomise.setName("Pennello per Eglomise");
                pennelloPerEglomise.setDescription("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore. Devi rispettare tutte le altre restrizioni di piazzamento.");
                toolCards.add(pennelloPerEglomise);
                break;
            case 3:
                AlesatorePerLaminaDiRame alesatorePerLaminaDiRame= new AlesatorePerLaminaDiRame();
                alesatorePerLaminaDiRame.setName("Alesatore per lamina di rame");
                alesatorePerLaminaDiRame.setDescription("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore. Devi rispettare tutte le altre restrizioni di piazzamento.");
                toolCards.add(alesatorePerLaminaDiRame);
                break;
            case 4:
                Lathekin lathekin= new Lathekin();
                lathekin.setName("Lathekin");
                lathekin.setDescription("Muovi esattamente due dadi, rispettando tutte le restrizioni di piazzamento.");
                toolCards.add(lathekin);
                break;
            case 5:
                TaglierinaCircolare taglierinaCircolare=new TaglierinaCircolare();
                taglierinaCircolare.setName("Taglierina circolare");
                taglierinaCircolare.setDescription("Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato dei Round.");
                toolCards.add(taglierinaCircolare);
                break;
            case 6:
                PennelloPerPastaSalda pennelloPerPastaSalda= new PennelloPerPastaSalda();
                pennelloPerPastaSalda.setName("Pennello per Pasta Salda");
                pennelloPerPastaSalda.setDescription("Dopo aver scelto un dado, tira nuovamente quel dado. Se non puoi piazzarlo, riponilo nella Riserva.");
                toolCards.add(pennelloPerPastaSalda);
                break;
            case 7:
                Martelletto martelletto= new Martelletto();
                martelletto.setName("Martelletto");
                martelletto.setDescription("Tira nuovamente tutti i dadi della Riserva. Questa carta può essera usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.");
                toolCards.add(martelletto);
                break;
            case 8:
                TenagliaARotelle tenagliaARotelle= new TenagliaARotelle();
                tenagliaARotelle.setName("Tenaglia a Rotelle");
                tenagliaARotelle.setDescription("Dopo il tuo primo turno scegli immediatamente un altro dado. Salta il tuo secondo turno in questo round.");
                toolCards.add(tenagliaARotelle);
                break;
            case 9:
                RigaInSughero rigaInSughero= new RigaInSughero();
                rigaInSughero.setName("Riga in Sughero");
                rigaInSughero.setDescription("Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado. Devi rispettare tutte le restrizioni di piazzamento.");
                toolCards.add(rigaInSughero);
                break;
            case 10:
                TamponeDiamantato tamponeDiamantato= new TamponeDiamantato();
                tamponeDiamantato.setName("Tampone Diamantato");
                tamponeDiamantato.setDescription("Dopo aver scelto un dado, giralo sulla faccia opposta (6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.).");
                toolCards.add(tamponeDiamantato);
                break;
            case 11:
                DiluentePerPastaSalda diluentePerPastaSalda= new DiluentePerPastaSalda();
                diluentePerPastaSalda.setName("Diluente per Pasta Salda");
                diluentePerPastaSalda.setDescription("Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal Sacchetto. Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.");
                toolCards.add(diluentePerPastaSalda);
                break;
            case 12:
                TaglierinaManuale taglierinaManuale= new TaglierinaManuale();
                taglierinaManuale.setName("Taglierina Manuale");
                taglierinaManuale.setDescription("Muovi fino a due dadi dello stesso colore di un solo dado sul Tracciato dei Round. Devi rispettare tutte le restrizioni di piazzamento.");
                toolCards.add(taglierinaManuale);
                break;
        }

    }


    public void changePlayer () {
        playerPlaying++;
        //notifyNextPlayer(players.get(playersRound[playerPlaying]));
    }

    public void changeRound (int firstPlayer) {
        int i=0, j;
        while (i<numPlayers) {
            playersRound[i] = i + firstPlayer;
            if (playersRound[i] >= numPlayers)
                for (j = 0; i < numPlayers; i++)
                    playersRound[i]=j;
            i++;
        }
        for (i=0; i<numPlayers; i++)
            playersRound[i+numPlayers] = playersRound[numPlayers-i];
    }

    public void useDice (Box box, Dice dice, Player player) throws NotValidException {
        boolean ok = player.useDice(box, dice);
        draftPool.removeDice(dice);
        //notifyUsedDice(ok);
    }

    public ArrayList<Player> getRanking() {   // ritorna un array di giocatori ordinato dal punteggio massimo al minimo
        int scores[] = new int[numPlayers];
        ArrayList<Player> ranking = new ArrayList<Player>();
        int i, j, max, k=1;
        boolean found = false;
        for (i=0; i<numPlayers; i++) {
            scores[i] = calculateScore(players.get(i));
        }
        for(i=0; i<numPlayers; i++) {
            max = 0;
            for(j=1; j<numPlayers; j++) {
                if (scores[j] > scores[max])
                    max = j;
                else if (scores[j]==scores[max])
                    if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())>players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        max = j;
                    else if (players.get(j).getPrivateObjective().calculateScore(players.get(j).getScheme())==players.get(max).getPrivateObjective().calculateScore(players.get(max).getScheme()))
                        if (players.get(j).getNumOfToken()>players.get(max).getNumOfToken())
                            max = j;
                        else if (players.get(j).getNumOfToken()==players.get(max).getNumOfToken())
                            while (k<=numPlayers&&!found)
                                if (players.get(firstPlayer-k)==players.get(j)) {
                                    max = j;
                                    found = true;
                                }
                                else if (players.get(firstPlayer-k)==players.get(max))
                                    found = true;
                k++;
            }

            ranking.add(players.get(max));
        }

        return ranking;
    }

    public int calculateScore(Player player) {
        int score = 0;
        for (int i=0; i<3; i++)
            score = score + publicObjectives.get(i).calculateScore();
        score = score + player.getPrivateObjective().calculateScore(player.getScheme());
        score = score + player.getNumOfToken();
        score = score - player.getScheme().countFreeBoxes();
        player.setScore(score);
        return score;
    }



    public void endMatch() {
        ArrayList<Player> ranking = getRanking();
    }

}

