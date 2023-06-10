package it.tbt.Model.World.impl;

import com.sun.glass.ui.mac.MacPlatformFactory;
import it.tbt.Model.Entities.Entity;
import it.tbt.Model.World.api.Room;
import it.tbt.Model.World.collision.Collidable;
import it.tbt.Model.World.collision.CollisionHandler;
import it.tbt.Model.World.collision.CollisionHandlerImpl;
import it.tbt.Model.World.position.RoomPosition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class RoomImpl implements Room {

    private String roomName;
    private int roomWidth;
    private int roomHeight;

    private CollisionHandler collisionHandler;

    private Set<Entity> entities;

    public RoomImpl(final String roomName) {
        roomWidth = 400;
        roomHeight = 400;
        this.roomName = roomName;
        entities = new HashSet<>();
        this.collisionHandler = new CollisionHandlerImpl();
    }

    /**
     * @param entity
     */
    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
        addCollidable(entity);
    }

    /**
     * @return
     */
    @Override
    public Set<Entity> getEntities() {
        return entities;
    }

    /**
     * @return
     */
    @Override
    public CollisionHandler getCollisionHandler() {
        return this.collisionHandler;
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

    private void addCollidable(Entity entity) {
        if(entity instanceof Collidable) {
            this.collisionHandler.addCollidable((Collidable) entity);
        }
    }
}
