package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;

public class ASAPGameLoop implements GameLoop {

    private Game game;
    private boolean running;

    public ASAPGameLoop(final Game g) {
        this.game = g;
    }

    /**
     *
     */
    @Override
    public void start() {
        this.game.initialize();
        this.running = true;
        new Thread(this).start();
    }

    /**
     *
     */
    @Override
    public void stop() {
        this.running = false;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        //Problems: on fast machines the render will be faster which means the world will render faster and it means that
        //that animations run faster, could be too fast.
        game.handleInput();
        game.update(16);
        game.render();

    }
}
