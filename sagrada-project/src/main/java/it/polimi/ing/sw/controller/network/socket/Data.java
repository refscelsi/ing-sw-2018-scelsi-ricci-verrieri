package it.polimi.ing.sw.controller.network.socket;

import java.util.ArrayList;

public class Data {
    String method;
    ArrayList<String> params;

    public Data(String method, ArrayList<String> params) {
        this.method = method;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public ArrayList<String> getParams() {
        return params;
    }
}

