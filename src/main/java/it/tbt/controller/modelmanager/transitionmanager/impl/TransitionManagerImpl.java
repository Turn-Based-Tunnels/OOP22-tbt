package it.tbt.controller.modelmanager.transitionmanager.impl;

import it.tbt.controller.modelmanager.ExploreStateImpl;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.model.GameState;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.World;

public class TransitionManagerImpl implements TransitionManager {

    private GameState currentGameState;
    private World world;
    private IParty party;
    private ModelState currentModelState;
    private Boolean stateChanged = false;

    public TransitionManagerImpl(World world, IParty party) {
        this.world = world;
        this.party = party;
        /*
        TO-REMOVE
        this.currentGameState = GameState.EXPLORE;
        this.currentModelState = new ExploreStateImpl(this.party.getCurrentRoom(), this.party);
        */
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
