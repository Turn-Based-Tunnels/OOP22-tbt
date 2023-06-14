package it.tbt.controller.modelmanager.updatemanager.api;

import it.tbt.controller.modelmanager.ModelState;
import it.tbt.model.GameState;

public interface UpdateManager {
    public void updateModel(GameState gameState, ModelState modelState, long timeTransition);
}
