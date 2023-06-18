package it.tbt.model.statechange;

/**
 * Class for triggering an Inventory GameState on a StateObserver object.
 */
public class InventoryTrigger implements StateTrigger {

    public StateObserver stateObserver;

    /**
     * @param stateObserver the state observer to be notified.
     */
    public InventoryTrigger(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

    /**
     * Notifies the stateObserver of the will on changing the GameState.
     */
    public void triggerInventory() {
        this.stateObserver.onInventory();
    }

    /**
     * This object shall have a reference to a StateObserver in order to notify it for changes of the GameState.
     *
     * @param stateObserver
     */
    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
