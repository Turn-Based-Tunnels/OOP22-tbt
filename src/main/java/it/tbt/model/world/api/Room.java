package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;


public interface Room {
    int X_AXIS_UPPERBOUND = 300;
    int Y_AXIS_UPPERBOUND = 300;
    int DEFAULT_START_X = 0;
    int DEFAULT_START_Y = 0;


    void addEntity(final SpatialEntity entity);

    Set<SpatialEntity> getEntities();

    Boolean isValidCoordinates(int xCenter, int yCenter, int width, int height);

}
