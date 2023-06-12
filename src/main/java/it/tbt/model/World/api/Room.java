package it.tbt.model.World.api;

import it.tbt.model.entities.Entity;
import it.tbt.model.entities.SpatialEntity;

import java.util.Set;


public interface Room {
    void addEntity(final SpatialEntity entity);

    Set<SpatialEntity> getEntities();

    Boolean isValidCoordinates(int x, int y);

}
