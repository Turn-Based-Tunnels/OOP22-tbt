package it.tbt.engine.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import it.tbt.commons.resourceloader.world.impl.WorldCreationDefault;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Status;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.characters.skills.SkillFactory;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.factories.AntidoteFactory;
import it.tbt.model.entities.items.factories.PotionFactory;
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

/**
 * Default implementation of the Game interface.
 */
public final class GameImpl implements Game {
    private ViewControllerManager viewControllerManager;
    private IGameStateManager gameStateManager;

    /**
     * This implementation uses a ViewControllerManager and an GameStateManager as
     * helper classes to delegate responsibility.
     * Creates both the World, the IParty and the Menus object with default
     * implementations.
     * 
     * @param gvf the GameViewFactory which is used to create views different based
     *            on the graphical framework chosen.
     */
    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl(gvf);
        gameStateManager = new GameStateManager(new WorldCreationDefault().createWorld(),
                PartyFactory.createDefaultParty(),
                MenuFactory.getMainMenu(),
                MenuFactory.getPauseMenu());
        ArrayList<Ally> allies = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>(SkillFactory.getFactory().getSkills());

        allies.add(new Ally("Roberto", 50, 50, 50, new ArrayList<>(Arrays.asList(skills.get(0)))));
        allies.add(new Ally("Gianfranco", 10, 10, 70, new ArrayList<>(Arrays.asList(skills.get(1)))));
        allies.add(new Ally("Caparezza", 30, 90, 20, new ArrayList<>(Arrays.asList(skills.get(2)))));
        allies.add(new Ally("Robertino", 20, 20, 20, new ArrayList<>(Arrays.asList(skills.get(3)))));
        Party p = new Party("party", 75, 75, 75, 75, allies);
        for(final Potion potion : PotionFactory.getInstance().getItems()) {
            p.addItemToInventory(potion);
            p.addItemToInventory(potion);
        }
        p.addItemToInventory(AntidoteFactory.getInstance().getAntidote());
        p.addCash(5000);
        gameStateManager = new GameStateManager(new WorldCreationDefault().createWorld(),
                p, MenuFactory.getMainMenu(), MenuFactory.getPauseMenu());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        this.viewControllerManager.renderView(
                this.gameStateManager.getState(),
                this.gameStateManager.getStateModel(),
                true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long deltaTime) {
        this.gameStateManager.updateState(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.viewControllerManager.renderView(
                this.gameStateManager.getState(),
                this.gameStateManager.getStateModel(),
                this.gameStateManager.hasStateChanged());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean handleInput() {
        Boolean r = this.viewControllerManager.getCommands().isEmpty();
        if (!r) {
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
