package it.tbt.view.api;

import it.tbt.controller.viewcontrollermanager.api.ViewController;

public abstract class AbstractGameView implements GameView{
    private ViewController viewController;
    public static int WIDTH_WINDOW;
    public static int HEIGHT_WINDOW;

    protected AbstractGameView(ViewController viewController) {
        this.viewController = viewController;
    }
}
