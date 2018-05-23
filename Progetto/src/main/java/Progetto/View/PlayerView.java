package Progetto.View;

import Progetto.Controller.Controller;
import Progetto.Controller.Observer;
import Progetto.Model.Dice;
import Progetto.Model.DraftPool;
import Progetto.Model.ObjectiveCard.ObjectiveCard;
import Progetto.Model.Player;
import Progetto.Model.SchemeCard;

import java.util.ArrayList;

public class PlayerView implements Observer {
    private final Controller controller;

    public PlayerView(Controller controller) {
        this.controller = controller;
    }

    //l'unica che parla col controller??
    @Override
    public void update() {

    }

    @Override
    public void update(ArrayList<SchemeCard> scheme, Player player) {

    }

    @Override
    public void update(Player firstPlayer, DraftPool draftPool) {

    }

    @Override
    public void update(ArrayList<Player> ranking) {

    }

    @Override
    public void update(Player player) {

    }

    @Override
    public void update1(ArrayList<ObjectiveCard> publicObjectives) {

    }

    @Override
    public void update(Dice dice) {

    }

    @Override
    public void notifyMessage(String message) {
        System.out.println(message);
    }
}
