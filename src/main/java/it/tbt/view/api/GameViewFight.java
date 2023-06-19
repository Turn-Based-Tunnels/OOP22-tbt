package it.tbt.view.api;

import it.tbt.controller.modelmanager.FightState;

/**
 * The GameViewFight interface represents a view for displaying and interacting
 * with the fight state in the game.
 * It extends the GameView interface.
 */
public interface GameViewFight extends GameView {

    /**
     * Sets the data of the fight state to be displayed in the view.
     *
     * @param modelState The FightState object representing the current state of the
     *                   fight.
     */
    void setData(FightState modelState);
}
