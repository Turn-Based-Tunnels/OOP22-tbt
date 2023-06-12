package it.tbt.view.api;

import it.tbt.controller.ModelManager.ExploreState;

/**
 * Specific version of the GameView interface which represents
 * the view of the specific GameState Explore.
 * It should be able to accept an ExploreState object to be able to render
 * its information.
 * @see GameView
 * @see it.tbt.model.GameState
 * @see ExploreState
 */
public interface GameViewExplore extends GameView {
    /**
     * Sets the data object which encapsulates this specific View.
     * @param exploreStateImpl the instance of the ExploreState
     * which contains the data needed to be rendered.
     */
    void setData(ExploreState exploreStateImpl);
}
