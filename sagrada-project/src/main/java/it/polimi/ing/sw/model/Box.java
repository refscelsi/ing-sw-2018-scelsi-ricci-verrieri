package it.polimi.ing.sw.model;

public class Box {
    private int x;
    private int y;
    private Dice dice;
    private Color color;
    private int shade;

    public Box(){
        color=Color.WHITE;
        shade=0;
        dice=null;
    }

    public Box(int x, int y, Color color, int shade){
        this.x=x;
        this.y=y;
        this.color=color;
        this.shade=shade;
        dice=null;
    }

    public boolean isFull(){
        if(this.dice==null){
            return false;
        }
        else
            return true;
    }

    public void removeDice(){
        this.dice=null;
    }

    public void placeDice(Dice dice) {
        this.dice=dice;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getShade() {
        return shade;
    }

    public Color getColor() {
        return color;
    }

    public Dice getDice() {
        return dice;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShade(int shade) {
        this.shade = shade;
    }

    public void setColor(Color color){
        this.color=color;
    }

}
