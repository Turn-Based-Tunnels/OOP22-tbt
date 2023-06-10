package it.tbt.view.api;

import it.tbt.controller.ModelManager.ExploreState;

public interface GameViewExplore extends GameView {
    void setData(ExploreState exploreStateImpl);
}
