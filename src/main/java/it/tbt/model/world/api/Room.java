package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;

/**
 * Interface for a Room which is to be able to store entities and permit operations on them.
 */

public interface Room {

    /**
     * Length of the Room.
     */
    int X_AXIS_UPPERBOUND = 300;
    /**
     * Height of the Room.
     */
    int Y_AXIS_UPPERBOUND = 300;

    /**
     * @param entity to be added to the Room.
     */
    void addEntity(SpatialEntity entity);

    /**
     * @return the entities in the Room as a Set.
     */
    Set<SpatialEntity> getEntities();

    /**
     * @param xCenter
     * @param yCenter
     * @param width
     * @param height
     * @return true if the rectangle formed by the four params does not exceed the Room boundaries.
     */
    Boolean isValidCoordinates(int xCenter, int yCenter, int width, int height);

}
