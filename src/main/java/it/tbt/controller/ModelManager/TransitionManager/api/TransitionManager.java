package it.tbt.controller.ModelManager.TransitionManager.api;

import it.tbt.controller.ModelManager.ModelState;
import it.tbt.model.GameState;
import it.tbt.model.statechange.StateObserver;

public interface TransitionManager extends StateObserver {
    public GameState getState();
    public ModelState getCurrentModelState();

    public Boolean hasStateChanged();
}
