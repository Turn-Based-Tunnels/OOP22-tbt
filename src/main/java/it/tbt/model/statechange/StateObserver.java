package it.tbt.model.statechange;

import it.tbt.model.fight.api.FightModel;

public interface StateObserver {
    public void onExplore();

    public void onFight(FightModel fightModel);

    public void onMenu();

    public void onPause();
}
