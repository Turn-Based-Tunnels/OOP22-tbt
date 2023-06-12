package it.tbt.model.statechange;

public interface StateTrigger {
    public void addStateObserver(StateObserver observer);

    public void removeStateObserver(StateObserver observer);
}
