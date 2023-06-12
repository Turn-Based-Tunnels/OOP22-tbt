package it.tbt.controller.ModelManager.UpdateManager.impl;

import it.tbt.controller.ModelManager.ExploreState;
import it.tbt.controller.ModelManager.ModelState;
import it.tbt.controller.ModelManager.UpdateManager.api.UpdateManager;
import it.tbt.model.GameState;
import it.tbt.model.time.TimeAffected;

public class UpdateManagerImpl implements UpdateManager {

    /**
     * @param gameState
     */
    @Override
    public void updateModel(GameState gameState, ModelState modelState, long timeTransition) {
        switch (gameState) {
            case EXPLORE -> {
                var state = (ExploreState) modelState;
                if(state.getParty() instanceof TimeAffected) {
                    ((TimeAffected)state.getParty()).affect(timeTransition);
                }
                for(var x: state.getRoom().getEntities()) {
                    if(x instanceof TimeAffected) {
                        ((TimeAffected)x).affect(timeTransition);
                    }
                }
            }
        }
    }
}
