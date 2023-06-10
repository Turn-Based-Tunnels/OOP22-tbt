package it.tbt;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;
import it.tbt.engine.impl.FixedTimeGameLoop;
import it.tbt.engine.impl.GameFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 *
 * Comment.
 */

public final class TurnBasedTunnels{
    public static void main(final String[] args) {
        TurnBasedTunnelsFx t = new TurnBasedTunnelsFx();
        Application.launch(t.getClass(), args);
    }

}
