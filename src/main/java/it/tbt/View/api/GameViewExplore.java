package it.tbt.View.api;

import it.tbt.Controller.ModelManager.ExploreState;

public interface GameViewExplore extends GameView {
    void setData(ExploreState exploreStateImpl);
}
