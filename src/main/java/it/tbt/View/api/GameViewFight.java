package it.tbt.View.api;

import it.tbt.Controller.ModelManager.FightState;
import it.tbt.Controller.ModelManager.ModelState;

public interface GameViewFight extends GameView {
    void setData(FightState modelState);
}
