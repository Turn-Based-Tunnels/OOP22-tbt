package it.tbt.controller.viewcontrollermanager.api;

import it.tbt.controller.modelmanager.ModelState;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;

import java.util.List;
import java.util.Optional;

public interface ViewControllerManager {

    public Optional<List<Command>> getCommands();

    public void renderView(GameState gameState, ModelState modelState, Boolean hasChanged);

    public void cleanCommands();

}
