package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static it.polimi.ing.sw.model.Color.*;
import static java.lang.System.out;

public class SchemeListFileConverter {

    private static final String FILE_PATH_LOCATION = "SchemeList.json";

    public ArrayList<Scheme> readFromFile() {
        ArrayList<Scheme> schemeArrayList = new ArrayList<>( );

        Scheme schema;
        Box boxes[][];
        Box box = new Box( );
        Dice dice = new Dice( );

        JSONObject jScheme;
        JSONObject jBox;
        JSONObject jBoxes;
        JSONObject jSchemeList;

        JSONParser parser;

        try {
            parser = new JSONParser( );

            Object obj = parser.parse(new FileReader(FILE_PATH_LOCATION));

            JSONObject jFile = (JSONObject) obj;

            int counter = Integer.valueOf((String) jFile.get("counter"));

            for (int k = 0; k < counter; k++) {

                jScheme = (JSONObject) jFile.get(String.valueOf(k));

                jBoxes = (JSONObject) jScheme.get("Boxes");

                boxes = new Box[4][5];

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        box = new Box( );
                        //create name
                        String name = String.valueOf(i).concat(String.valueOf(j));

                        jBox = (JSONObject) jBoxes.get(name);

                        box.setX(Integer.valueOf((String) jBox.get("X")));

                        box.setY(Integer.valueOf((String) jBox.get("Y")));

                        Color color = setColor((String) jBox.get("Color"));
                        box.setColor(color);

                        box.setShade(Integer.valueOf((String) jBox.get("Shade")));

                        boxes[i][j] = box;
                    }
                }

                int id = Integer.valueOf((String) (jScheme.get("ID")));
                int diff = Integer.valueOf((String) (jScheme.get("Difficulty")));
                schema = new Scheme(id, diff, boxes);

                schemeArrayList.add(schema);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace( );
        } catch (IOException e) {
            e.printStackTrace( );
        } catch (ParseException e) {
            e.printStackTrace( );
        }

        return schemeArrayList;
    }

    private Color setColor(String color) {
        if (color.equals("WHITE"))
            return WHITE;
        if (color.equals("RED"))
            return RED;
        if (color.equals("BLUE"))
            return BLUE;
        if (color.equals("YELLOW"))
            return YELLOW;
        if (color.equals("PURPLE"))
            return PURPLE;
        if (color.equals("GREEN"))
            return GREEN;
        return WHITE;

    }

    public void writeToFile(ArrayList<Scheme> schemeArrayList) {

        JSONObject jScheme;
        JSONObject jBox;
        JSONObject jBoxes = new JSONObject( );
        JSONObject jSchemeList = new JSONObject( );


        Box boxes[][];
        Box box;

        int counter = 0;

        for (Scheme schemeObject : schemeArrayList) {


            boxes = schemeObject.getBoxes( );
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {

                    jBox = new JSONObject( );
                    box = boxes[i][j];

                    jBox.put("X", String.valueOf(box.getX( )));

                    jBox.put("Y", String.valueOf(box.getY( )));

                    jBox.put("Color", box.getColor( ).toString( ));

                    jBox.put("Shade", String.valueOf(box.getShade( )));

                    //create name
                    String name = String.valueOf(i).concat(String.valueOf(j));

                    jBoxes.put(name, jBox);
                }
            }
            jScheme = new JSONObject( );

            jScheme.put("ID", String.valueOf(schemeObject.getId( )));
            jScheme.put("Difficulty", String.valueOf(schemeObject.getDifficulty( )));
            jScheme.put("Boxes", jBoxes);

            //jScheme.put( "scheme", jScheme );

            jSchemeList.put(counter, jScheme);

            counter++;
        }

        jSchemeList.put("counter", java.lang.String.valueOf(counter));

        try (FileWriter file = new FileWriter(FILE_PATH_LOCATION)) {
            out.println("arriva qui");
            file.write(jSchemeList.toJSONString( ));
            file.flush( );
        } catch (IOException e) {
            e.printStackTrace( );
            out.println("NOOOOON VAAA nel file");
        }

        out.print(jSchemeList);
    }
}
