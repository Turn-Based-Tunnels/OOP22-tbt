package it.tbt.engine.api;

/**
 *
 */

public interface Game {

    void initialize();
    void loadResources();
    void update(long deltaTime);
    void render();
    void render(long time);
    Boolean handleInput();
    Boolean isOver();
    void cleanup();
}
