package it.tbt.controller.modelmanager.updatemanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.updatemanager.api.UpdateManager;
import it.tbt.model.GameState;
import it.tbt.model.time.TimeAffected;

public final class UpdateManagerImpl implements UpdateManager {

    /**
     * @param gameState
     */
    @Override
    public void updateModel(final GameState gameState, final ModelState modelState, final long timeTransition) {
        switch (gameState) {
            case EXPLORE -> {
                if (modelState instanceof ExploreState state) {
                    if (state.getParty() instanceof TimeAffected) {
                        ((TimeAffected) state.getParty()).affect(timeTransition);
                    }
                    for (var x : state.getRoom().getEntities()) {
                        if (x instanceof TimeAffected) {
                            ((TimeAffected) x).affect(timeTransition);
                        }
                    }
                }
            }
        }
    }
}
