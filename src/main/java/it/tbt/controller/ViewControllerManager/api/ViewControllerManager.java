package it.tbt.controller.ViewControllerManager.api;

import it.tbt.controller.ModelManager.ModelState;
import it.tbt.model.Command.api.Command;
import it.tbt.model.GameState;

import java.util.List;

public interface ViewControllerManager {

    public List<Command> getCommands();

    public void renderView(GameState gameState, ModelState modelState, Boolean hasChanged);

    public void cleanCommands();

}
