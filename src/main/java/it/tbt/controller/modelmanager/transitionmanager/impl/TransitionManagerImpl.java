package it.tbt.controller.modelmanager.transitionmanager.impl;

import it.tbt.controller.modelmanager.ExploreStateImpl;
import it.tbt.controller.modelmanager.MenuStateImpl;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.model.GameState;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.menu.impl.MenuModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.api.World;
import java.util.*;

/**
 * Default implementation of a TransitionManager
 */

public final class TransitionManagerImpl implements TransitionManager {

    private Optional<GameState> currentGameState;
    private World world;
    private IParty party;
    private MenuModel mainMenu;
    private MenuModel pauseMenu;
    private Optional<ModelState> currentModelState;
    private Boolean stateChanged = false;

    public TransitionManagerImpl(final World world, final IParty party, final MenuModel mainMenu, final MenuModel pauseMenu) {
        this.world = world;
        this.party = party;
        this.mainMenu = mainMenu;
        this.pauseMenu = pauseMenu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        if(world == null || party == null) {
            throw new IllegalStateException("Null objects have been passed to the transition manager.");
        } else {
            this.startObserving();
        }
    }

    /**
     * Subscribes to model objects who can trigger a GameState change.
     */
    private void startObserving() {
        if(this.party instanceof StateTrigger) {
            ((StateTrigger)this.party).setStateObserver(this);
        }
        for(var x: this.world.getListRoom()) {
            for(var y: x.getEntities()) {
                if(y instanceof StateTrigger) {
                    ((StateTrigger)y).setStateObserver(this);
                    if(y instanceof FightNPC){
                        if(((FightNPC)y).getFightModel() instanceof StateTrigger){
                            ((StateTrigger)((FightNPC)y).getFightModel()).setStateObserver(this);
                        }

                    }
                }
            }
        }
        if(this.pauseMenu instanceof StateTrigger){
            ((StateTrigger)pauseMenu).setStateObserver(this);
        }
        for(var x: this.mainMenu.getItems()){
            if(x instanceof StateTrigger){
                ((StateTrigger)x).setStateObserver(this);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        if(this.currentGameState.isEmpty()) {
            throw new IllegalStateException("Game Transition Manager not initialized properly. GameState not present.");
        }
        return this.currentGameState.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getCurrentModelState() {
        if(this.currentModelState.isEmpty()) {
            throw new IllegalStateException("Game Transition Manager not initialized properly. ModelState not present.");
        }
        return this.currentModelState.get();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void onExplore() {
        stateChanged = true;
        this.currentGameState = Optional.of(GameState.EXPLORE);
        this.currentModelState = Optional.of(new ExploreStateImpl(this.party.getCurrentRoom(), this.party));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFight() {
        //TO-DO
    }

    @Override
    public void onMenu() {
        stateChanged = true;
        this.currentGameState = Optional.of(GameState.MENU);
        this.currentModelState = Optional.of(new MenuStateImpl(mainMenu));
    }

    @Override
    public void onPause(){
        stateChanged= true;
        this.currentGameState = Optional.of(GameState.PAUSE);
        this.currentModelState = Optional.of(new MenuStateImpl(pauseMenu));
    }
}
