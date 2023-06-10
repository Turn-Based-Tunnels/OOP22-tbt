package it.tbt.Model.Party;

import it.tbt.Commons.Point2D;
import it.tbt.Model.Entities.Entity;
import it.tbt.Model.World.api.Room;
import it.tbt.Model.World.collision.Collidable;
import it.tbt.Model.World.collision.CollisionHandler;
import it.tbt.Model.World.collision.CollisionTrigger;
import it.tbt.Model.time.TimeAffected;
import java.util.HashSet;
import java.util.Set;

public class Party extends Entity implements IParty, CollisionTrigger, TimeAffected {

    CollisionHandler collisionHandler;
    Set<Character> roaster;
    Room currentRoom;

    public Party(String Name, int X, int Y) {
        super(Name, X, Y);
        this.roaster = new HashSet<>();
    }

    public Party(String Name, int X, int Y, Set<Character> c) {
        super(Name, X, Y);
        this.roaster = c;
    }

    /**
     * @param room
     */
    @Override
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * @return
     */
    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * @param xv
     * @param yv
     */
    @Override
    public void move(int xv, int yv) {
        if(this.currentRoom.isValidCoordinates(xv+this.X, yv+this.Y)) {
            this.X += xv;
            this.Y += yv;
        }
    }

    /**
     * @param collisionHandler
     */
    @Override
    public void registerHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }

    /**
     *
     */
    @Override
    public void notifyPossibleCollision() {
        this.collisionHandler.checkForColliding(this);
    }

    /**
     * @param e
     */
    @Override
    public void onCollide(Collidable e) {

    }

    /**
     * @return
     */
    @Override
    public Point2D getCenter() {
       return new Point2D(this.X, this.Y);
    }

    /**
     * @param time
     */
    @Override
    public void affect(float time) {

    }
}
