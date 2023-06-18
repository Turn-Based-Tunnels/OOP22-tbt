package it.tbt.model.menu.impl;

/**
 * The {@code MenuButton} class represents a button in a menu.
 * It extends the {@link MenuItem} class and implements the {@link it.tbt.model.menu.api.MenuButton} interface.
 */
public abstract class MenuButton extends MenuItem implements it.tbt.model.menu.api.MenuButton {

    /**
     * Constructs a {@code MenuButton} with the specified text.
     *
     * @param text the text of the menu button
     */
    public MenuButton(final String text) {
        super(text);
    }
}
