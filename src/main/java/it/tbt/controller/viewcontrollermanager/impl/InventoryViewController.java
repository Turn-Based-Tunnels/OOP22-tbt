package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code InventoryViewController} class represents the view controller for the inventory state.
 * It handles user input and triggers actions associated with the inventory state.
 */
public class InventoryViewController implements ViewController {

    List<Command> commands;
    private final InventoryState inventoryState;
    /**
     * Constructs a new {@code InventoryViewController} with the specified inventory state.
     *
     * @param inventoryState the inventory state
     */
    public InventoryViewController(final InventoryState inventoryState){
        this.inventoryState = inventoryState;
        commands = new ArrayList<> ();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.commands.add (() -> inventoryState.previousElement ());
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.commands.add (() -> inventoryState.nextElement ());
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> this.commands.add (() -> inventoryState.performAction ());
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> this.commands.add (() -> inventoryState.nextPhase ());
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> this.commands.add (() -> inventoryState.previousPhase ());
            case KeyEvent.VK_ESCAPE -> this.commands.add (() -> inventoryState.switchToExplore ());
            default -> {
            }
        }
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
        this.commands.clear();
    }
}
