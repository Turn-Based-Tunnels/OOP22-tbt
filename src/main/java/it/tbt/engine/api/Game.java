package it.tbt.engine.api;

/**
 * Interface for the handling of all the steps of the GameLogic.
 */

public interface Game {

    /**
     * Initialization process for correct game logic handling.
     */
    void initialize();
    /**
     * Updates the Model.
     * @param deltaTime
     */
    void update(long deltaTime);
    /**
     * Renders the current view.
     */
    void render();
    /**
     * @return true if any input has been discovered, false otherwise.
     */
    Boolean handleInput();
    /**
     * @return true if the conditions for the game to be considered over are met.
     * False otherwise.
     */
    Boolean isOver();
}
