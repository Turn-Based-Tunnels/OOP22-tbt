package it.tbt.model.Party;

import it.tbt.model.Entities.AbstractEntity;
import it.tbt.model.Entities.Entity;
import it.tbt.model.Entities.SpatialEntity;
import it.tbt.model.World.api.Room;
import it.tbt.model.time.TimeAffected;

import java.util.HashSet;
import java.util.Set;

public class Party extends AbstractEntity implements TimeAffected, IParty {
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
        if(this.currentRoom.isValidCoordinates(xv+this.x, yv+this.y)) {
            this.x += xv;
            this.y += yv;
        }
    }

    /**
     * @param time
     */
    @Override
    public void affect(long time) {

    }
}
