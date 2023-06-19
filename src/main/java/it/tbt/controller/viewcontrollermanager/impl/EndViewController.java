package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EndViewController} class represents the view controller for the end state.
 * It handles user input and triggers actions associated with the end state.
 */
public class EndViewController implements ViewController {

    private final List<Command> commands;
    private final EndState endState;

    /**
     * Constructs a new {@code EndViewController} with the specified end state.
     *
     * @param endState the end state
     * @throws IllegalArgumentException if the endState is null
     */
    public EndViewController(final EndState endState) {
        if (endState == null) {
            throw new IllegalArgumentException("EndState cannot be null");
        }
        this.endState = endState;
        commands = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        endState.triggerMainMenu();
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
        commands.clear();
    }
}
