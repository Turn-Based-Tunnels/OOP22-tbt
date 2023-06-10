package it.tbt.Model.World.impl;

import it.tbt.Model.Entities.Entity;
import it.tbt.Model.World.api.Room;
import java.util.HashSet;
import java.util.Set;


public class RoomImpl implements Room {

    private String roomName;
    private int roomWidth;
    private int roomHeight;

    private Set<Entity> entities;

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
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * @return
     */
    @Override
    public Set<Entity> getEntities() {
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
