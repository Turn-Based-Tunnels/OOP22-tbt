package it.tbt.Controller.ModelManager;

import it.tbt.Model.GameState;
import it.tbt.Model.Party.IParty;
import it.tbt.Model.World.api.World;

public interface IGameStateManager {

    ModelState getStateModel();
    GameState getState();
    Boolean hasStateChanged();
    void updateState(float timePassed);
    IParty getParty();
    void setParty(IParty party);
    World getWorld();
    void setWorld(World world);
}
