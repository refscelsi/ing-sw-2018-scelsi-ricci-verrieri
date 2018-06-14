package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SchemeListFileConverter {

    private static final String FILE_PATH_LOCATION = "SchemeList.json";

    public ArrayList<Scheme> readFromFile (){

        JSONParser parser = new JSONParser();

        ArrayList<Scheme> schemeArrayList = new ArrayList<>();

        Scheme schema;
        Box boxes[][]=new Box[4][5];
        Box box= new Box();
        Dice dice = new Dice();

        try {

            Object obj = parser.parse(new FileReader(FILE_PATH_LOCATION));

            JSONObject jsonObject = (JSONObject) obj;

            String counter = (String) jsonObject.get("counter");

            for (int k = 0; k <= Integer.valueOf(counter);k++){
                for (int i = 0; i<4;i++){
                    for (int j =0; j<5;j++){
                        //create name
                        String name= String.valueOf(i).concat(String.valueOf(j));
                        //JSONArray msg = (JSONArray) jsonObject.get(name);


                        /*
                        Iterator<String> iterator = msg.iterator();
                        while (iterator.hasNext()) {
                        //System.out.println(iterator.next());

                        //box=boxes[i][j];
                        //list.add(box.getX());
                        box.setY();
                        list.add(box.getY());
                        list.add(box.getColor());
                        list.add(box.getShade());

                        //not required
                        dice=box.getDice();
                        list.add(dice.getNumFacciaUp());
                        list.add(dice.getDiceColor());

                        //create name
                        String name= String.valueOf(i).concat(String.valueOf(j));
                        obj.put(name, list);*/
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return schemeArrayList;
    }

    public void writeToFile (ArrayList<Scheme> schemeArrayList){

        JSONObject obj = new JSONObject();
        JSONObject jScheme = new JSONObject();
        JSONObject jDice = new JSONObject();
        JSONObject jBox = new JSONObject();
        JSONObject jBoxes = new JSONObject();
        JSONObject jSchemeList = new JSONObject();

        JSONArray boxesJList = new JSONArray();
        JSONArray schemeJList = new JSONArray();

        Box boxes[][]=new Box[4][5];
        Box box= new Box();
        Dice dice = new Dice();

        int counter=0;

        int lenght= schemeArrayList.size();
        for (int k=0; k<lenght;k++) {
            boxes=schemeArrayList.get(k).getBoxes();
            for (int i = 0; i<4;i++){
                for (int j =0; j<5;j++){

                    jBox = new JSONObject();
                    box=boxes[i][j];

                    //innerList.add(box.getX());
                    jBox.put("X",box.getX());

                    //innerList.add(box.getY());
                    jBox.put("Y",box.getY());

                    //innerList.add(box.getColor());
                    jBox.put("Color",box.getColor());

                    //innerList.add(box.getShade());
                    jBox.put("Shade",box.getShade());

                    //not required
                    dice=box.getDice();
                    jDice = new JSONObject();

                    //innerList.add(dice.getNumFacciaUp());
                    jDice.put("NumFacciaUp",dice.getNumFacciaUp());

                    //innerList.add(dice.getDiceColor());
                    jDice.put("DiceColor",dice.getDiceColor());

                    jBox.put("Dice",jDice);

                    //create name
                    String name= String.valueOf(i).concat(String.valueOf(j));

                    jBoxes.put(name,jBox);
                }
            }
            jScheme = new JSONObject();

            jScheme.put("ID", schemeArrayList.get(k).getId());
            jScheme.put("Difficulty", schemeArrayList.get(k).getDifficulty());
            jScheme.put("Boxes",jBoxes);

            jSchemeList.put(counter,jScheme);

            counter++;
        }
        try (FileWriter file = new FileWriter(FILE_PATH_LOCATION)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("NOOOOON VAAA nel file");
        }

        System.out.print(obj);
    }
}
