package it.tbt.model.statechange;

public interface StateObserver {
    void onExplore();
    void onFight();
    void onMenu();
}
