package it.tbt.model.world.api;

import java.util.List;

public interface World {
    void addRoom(Room room);
    List<Room> getListRoom();
    Room getStartRoom();
    void setStartRoom(final Room room);
}
