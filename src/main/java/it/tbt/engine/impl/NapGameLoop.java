package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;

public class NapGameLoop implements GameLoop {

    private static final int TARGET_FPS = 60;
    private static final long MS_PER_FRAME = 1000 / TARGET_FPS;

    private Game game;
    private boolean running;

    public NapGameLoop(final Game g) {
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

        //Problems: this is good, as long as everything is done in under MS_PER_FRAME,
        //because if not
        long start = System.currentTimeMillis();
        game.handleInput();
        game.update(MS_PER_FRAME);
        game.render();
        try {
            Thread.sleep(start + MS_PER_FRAME - System.currentTimeMillis());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
