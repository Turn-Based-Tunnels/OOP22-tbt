package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;


public interface Room {
    public static int DEFAULT_START_X = 0;
    public static int DEFAULT_START_Y = 0;

    void addEntity(final SpatialEntity entity);

    Set<SpatialEntity> getEntities();

    Boolean isValidCoordinates(int x, int y);

}
