package it.tbt.engine.impl;

import it.tbt.controller.ModelManager.GameStateManager;
import it.tbt.controller.ModelManager.IGameStateManager;
import it.tbt.controller.ViewControllerManager.api.ViewControllerManager;
import it.tbt.controller.ViewControllerManager.impl.GameViewManagerImpl2;
import it.tbt.model.GameState;
import it.tbt.model.World.impl.FileWorldCreationStrategy;
import it.tbt.model.party.IParty;
import it.tbt.model.party.Party;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;


public class GameImpl implements Game {

    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;
    private GameState gameState;

    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl2(gvf);
        IParty t = new Party("Party", 0,0);
        gameStateManager = new GameStateManager(t, new FileWorldCreationStrategy("data.json").createWorld());
        t.setCurrentRoom(this.gameStateManager.getWorld().getListRoom().stream().findFirst().get());
        gameState = GameState.EXPLORE;
    }

    /**
     *
     */
    @Override
    public void initialize() {
       this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), true);
    }

    /**
     *
     */
    @Override
    public void loadResources() {
        //TO LOAD RESOURCES
    }

    /**
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        this.gameStateManager.updateState(deltaTime);

    }

    /**
     *
     */
    @Override
    public void render() {
        this.viewControllerManager.renderView(this.gameStateManager.getState(), this.gameStateManager.getStateModel(), this.gameStateManager.hasStateChanged());
    }

    /**
     * @param time
     */
    @Override
    public void render(float time) {
        //RENDER WITH TIME LAG TO REPRESENT
    }

    /**
     * @return
     */
    @Override
    public Boolean handleInput() {
        Boolean r = this.viewControllerManager.getCommands().isEmpty();
        this.viewControllerManager.getCommands().stream().forEach(l->l.execute());
        this.viewControllerManager.cleanCommands();
        return !r;
    }

    /**
     * @return
     */
    @Override
    public Boolean isOver() {
        return this.gameStateManager.isOver();
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        //CLEAN UP ON THE CLOSURE
    }
}
