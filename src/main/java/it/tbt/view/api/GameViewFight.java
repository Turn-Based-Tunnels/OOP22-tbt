package it.tbt.view.api;

import it.tbt.controller.ModelManager.FightState;

public interface GameViewFight extends GameView {
    void setData(FightState modelState);
}
