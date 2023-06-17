package it.tbt.model;

/**
 * Enumeration for all the different GameStates which the Game can be in.
 */
public enum GameState {
    /**
     * Explore GameState, the player moves around the environment.
     */
    EXPLORE,
    /**
     * Fight GameState, the player engages in fight.
     */
    FIGHT,
    /**
     * MainMenu GameState, the player can choose from different options.
     */
    MENU,
    /**
     * Pause GameState, the player can choose from different options.
     */
    PAUSE
}
