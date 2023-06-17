package it.tbt.controller.modelmanager;

import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.controller.modelmanager.transitionmanager.impl.TransitionManagerImpl;
import it.tbt.controller.modelmanager.updatemanager.api.UpdateManager;
import it.tbt.controller.modelmanager.updatemanager.impl.UpdateManagerImpl;
import it.tbt.model.GameState;
import it.tbt.model.menu.impl.MenuModel;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.World;

public class GameStateManager implements IGameStateManager {

    private TransitionManager transitionManager;
    private UpdateManager updateManager;

    public GameStateManager(final TransitionManager transitionManager, final UpdateManager updateManager) {
        this.transitionManager = transitionManager;
        this.updateManager = updateManager;
        transitionManager.onMenu();
    }

    public GameStateManager(final World world, final IParty party, final MenuModel mainMenu,
            final MenuModel pauseMenu) {
        this.transitionManager = new TransitionManagerImpl(world, party, mainMenu, pauseMenu);
        this.updateManager = new UpdateManagerImpl();
        this.transitionManager.init();
        party.setCurrentRoom(world.getStartRoom());
        transitionManager.onMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getStateModel() {
        return this.transitionManager.getCurrentModelState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.transitionManager.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasStateChanged() {
        return this.transitionManager.hasStateChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(long timePassed) {
        this.updateManager.updateModel(this.getState(), this.getStateModel(), timePassed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOver() {
        return false;
    }

}
