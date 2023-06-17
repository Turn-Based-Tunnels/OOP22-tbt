package it.tbt.view.api;

import it.tbt.controller.viewcontrollermanager.api.ViewController;

/**
 * Abstract GameView for all graphical frameworks.
 */
public abstract class AbstractGameView implements GameView {
    private ViewController viewController;
    /**
     * @param viewController il controller per la view.
     */
    protected AbstractGameView(final ViewController viewController) {
        this.viewController = viewController;
    }
}
