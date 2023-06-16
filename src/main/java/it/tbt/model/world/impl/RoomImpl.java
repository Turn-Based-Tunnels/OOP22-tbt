package it.tbt.model.world.impl;

import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.HashSet;
import java.util.Set;


public class RoomImpl implements Room {

    private String roomName;
    private int roomWidth = X_AXIS_UPPERBOUND;
    private int roomHeight = Y_AXIS_UPPERBOUND;

    private Set<SpatialEntity> entities;

    public RoomImpl(final String roomName) {
        this.roomName = roomName;
        entities = new HashSet<>();
    }

    /**
     * @param entity
     */
    @Override
    public void addEntity(SpatialEntity entity) {
        entities.add(entity);
    }

    /**
     * @return
     */
    @Override
    public Set<SpatialEntity> getEntities() {
        return entities;
    }

    /**
     * @param xCenter
     * @param yCenter
     * @param width
     * @param height
     * @return
     */
    @Override
    public Boolean isValidCoordinates(int xCenter, int yCenter, int width, int height) {
        final int left = xCenter - (width / 2);
        final int right = xCenter + (width / 2);
        final int top = yCenter - (height / 2);
        final int bottom = yCenter + (height / 2);
        return !(left < 0 || right > this.roomWidth || top < 0 || bottom > this.roomHeight);
    }

}
