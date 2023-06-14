package it.tbt.commons.resourceloader.world.impl;

import it.tbt.commons.resourceloader.world.api.WorldCreationStrategy;
import it.tbt.model.roomLink.RoomLink;
import it.tbt.model.roomLink.RoomLinkImpl;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;
import it.tbt.model.world.impl.RoomImpl;
import it.tbt.model.world.impl.WorldImpl;

public class WorldCreationDefault implements WorldCreationStrategy {
    /**
     * @return
     */
    @Override
    public World createWorld() {
        World w = new WorldImpl();
        Room startRoom = new RoomImpl("RoomStart");
        Room endRoom = new RoomImpl("EndRoom");
        RoomLink roomLink1 = new RoomLinkImpl("link", 100, 100, 75, 75, startRoom, endRoom);
        startRoom.addEntity(roomLink1);
        RoomLink roomLink2 = new RoomLinkImpl("link2", 150, 150, 50, 50, startRoom, endRoom);
        endRoom.addEntity(roomLink2);
        w.addRoom(startRoom);
        w.addRoom(endRoom);
        w.setStartRoom(startRoom);
        return w;
    }
}
