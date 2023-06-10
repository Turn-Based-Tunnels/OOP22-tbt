package it.tbt.Model.World.api;

import it.tbt.Model.Entities.Entity;
import it.tbt.Model.World.collision.CollisionHandler;
import it.tbt.Model.World.position.RoomPosition;

import java.util.Set;


public interface Room {
    void addEntity(final Entity entity);

    Set<Entity> getEntities();

    CollisionHandler getCollisionHandler();

    Boolean isValidCoordinates(int x, int y);

}
