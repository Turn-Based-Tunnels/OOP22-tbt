package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuSelect;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MainMenuController} class represents the view controller for the main menu state.
 * It handles user input and triggers actions associated with the main menu state.
 */
public class MainMenuController implements ViewController {

    private final List<Command> commands;

    private final MenuState modelState;

    /**
     * Constructs a new {@code MainMenuController} with the specified menu state.
     */
    public MainMenuController(final MenuState menuStateImpl){
        this.modelState = menuStateImpl;
        commands = new ArrayList<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {

        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.commands.add (() -> modelState.PreviousElement ());
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.commands.add (() -> modelState.NextElement ());
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> {
                if (modelState.getItems ().get (modelState.getFocus ()) instanceof MenuButton) {
                    this.commands.add (((MenuButton) modelState.getItems ().get (modelState.getFocus ())).getAction ());
                }
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                if (modelState.getItems ().get (modelState.getFocus ()) instanceof MenuSelect<?>) {
                    this.commands.add (((MenuSelect) modelState.getItems ().get (modelState.getFocus ())).nextOption ());
                }
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                if (modelState.getItems ().get (modelState.getFocus ()) instanceof MenuSelect<?>) {
                    this.commands.add (((MenuSelect) modelState.getItems ().get (modelState.getFocus ())).previousOption ());
                }
            }
            case KeyEvent.VK_ESCAPE -> this.commands.add (() -> System.exit (0));
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
