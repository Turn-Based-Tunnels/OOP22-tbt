package it.tbt.engine.impl;

import it.tbt.Commons.resourceloader.world.impl.FileWorldCreationStrategy3;
import it.tbt.controller.modelmanager.GameStateManager;
import it.tbt.controller.modelmanager.IGameStateManager;
import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.controller.modelmanager.transitionmanager.impl.TransitionManagerImpl;
import it.tbt.controller.modelmanager.updatemanager.api.UpdateManager;
import it.tbt.controller.modelmanager.updatemanager.impl.UpdateManagerImpl;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.controller.viewcontrollermanager.impl.GameViewManagerImpl2;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.impl.RoomImpl;
import it.tbt.model.party.IParty;
import it.tbt.model.party.Party;
import it.tbt.model.world.api.World;
import it.tbt.model.roomLink.RoomLink;
import it.tbt.model.roomLink.RoomLinkImpl;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;


public class GameImpl implements Game {

    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;

    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl2(gvf);
        IParty t = new Party("Party", 0,0, 50, 50);
        World w = new FileWorldCreationStrategy3().createWorld();
        Room startRoom = new RoomImpl("RoomStart");
        Room endRoom = new RoomImpl("endRoom");
        RoomLink roomLink1 = new RoomLinkImpl("link", 100, 100, 75, 75, startRoom, endRoom);
        startRoom.addEntity(roomLink1);
        RoomLink roomLink2 = new RoomLinkImpl("link2", 150, 150, 50, 50, startRoom, endRoom);
        endRoom.addEntity(roomLink2);
        TransitionManager transitionManager = new TransitionManagerImpl(w, t);
        UpdateManager updateManager = new UpdateManagerImpl();
        gameStateManager = new GameStateManager(transitionManager, updateManager);
        ((StateTrigger)t).addStateObserver(transitionManager);
        t.setCurrentRoom(startRoom);

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
    public void update(long deltaTime) {
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
    public void render(long time) {
        //RENDER WITH TIME LAG TO REPRESENT
    }

    /**
     * @return
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
