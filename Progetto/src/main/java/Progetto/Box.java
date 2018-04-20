package Progetto;

public class Box {
    private int x;
    private int y;
    private Dice dice;
    private Color color;
    private int shade;

    public Box(){
        this.color=Color.WHITE;
    }

    public boolean isEmpty(){
        if(this.dice!=null){
            return false;
        }
        else
            return true;
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

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
