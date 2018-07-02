package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Scheme;

import java.io.Serializable;

public class PrivateObjectiveCard implements Serializable {

    private Color color;
    private String name;
    private String description;
    private int id;

    public PrivateObjectiveCard(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int calculateScore(Scheme scheme) {
        int score = 0;
        Box boxes[][] = scheme.getBoxes();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++)
                if (boxes[i][j].getColor() == color)
                    score = score + boxes[i][j].getShade();
        return score;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
