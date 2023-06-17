package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;
import it.tbt.model.command.explore.CommandInteract;
import it.tbt.model.command.explore.CommandMove;
import it.tbt.model.command.explore.CommandPause;
import it.tbt.model.world.interaction.InteractionTrigger;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Default implementation of the Controller of the Explore state.
 */

public final class ExploreControllerImpl implements ViewController {

    private List<Command> commands;
    public static int DEFAULT_MOVE_X = 5;
    public static int DEFAULT_MOVE_Y = 5;
    private ExploreState modelState;

    /**
     * @param exploreStateImpl the state on which the controller maps the input to
     *                         the commands to the state.
     */
    public ExploreControllerImpl(final ExploreState exploreStateImpl) {
        this.modelState = exploreStateImpl;
        this.commands = new LinkedList<>();
    }

    /**
     * @return the list of Commands intercepted.
     */
    @Override
    public List<Command> getCommands() {
        return this.commands;
    }

    /**
     * Cleans the list of Commands this ViewController currently has.
     */
    @Override
    public void clean() {
        this.commands = new LinkedList<>();
    }

    /**
     * Moves the IParty in the X-axis by a positive amount.
     */
    private void moveRight() {
        this.commands.add(new CommandMove(this.modelState.getParty(), DEFAULT_MOVE_X, 0));
        this.commands.add(new CommandMove(this.modelState.getParty(), DEFAULT_MOVE_X, 0));
    }

    /**
     * Moves the IParty in the Y-axis by a positive amount.
     */
    private void moveDown() {
        this.commands.add(new CommandMove(this.modelState.getParty(), 0, DEFAULT_MOVE_Y));
    }

    /**
     * Moves the IParty in the Y-axis by a negative amount.
     */
    private void moveUp() {
        this.commands.add(new CommandMove(this.modelState.getParty(), 0, -DEFAULT_MOVE_Y));
    }

    /**
     * Moves the IParty in the X-axis by a negative amount.
     */
    private void moveLeft() {
        this.commands.add(new CommandMove(this.modelState.getParty(), -DEFAULT_MOVE_X, 0));
    }

    /**
     * The IParty interacts with the environment.
     */
    private void interactWithProximity() {
        if (this.modelState.getParty() instanceof InteractionTrigger) {
            this.commands.add(new CommandInteract((InteractionTrigger) this.modelState.getParty()));
        }
    }

    /**
     * Triggers the Pause GameState.
     */
    private void triggerPause() {
        this.commands.add(new CommandPause(this.modelState.getTriggerPause()));
    }

    @Override
    public void onKeyPressed(final int key) {
        if (key == KeyEvent.VK_D) {
            this.moveRight();
        } else if (key == KeyEvent.VK_W) {
        } else if (key == KeyEvent.VK_W) {
            this.moveUp();
        } else if (key == KeyEvent.VK_A) {
        } else if (key == KeyEvent.VK_A) {
            this.moveLeft();
        } else if (key == KeyEvent.VK_S) {
        } else if (key == KeyEvent.VK_S) {
            this.moveDown();
        } else if (key == KeyEvent.VK_E) {
        } else if (key == KeyEvent.VK_E) {
            this.interactWithProximity();
        } else if (key == KeyEvent.VK_ESCAPE) {
            this.triggerPause();
        }
    }
}
