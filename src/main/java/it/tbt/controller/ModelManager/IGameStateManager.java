package it.tbt.controller.ModelManager;

import it.tbt.model.GameState;
import it.tbt.model.Party.IParty;
import it.tbt.model.World.api.World;

public interface IGameStateManager {

    ModelState getStateModel();
    GameState getState();
    Boolean hasStateChanged();
    void updateState(float timePassed);
    IParty getParty();
    void setParty(IParty party);
    World getWorld();
    void setWorld(World world);
    Boolean isOver();
}
