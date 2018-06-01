package Progetto.Model;

public class SchemeCard {
   private short id1,id2;
   private Scheme front, back;


   public SchemeCard(short id1,short id2){
       this.id1=id1;
       this.id2=id2;

       //da aggiungere che carica gli schemi
   }

    public Scheme getA() {
        return front;
    }

    public Scheme getBack() {
        return back;
    }

    public void setA(Scheme front) {
        this.front = front; }

    public void setBack(Scheme back) {
        this.back = back;
    }

    public short getId1() {
        return id1;
    }

    public short getId2() {
        return id2;
    }

    public void setId1(short id1) {
        this.id1 = id1;
    }

    public void setId2(short id2) {
        this.id2 = id2;
    }
}


