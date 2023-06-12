package it.tbt.model.roomLink;

import it.tbt.model.World.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.function.Predicate;

public interface RoomLink extends SpatialEntity {
    Room getRoomOnPredicate(Predicate<Room> predicate);

}
