package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractViewController implements ViewController {

    private List<Command> commands;

    protected AbstractViewController() {
        this.commands = new LinkedList<>();
    }

    /**
     * @return the list of Commands intercepted.
     */
    @Override
    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    /**
     * Cleans the Commands this ViewController currently has.
     */
    @Override
    public void clean() {
        this.commands.clear();
    }
    protected void addCommand(final Command command) {
        this.commands.add(command);
    }
}
