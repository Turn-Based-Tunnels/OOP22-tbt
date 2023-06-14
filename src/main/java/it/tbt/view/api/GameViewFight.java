package it.tbt.view.api;

import it.tbt.controller.modelmanager.FightState;

public interface GameViewFight extends GameView {
    void setData(FightState modelState);
}
