package Progetto.Model.ToolCard;

import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public interface ToolCard {
    void execute() throws ToolCardException, NotValidException;

}
