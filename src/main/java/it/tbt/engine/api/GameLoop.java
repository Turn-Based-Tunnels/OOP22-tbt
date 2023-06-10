package it.tbt.engine.api;

/**
 * The GameLoop interface defines the methods for controlling the flow of a game.
 */

public interface GameLoop extends Runnable {
    void start();
    void stop();

}
