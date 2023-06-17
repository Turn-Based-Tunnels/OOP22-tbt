package it.tbt.model.world.impl;

import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Default implementation of the Room interface.
 */

public class RoomImpl implements Room {

    private String roomName;
    private int roomWidth = X_AXIS_UPPERBOUND;
    private int roomHeight = Y_AXIS_UPPERBOUND;

    private Set<SpatialEntity> entities;

    /**
     * @param roomName the room's name
     */
    public RoomImpl(final String roomName) {
        this.roomName = roomName;
        entities = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final SpatialEntity entity) {
        entities.add(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<SpatialEntity> getEntities() {
        return entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isValidCoordinates(final int xCenter, final int yCenter, final int width, final int height) {
        final int left = xCenter - (width / 2);
        final int right = xCenter + (width / 2);
        final int top = yCenter - (height / 2);
        final int bottom = yCenter + (height / 2);
        return !(left < 0 || right > this.roomWidth || top < 0 || bottom > this.roomHeight);
    }

}
