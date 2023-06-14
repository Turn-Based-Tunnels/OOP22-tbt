package it.tbt.engine.impl;

import it.tbt.commons.resourceloader.world.impl.WorldCreationDefault;
import it.tbt.control.menu.impl.MenuFactory;
import it.tbt.controller.modelmanager.GameStateManager;
import it.tbt.controller.modelmanager.IGameStateManager;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.controller.viewcontrollermanager.impl.GameViewManagerImpl;
import it.tbt.model.GameState;
import it.tbt.model.party.PartyFactory;
import it.tbt.model.party.IParty;
import it.tbt.model.party.Party;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;


public class GameImpl implements Game {

    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;

    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl(gvf);
        gameStateManager = new GameStateManager(new WorldCreationDefault().createWorld(), PartyFactory.createDefaultParty(), MenuFactory.getMainMenu());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
       this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long deltaTime) {
        this.gameStateManager.updateState(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), this.gameStateManager.hasStateChanged());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean handleInput() {
        Boolean r = this.viewControllerManager.getCommands().isEmpty();
        if(r == false) {
            this.viewControllerManager.getCommands().get().stream().forEach(l -> l.execute());
            this.viewControllerManager.cleanCommands();
        }
        return !r;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOver() {
        return this.gameStateManager.isOver();
    }

}
