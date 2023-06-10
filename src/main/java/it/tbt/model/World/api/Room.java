package it.tbt.model.World.api;

import it.tbt.model.Entities.Entity;

import java.util.Set;


public interface Room {
    void addEntity(final Entity entity);

    Set<Entity> getEntities();

    Boolean isValidCoordinates(int x, int y);

}
