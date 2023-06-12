package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;


public interface Room {
    void addEntity(final SpatialEntity entity);

    Set<SpatialEntity> getEntities();

    Boolean isValidCoordinates(int x, int y);

}
