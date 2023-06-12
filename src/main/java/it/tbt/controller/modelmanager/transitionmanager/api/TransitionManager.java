package it.tbt.controller.modelmanager.transitionmanager.api;

import it.tbt.controller.modelmanager.ModelState;
import it.tbt.model.GameState;
import it.tbt.model.statechange.StateObserver;

public interface TransitionManager extends StateObserver {
    public GameState getState();
    public ModelState getCurrentModelState();

    public Boolean hasStateChanged();
}
