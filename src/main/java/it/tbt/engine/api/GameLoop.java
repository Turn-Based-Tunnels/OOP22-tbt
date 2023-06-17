package it.tbt.engine.api;

/**
 * The GameLoop interface defines the single loop cycle.
 * It exposes a Boolean telling if the GameLogic is consistent up to the last loop cycle called.
 */

public interface GameLoop {

    /**
     * Method that represents the single iteration of the game logic.
     */
    void loop();
    /**
     * @return true if up to the last call of the loop method the logic is consistent thus
     * making sure that another call to the loop method provides another consistent game status.
     */
    Boolean isConsistent();


}
