package it.tbt.model.statechange;

public interface StateObserver {
    public void onExplore();
    public void onFight();
    public void onMenu();
    public void onPause();
}
