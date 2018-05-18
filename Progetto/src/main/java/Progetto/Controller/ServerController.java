package Progetto.Controller;

import Progetto.Model.*;
import Progetto.View.*;

import javax.swing.text.View;

public class ServerController {

    private Match match;
    private View view;       // ci va?

    public ServerController(Match match, View view){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
    }




