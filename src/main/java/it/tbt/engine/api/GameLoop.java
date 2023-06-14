package it.tbt.engine.api;

/**
 * The GameLoop interface defines the ,
 * and it exposes a Boolean telling if the GameLogic is consistent until the last
 */

public interface GameLoop{

    /**
     * Method that represents the single iteration of the game logic
     */
    void loop();
    /**
     * @return true if up to the last call of the loop method the logic is consistent thus
     * making sure that another call to the loop method provides another consistent game status.
     */
    Boolean isConsistent();


}
