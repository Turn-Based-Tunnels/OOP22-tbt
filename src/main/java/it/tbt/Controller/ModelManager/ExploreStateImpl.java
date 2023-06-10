package it.tbt.Controller.ModelManager;

import it.tbt.Model.Entities.Entity;
import it.tbt.Model.Party.IParty;
import it.tbt.Model.World.api.Room;

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

    public IParty getParty() { return party; };
}
