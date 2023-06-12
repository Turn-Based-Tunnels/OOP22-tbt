package it.tbt.controller.ModelManager.UpdateManager.api;

import it.tbt.controller.ModelManager.ModelState;
import it.tbt.model.GameState;

public interface UpdateManager {
    public void updateModel(GameState gameState, ModelState modelState, long timeTransition);
}
