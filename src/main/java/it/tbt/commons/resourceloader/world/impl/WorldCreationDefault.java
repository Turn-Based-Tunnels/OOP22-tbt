package it.tbt.commons.resourceloader.world.impl;

import java.util.HashMap;
import java.util.Map;

import it.tbt.commons.resourceloader.world.api.WorldCreationStrategy;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.npc.impl.NPCFactory;
import it.tbt.model.fight.impl.FightModelImpl;
import it.tbt.model.roomLink.RoomLink;
import it.tbt.model.roomLink.RoomLinkImpl;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;
import it.tbt.model.world.impl.RoomImpl;
import it.tbt.model.world.impl.WorldImpl;

/**
 * Hard coded default World Setup.
 */

public class WorldCreationDefault implements WorldCreationStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public World createWorld() {
        World w = new WorldImpl();
        Room startRoom = new RoomImpl("RoomStart");
        Room endRoom = new RoomImpl("EndRoom");
        Map<Item, Double> drops = new HashMap<>();
        drops.put(new Potion("Potion", 3, 50), 0.5);
        startRoom.addEntity(NPCFactory.createFightNPC("Roberto", 25, 25, 75, 75, new FightModelImpl(5, drops)));
        RoomLink roomLink1 = new RoomLinkImpl("link", 200, 200, 75, 75, startRoom, endRoom);
        startRoom.addEntity(roomLink1);
        RoomLink roomLink2 = new RoomLinkImpl("link2", 150, 150, 50, 50, startRoom, endRoom);
        endRoom.addEntity(roomLink2);
        w.addRoom(startRoom);
        w.addRoom(endRoom);
        w.setStartRoom(startRoom);
        return w;
    }
}
