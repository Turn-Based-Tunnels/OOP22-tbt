package it.tbt.model.World.impl;

import it.tbt.model.World.api.Room;
import it.tbt.model.World.api.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorldImpl implements World {

    Set<Room> rooms = new HashSet<>();


    public WorldImpl(final Set<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * @param room
     */
    @Override
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    /**
     * @return
     */
    @Override
    public List<Room> getListRoom() {
        return this.rooms.stream().toList();
    }
}
