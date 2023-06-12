package it.tbt.controller.modelmanager;

import it.tbt.model.entities.Entity;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.Room;

import java.util.LinkedList;
import java.util.List;


public class ExploreStateImpl implements ExploreState{

    private Room room;
    private IParty party;
    public ExploreStateImpl(Room room, IParty party) {
        this.room = room;
        this.party = party;
    }

    public List<Entity> getAllEntities() {
        return new LinkedList<>(this.room.getEntities());
    }

    public IParty getParty() { return party; }

    /**
     * @return
     */
    @Override
    public Room getRoom() {
        return this.room;
    }

    ;
}
