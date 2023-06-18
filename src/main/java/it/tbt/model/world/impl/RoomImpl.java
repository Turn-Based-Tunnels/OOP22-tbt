package it.tbt.model.world.impl;

import it.tbt.model.entities.KillableEntity;
import it.tbt.model.world.api.KillObserver;
import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Default implementation of the Room interface.
 */

public class RoomImpl implements Room, KillObserver {

    private String roomName;
    private int roomWidth;
    private int roomHeight;
    private Set<SpatialEntity> entities;

    /**
     * @param roomName the room's name
     */
    public RoomImpl(final String roomName, final int roomWidth, final int roomHeight) {
        this.roomName = roomName;
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        entities = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final SpatialEntity entity) {
        if(isValidCoordinates(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight())) {
            entities.add(entity);
            if(entity instanceof KillableEntity) {
                ((KillableEntity) entity).setKillObserver(this);
            }

        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKill(final SpatialEntity spatialEntity) {
        this.entities.remove(spatialEntity);
    }
}
