package it.tbt.engine.impl;

import it.tbt.Controller.ModelManager.GameStateManager;
import it.tbt.Controller.ModelManager.IGameStateManager;
import it.tbt.Controller.ViewControllerManager.api.ViewControllerManager;
import it.tbt.Controller.ViewControllerManager.impl.GameViewManagerImpl2;
import it.tbt.Model.Party.IParty;
import it.tbt.Model.Party.Party;
import it.tbt.Model.GameState;
import it.tbt.Model.World.impl.FileWorldCreationStrategy;
import it.tbt.View.api.GameViewFactory;
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
