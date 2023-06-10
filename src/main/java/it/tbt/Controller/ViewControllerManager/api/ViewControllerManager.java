package it.tbt.Controller.ViewControllerManager.api;

import it.tbt.Controller.ModelManager.ModelState;
import it.tbt.Model.Command.api.Command;
import it.tbt.Model.GameState;
import it.tbt.View.api.GameView;

import java.util.List;
import java.util.Queue;

public interface ViewControllerManager {

    public List<Command> getCommands();

    public void renderView(GameState gameState, ModelState modelState, Boolean hasChanged);

    public void cleanCommands();

}
