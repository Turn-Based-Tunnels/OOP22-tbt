package it.tbt.controller.ModelManager;

import it.tbt.model.GameState;
import it.tbt.model.Party.IParty;
import it.tbt.model.World.api.World;

public interface IGameStateManager {

    ModelState getStateModel();
    GameState getState();
    Boolean hasStateChanged();
    void updateState(long timePassed);
    Boolean isOver();
}
