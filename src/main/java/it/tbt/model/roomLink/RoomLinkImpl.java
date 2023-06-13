package it.tbt.model.roomLink;

import it.tbt.model.world.api.Room;
import it.tbt.model.world.interaction.Interactable;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.SpatialEntityImpl;
import it.tbt.model.party.IParty;

import java.util.List;
import java.util.function.Predicate;

public class RoomLinkImpl extends SpatialEntityImpl implements RoomLink, Interactable {

    private Room room1;
    private Room room2;

    public RoomLinkImpl(String Name, int X, int Y, int width, int height, Room firstRoom, Room secondRoom) {
        super(Name, X, Y, width, height);
        this.room1 = firstRoom;
        this.room2 = secondRoom;

    }

    @Override
    public Room getRoomOnPredicate(Predicate<Room> predicate) {
        return List.of(room1, room2).stream().filter(l->predicate.test(l)).findFirst().get();
    }


    /**
     * @param interactable
     */
    @Override
    public void OnInteraction(SpatialEntity interactable) {
        if(interactable instanceof IParty) {
            ((IParty)interactable).setCurrentRoom(getRoomOnPredicate(l->l!=((IParty)interactable).getCurrentRoom()));
        }

    }
}

