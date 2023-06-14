package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.model.command.api.Command;
import it.tbt.model.command.explore.CommandInteract;
import it.tbt.model.command.explore.CommandMove;
import it.tbt.model.world.interaction.InteractionTrigger;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Default implementation of the Controller of the Explore state.
 */

public class ExploreControllerImpl implements ExploreController {

    private List<Command> commands;

    private ExploreState modelState;

    public ExploreControllerImpl(final ExploreState exploreStateImpl) {
        this.modelState = exploreStateImpl;
        this.clean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Command> getCommands() {
        return this.commands;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clean() {
        this.commands = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    public void moveRight() {
        this.commands.add(new CommandMove(this.modelState.getParty(), DEFAULT_MOVE_X,0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveDown() {
        this.commands.add(new CommandMove(this.modelState.getParty(),0,DEFAULT_MOVE_Y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveUp() {
        this.commands.add(new CommandMove(this.modelState.getParty(), 0, -DEFAULT_MOVE_Y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveLeft() {
        this.commands.add(new CommandMove(this.modelState.getParty(), -DEFAULT_MOVE_X, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interactWithProximity() {
        if(this.modelState.getParty() instanceof InteractionTrigger) {
            this.commands.add(new CommandInteract((InteractionTrigger) this.modelState.getParty()));
        }

    }

    @Override
    public void onKeyPressed(int key) {
        if(key==KeyEvent.VK_D) {
            this.moveRight();
        } else if(key==KeyEvent.VK_W) {
            this.moveUp();
        } else if(key==KeyEvent.VK_A) {
            this.moveLeft();
        } else if(key==KeyEvent.VK_S) {
            this.moveDown();
        } else if(key==KeyEvent.VK_E) {
            this.interactWithProximity();
        }
    }
}
