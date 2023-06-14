package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;


public interface Room {
    public static int DEFAULT_START_X = 0;
    public static int DEFAULT_START_Y = 0;
    public static int X_AXIS_UPPERBOUND = 300;
    public static int Y_AXIS_UPPERBOUND = 300;

    void addEntity(final SpatialEntity entity);

    Set<SpatialEntity> getEntities();

    Boolean isValidCoordinates(int x, int y);

}
