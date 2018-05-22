package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ToolCard.*;

import javax.swing.text.html.HTML;

public class ChosenCard implements State {
    Controller controller;

    public ChosenCard(Controller newStateController){
        controller=newStateController;
    }

    public void handleCard() throws ToolCardException, NotValidException {

    int id=controller.getCard().getId();

        switch (id){
            case 1:
                PinzaSgrossatrice pinzaSgrossatrice= new PinzaSgrossatrice();
                if(controller.getMatch().getToolCardTokens()[1]==1){
                    controller.getMatch().useToolCard(pinzaSgrossatrice);
                    controller.getMatch().getToolCardTokens()[1]=2;
                }
                break;
            case 2:
                PennelloPerEglomise pennelloPerEglomise= new PennelloPerEglomise();
                pennelloPerEglomise.execute(controller.getDice(),controller.getPlayer(),controller.getBox(),controller.getBox());
                break;
            case 3:
                AlesatorePerLaminaDiRame alesatorePerLaminaDiRame=new AlesatorePerLaminaDiRame();
                break;
            case 4:
                Lathekin lathekin= new Lathekin();
                break;
            case 5:
                TaglierinaCircolare taglierinaCircolare=new TaglierinaCircolare();
                break;
            case 6:
                PennelloPerPastaSalda pennelloPerPastaSalda= new PennelloPerPastaSalda();
                break;
            case 7:
                Martelletto martelletto= new Martelletto();
                break;
            case 8:
                TenagliaARotelle tenagliaARotelle= new TenagliaARotelle();
                break;
            case 9:
                RigaInSughero rigaInSughero= new RigaInSughero();
                break;
            case 10:
                TamponeDiamantato tamponeDiamantato= new TamponeDiamantato();
                break;
            case 11:
                DiluentePerPastaSalda diluentePerPastaSalda= new DiluentePerPastaSalda();
                break;
            case 12:
                TaglierinaManuale taglierinaManuale= new TaglierinaManuale();
                break;
        }
    }

    @Override
    public void chooseDice(Dice dice) {

    }


    @Override
    public void chooseBox(Box box) {

    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        controller.getMatch().notifyObserver("hai già scelto una carta");
    }

    @Override
    public void endTurn() {
        //controllare che abbia veramente finito
        controller.getMatch().changePlayer();
        controller.setPlayer(controller.getMatch().getPlayerPlaying());
        controller.setState(controller.getStartedTurn());
    }
}
