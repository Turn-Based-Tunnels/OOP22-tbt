package it.tbt.controller.modelmanager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.InventoryTrigger;
import it.tbt.model.statechange.PauseTrigger;
import it.tbt.model.world.api.Room;

/**
 * Default implementation of the Explore GameState wrapper.
 */
public final class ExploreStateImpl implements ExploreState {

    private Room room;
    private IParty party;
    private PauseTrigger pauseTrigger;

    private InventoryTrigger inventoryTrigger;

    /**
     * @param room the current room
     * @param party the party used by the player
     * @param pauseTrigger possibility of switching to Pause Game State
     * @param inventoryTrigger possibility of switching to Inventory Game State
     */
    @SuppressFBWarnings("EI2")
    public ExploreStateImpl(final Room room,
                            final IParty party,
                            final PauseTrigger pauseTrigger,
                            final InventoryTrigger inventoryTrigger) {
        this.room = room;
        this.party = party;
        this.pauseTrigger = pauseTrigger;
        this.inventoryTrigger = inventoryTrigger;
    }
    /**
     * {@inheritDoc}
     */
    public IParty getParty() {
        return party; }
    /**
     * {@inheritDoc}
     */
    @Override
    public Room getRoom() {
        return this.room;
    }
    /**
     * {@inheritDoc}
     */
    //@SuppressFBWarnings("EI")
    @Override
    public PauseTrigger getTriggerPause() {
        return new PauseTrigger(this.pauseTrigger);
    }
    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI")
    @Override
    public InventoryTrigger getTriggerInventory() {
        return this.inventoryTrigger;
    }

}
