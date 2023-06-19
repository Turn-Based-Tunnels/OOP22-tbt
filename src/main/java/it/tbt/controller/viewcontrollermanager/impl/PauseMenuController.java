package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuSelect;

import java.awt.event.KeyEvent;

/**
 * The {@code PauseMenuController} class represents the view controller for the pause menu state.
 * It handles user input and triggers actions associated with the pause menu state.
 */
public class PauseMenuController extends AbstractViewController {

    private final MenuState modelState;

    /**
     * Constructs a new {@code PauseMenuController} with the specified menu state.
     *
     * @param menuStateImpl the menu state
     * @throws IllegalArgumentException if menuStateImpl is null
     */
    public PauseMenuController(final MenuState menuStateImpl) {
        super();
        if (menuStateImpl == null) {
            throw new IllegalArgumentException("MenuState cannot be null");
        }
        this.modelState = menuStateImpl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.addCommand(() -> modelState.previousElement());
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.addCommand(() -> modelState.nextElement());
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuButton) {
                    this.addCommand(((MenuButton) modelState.getItems().get(modelState.getFocus())).getAction());
                }
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>) {
                    this.addCommand(((MenuSelect) modelState.getItems().get(modelState.getFocus())).nextOption());
                }
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>) {
                    this.addCommand(((MenuSelect) modelState.getItems().get(modelState.getFocus())).previousOption());
                }
            }
            case KeyEvent.VK_ESCAPE -> this.addCommand(modelState::triggerExplore);
            default -> {
            }
        }
    }
}
