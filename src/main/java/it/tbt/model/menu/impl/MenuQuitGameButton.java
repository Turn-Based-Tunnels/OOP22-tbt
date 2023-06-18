package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;

/**
 * The {@code MenuQuitGameButton} class represents a menu button for quitting the game.
 * It extends the {@link MenuButton} class.
 */
public class MenuQuitGameButton extends MenuButton{
    /**
     * Creates a new instance of {@code MenuQuitGameButton} with the specified text.
     *
     * @param text the text of the button
     */
    public MenuQuitGameButton(final String text) {
        super(text);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonCommand getAction(){
        return () -> System.exit(0);

    }
}