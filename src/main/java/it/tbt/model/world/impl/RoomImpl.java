package it.tbt.model.world.impl;

import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.HashSet;
import java.util.Set;


public class RoomImpl implements Room {

    private String roomName;
    private int roomWidth;
    private int roomHeight;

    private Set<SpatialEntity> entities;

    public RoomImpl(final String roomName) {
        roomWidth = 400;
        roomHeight = 400;
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
     * @param x
     * @param y
     * @return
     */
    @Override
    public Boolean isValidCoordinates(int x, int y) {
        return x>=0 && x<=roomWidth && y>=0 && y<=roomHeight;
    }

}
