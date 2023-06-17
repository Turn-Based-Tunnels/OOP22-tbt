package it.tbt.engine.impl;

import java.util.ArrayList;

import it.tbt.commons.resourceloader.world.impl.WorldCreationDefault;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.menu.impl.MenuFactory;
import it.tbt.controller.modelmanager.GameStateManager;
import it.tbt.controller.modelmanager.IGameStateManager;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.controller.viewcontrollermanager.impl.GameViewManagerImpl;
import it.tbt.model.party.IParty;
import it.tbt.model.party.Party;
import it.tbt.model.party.PartyFactory;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;

public class GameImpl implements Game {

    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;

    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl(gvf);
        ArrayList<Ally> allies = new ArrayList<>();
        allies.add(new Ally("Roberto", 5, 5, 5));
        allies.add(new Ally("Gianfranco", 10, 1, 7));
        allies.add(new Ally("Caparezza", 3, 9, 2));
        allies.add(new Ally("Robertino", 2, 2, 2));
        Party p = new Party("party", 75, 75, 75, 75, allies);
        gameStateManager = new GameStateManager(new WorldCreationDefault().createWorld(),
                p, MenuFactory.getMainMenu(), MenuFactory.getPauseMenu());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(),
                true);
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
        this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(),
                this.gameStateManager.hasStateChanged());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean handleInput() {
        Boolean r = this.viewControllerManager.getCommands().isEmpty();
        if (r == false) {
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
