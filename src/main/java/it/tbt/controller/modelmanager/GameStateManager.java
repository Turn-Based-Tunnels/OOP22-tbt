package it.tbt.controller.modelmanager;

import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.controller.modelmanager.transitionmanager.impl.TransitionManagerImpl;
import it.tbt.controller.modelmanager.updatemanager.api.UpdateManager;
import it.tbt.controller.modelmanager.updatemanager.impl.UpdateManagerImpl;
import it.tbt.model.GameState;
import it.tbt.model.menu.impl.MenuModel;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.World;

/**
 * Default implementation of the GameStateManager class for the management of the logic aspect of the application
 */
public final class GameStateManager implements IGameStateManager {

    private TransitionManager transitionManager;
    private UpdateManager updateManager;

    /**
     * @param world world object used for the game
     * @param party party object for the player
     * @param mainMenu mainMenu object for the mainMenu state
     * @param pauseMenu pauseMenu object for the pause state
     */
    public GameStateManager(final World world, final IParty party, final MenuModel mainMenu, final  MenuModel pauseMenu) {
        this.transitionManager = new TransitionManagerImpl(world, party, mainMenu, pauseMenu);
        this.updateManager = new UpdateManagerImpl();
        this.transitionManager.init();
        party.setCurrentRoom(world.getStartRoom().get());
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
    public void updateState(final long timePassed) {
        this.updateManager.updateModel(this.getState(), this.getStateModel(), timePassed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOver() {
        //return this.player.getMembers().stream().filter(l->l.getHealth()<=0).count()==this.player.getMembers().size();
        return false;
    }

}
