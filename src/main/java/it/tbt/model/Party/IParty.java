package it.tbt.model.Party;

import it.tbt.model.Entities.SpatialEntity;
import it.tbt.model.World.api.Positionable;
import it.tbt.model.World.api.Room;
import org.w3c.dom.Entity;

public interface IParty extends SpatialEntity {
    void setCurrentRoom(Room room);
    Room getCurrentRoom();
    void move(int xv, int yv);

}
