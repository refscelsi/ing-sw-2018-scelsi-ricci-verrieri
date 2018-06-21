package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.toolCard.*;
import it.polimi.ing.sw.model.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;



public class ToolCards {

    private ArrayList<ToolCard> toolCards;


    public ToolCards() throws ToolCardException, NotValidException{
        toolCards= new ArrayList<ToolCard>();
        ArrayList<Integer> values= new ArrayList<Integer>();
        for(int i=1; i<13;i++){
            values.add(i);
        }
        Collections.shuffle(values);
        for(int i=0;i<3;i++){
            int id=values.get(i);
            addCard(id);
        }
    }


    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }


    // metodo che serve solo per il test

    public int getSize() {
        return toolCards.size();
    }


    public void addCard(int id) throws ToolCardException, NotValidException {
        ToolCard toolCard;
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


}
