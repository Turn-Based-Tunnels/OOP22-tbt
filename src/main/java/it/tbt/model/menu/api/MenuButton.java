package it.tbt.model.menu.api;

import it.tbt.model.command.menu.ButtonCommand;

/**
 * The {@code MenuButton} interface represents a button in a menu.
 */
public interface MenuButton {
    /**
     * Returns the action associated with the menu button.
     *
     * @return the action associated with the menu button
     */
    ButtonCommand getAction();
}