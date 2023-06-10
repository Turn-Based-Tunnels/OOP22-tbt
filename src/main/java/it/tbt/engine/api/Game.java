package it.tbt.engine.api;

/**
 *
 */

public interface Game {

    void initialize();
    void loadResources();
    void update(float deltaTime);
    void render();
    void render(float time);
    Boolean handleInput();
    void cleanup();
}
