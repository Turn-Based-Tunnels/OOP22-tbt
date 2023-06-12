package it.tbt.controller.modelmanager;

import it.tbt.model.GameState;

public interface IGameStateManager {

    ModelState getStateModel();
    GameState getState();
    Boolean hasStateChanged();
    void updateState(long timePassed);
    Boolean isOver();
}
