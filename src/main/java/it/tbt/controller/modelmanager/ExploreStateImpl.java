package it.tbt.controller.modelmanager;

import it.tbt.model.party.IParty;
import it.tbt.model.statechange.PauseTrigger;
import it.tbt.model.world.api.Room;

/**
 * Default implementation of the Explore GameState wrapper.
 */
public final class ExploreStateImpl implements ExploreState {

    private Room room;
    private IParty party;
    private PauseTrigger pauseTrigger;

    /**
     * @param room the current room
     * @param party the party used by the player
     * @param pauseTrigger possibility of switching to Pause Game State
     */
    public ExploreStateImpl(final Room room, final IParty party, final PauseTrigger pauseTrigger) {
        this.room = room;
        this.party = party;
        this.pauseTrigger = pauseTrigger;
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
    @Override
    public PauseTrigger getTriggerPause() {
        return this.pauseTrigger;
    }

}
