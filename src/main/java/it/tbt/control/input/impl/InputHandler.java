package it.tbt.control.input.impl;

import it.tbt.control.input.api.InputListener;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class InputHandler {
    private InputListener inputListener;

    public InputHandler(InputListener inputListener) {
        this.inputListener = inputListener;

        // Register the KeyEventDispatcher to capture keyboard input
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    inputListener.onKeyPressed(event.getKeyCode());
                }
                return false;
            }
        });
    }

}