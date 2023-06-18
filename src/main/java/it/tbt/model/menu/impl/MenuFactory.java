package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuItem;

import java.util.*;

/**
 * The {@code MenuFactory} class provides factory methods to create different menus.
 */
public class MenuFactory {

    private MenuFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates and returns the main menu.
     *
     * @return the main menu
     */
    public static MenuModelImpl getMainMenu() {
        final List<MenuItem> items = new ArrayList<>();

        items.addAll(Arrays.asList(new MenuNewGameButton("New Game"),
                new MenuQuitGameButton("Exit")));

        return new MenuModelImpl ("Main Menu", items);
    }

    /**
     * Creates and returns the pause menu.
     *
     * @return the pause menu
     */
    public static MenuModelImpl getPauseMenu() {
        final List<MenuItem> items = new ArrayList<>();

        items.addAll(Arrays.asList(new MenuNewGameButton("Continue"),
                new MenuQuitToTitleButton("Quit to Title Screen"),
                new MenuQuitGameButton("Exit To Desktop")));

        return new MenuModelImpl ("Pause", items);
    }
}
