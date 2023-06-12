package it.tbt.controller.ModelManager.TransitionManager.impl;

import it.tbt.controller.ModelManager.ExploreStateImpl;
import it.tbt.controller.ModelManager.ModelState;
import it.tbt.controller.ModelManager.TransitionManager.api.TransitionManager;
import it.tbt.engine.api.Game;
import it.tbt.model.GameState;
import it.tbt.model.party.IParty;
import it.tbt.model.World.api.World;

public class TransitionManagerImpl implements TransitionManager {

    private GameState currentGameState;
    private World world;
    private IParty party;
    private ModelState currentModelState;
    private Boolean stateChanged = false;

    public TransitionManagerImpl(World world, IParty party) {
        this.world = world;
        this.party = party;
        this.currentGameState = GameState.EXPLORE;
        this.currentModelState = new ExploreStateImpl(this.party.getCurrentRoom(), this.party);
    }

    /**
     * @return
     */
    @Override
    public GameState getState() {
        return this.currentGameState;
    }

    /**
     * @return
     */
    @Override
    public ModelState getCurrentModelState() {
        return this.currentModelState;
    }

    /**
     * @return
     */
    @Override
    public Boolean hasStateChanged() {
        var x = false;
        if(this.stateChanged==true) {
            x = true;
            this.stateChanged = false;
        }
        return x;

    }

    /**
     *
     */
    @Override
    public void onExplore() {
        stateChanged = true;
        this.currentGameState = GameState.EXPLORE;
        this.currentModelState = new ExploreStateImpl(this.party.getCurrentRoom(), this.party);
    }

    /**
     *
     */
    @Override
    public void onFight() {

    }
}
