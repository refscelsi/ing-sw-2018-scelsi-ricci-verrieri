package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.awt.*;
import java.io.Serializable;


public abstract class ToolCard implements Serializable{

    private int numOfTokens;
    private int id;
    private String name;
    private String description;

    public ToolCard (int id) {
        this.id=id;
        numOfTokens = 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNumOfTokens () {
        return numOfTokens;
    }

    public void incrementNumOfTokens () {
        numOfTokens = 2;
    }

    public int getId(){
        return id;
    }

    public boolean getFirstExecutionDone(){
        return false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void execute1(DraftPool draftPool, int indexInDraftPool, String operation) throws NotValidException{
        System.out.println("problemi toolcard");
    }

    public void execute2(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        System.out.println("problema toolcard");
    }
    public void execute3(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        System.out.println("problema toolcard");
    }

    public void execute4(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        System.out.println("problema toolcard");
    }

    public void execute11b(int numFacciaUp, Scheme scheme, int row, int col) throws NotValidException{
        System.out.println("problema toolcard");
    }

    public void execute11a(DraftPool draftPool, int indexInDraftPool, Bag bag) {
        System.out.println("problema toolcard");
    }

    public void execute7(DraftPool draftPool){
        System.out.println("problema toolcard");
    }

    public void execute6(DraftPool draftPool, int indexInDraftPool){
        System.out.println("problemi toolcard");
    }

    public void execute9(Scheme scheme, Dice dice, int row, int col) throws NotValidException{
        System.out.println("problemi toolcard");
    }

    public void execute5(DraftPool draftpool, int indexInDraftPool, RoundTrack roundTrack, int round, int indexInRound) throws NotValidPlayException {
        System.out.println("problemi toolcard");
    }

    public void execute(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol, RoundTrack roundTrack) throws NotValidException{
        System.out.println("problemi toolcard");
    }

    public void execute10(Dice dice){
        System.out.println("problemi toolcard");
    }

    public void execute8(Player[] playersRound, int playersRoundIndex){
        System.out.println("problemi toolcard");
    }





}
