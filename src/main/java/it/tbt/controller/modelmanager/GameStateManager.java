package it.tbt.controller.modelmanager;

import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.controller.modelmanager.transitionmanager.impl.TransitionManagerImpl;
import it.tbt.controller.modelmanager.updatemanager.api.UpdateManager;
import it.tbt.controller.modelmanager.updatemanager.impl.UpdateManagerImpl;
import it.tbt.model.GameState;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.World;

public class GameStateManager implements IGameStateManager{

    private TransitionManager transitionManager;
    private UpdateManager updateManager;

    public GameStateManager(final IParty party, final World world) {
        this.transitionManager = new TransitionManagerImpl(world, party);
        this.updateManager = new UpdateManagerImpl();
    }

    public GameStateManager(final TransitionManager transitionManager, final UpdateManager updateManager) {
        this.transitionManager = transitionManager;
        this.updateManager = updateManager;
    }

    /**
     * @return
     */
    @Override
    public ModelState getStateModel() {
        return this.transitionManager.getCurrentModelState();
    }

    /**
     * @return
     */
    @Override
    public GameState getState() {
        return this.transitionManager.getState();
    }

    /**
     * @return
     */
    @Override
    public Boolean hasStateChanged() {
        return this.transitionManager.hasStateChanged();
    }

    /**
     *
     */
    @Override
    public void updateState(long timePassed) {
        this.updateManager.updateModel(this.getState(), this.getStateModel(), timePassed);
    }

    /**
     * @return
     */
    @Override
    public Boolean isOver() {
        return false;
    }

}
