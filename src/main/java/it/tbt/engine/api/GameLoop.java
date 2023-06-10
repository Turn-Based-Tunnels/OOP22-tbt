package it.tbt.engine.api;

/**
 * The GameLoop interface defines the method that represents the single iteration of the game logic,
 * and it exposes a Boolean telling if the GameLogic is consistent until the last
 */

public interface GameLoop{
    void loop();
    Boolean isConsistent();


}
