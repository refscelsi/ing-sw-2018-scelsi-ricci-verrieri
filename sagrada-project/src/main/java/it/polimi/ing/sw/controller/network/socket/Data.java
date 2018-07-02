package it.polimi.ing.sw.controller.network.socket;

import java.util.ArrayList;

public class Data {
    String method;
    String nickname;
    int indexDice;
    int row;
    int col;
    int destRow;
    int destCol;
    int id;
    int dice;
    int operation;

    public Data (String method, String nickname){
        this.method = method;
        this.nickname=nickname;
    }

    public Data(String method, int indexDice, int row, int col) {
        this.method = method;
        this.indexDice=indexDice;
        this.row=row;
        this.col=col;
    }

    public Data (String method){
        this.method=method;
    }

    public Data (String method, int id){
        this.method=method;
        this.id=id;
    }

    public String getMethod() {
        return method;
    }

    //public ArrayList<String> getParams() {
    //return params;
    // }

    public Data(String method, int id, int dice, int operation, int indexDice, int row, int col, int destRow, int destCol) {
        this.method = method;
        this.id=id;
        this.dice=dice;
        this.operation=operation;
        this.row=row;
        this.col=col;
        this.destRow=destRow;
        this.destCol=destCol;
    }


}

