package it.tbt.model.World.api;

import java.util.List;

public interface World {
    void addRoom(Room room);
    List<Room> getListRoom();
}
