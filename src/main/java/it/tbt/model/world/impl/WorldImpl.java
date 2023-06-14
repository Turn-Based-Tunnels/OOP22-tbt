package it.tbt.model.world.impl;

import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorldImpl implements World {

    Set<Room> rooms;
    private Room start;

    public WorldImpl(final Set<Room> rooms) {
        this.rooms = rooms;
    }

    public WorldImpl() { this.rooms = new HashSet<>(); }

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

    /**
     * @return
     */
    @Override
    public Room getStartRoom() {
        return this.start;
    }

    /**
     * @param room
     */
    @Override
    public void setStartRoom(final Room room) {
        this.start = room;
    }
}
