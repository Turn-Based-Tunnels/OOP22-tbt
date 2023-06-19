package it.tbt.commons.resourceloader.world.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import it.tbt.commons.resourceloader.world.api.WorldCreationStrategy;
import it.tbt.model.EndEntity;
import it.tbt.model.entities.items.Armor;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.Weapon;
import it.tbt.model.entities.items.factories.AntidoteFactory;
import it.tbt.model.entities.items.factories.ArmorFactory;
import it.tbt.model.entities.items.factories.PotionFactory;
import it.tbt.model.entities.items.factories.WeaponFactory;
import it.tbt.model.entities.npc.impl.NPCFactory;
import it.tbt.model.fight.impl.FightModelImpl;
import it.tbt.model.roomLink.RoomLink;
import it.tbt.model.roomLink.RoomLinkImpl;
import it.tbt.model.shop.Shop;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.api.World;
import it.tbt.model.world.impl.RoomImpl;
import it.tbt.model.world.impl.WorldImpl;

/**
 * Hard coded default World Setup.
 */

public class WorldCreationDefault implements WorldCreationStrategy {
    private final Random rnd = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public World createWorld() {
        World w = new WorldImpl();
        Room startRoom = new RoomImpl("RoomStart", RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        Room endRoom = new RoomImpl("EndRoom", RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        endRoom.addEntity(new EndEntity("EntityEnd", 200,200,30,30));
        Map<Item, Double> drops = new HashMap<>();
        drops.put(new Potion("Potion", 3, 50), 0.5);
        startRoom.addEntity(NPCFactory.createFightNPC("Roberto", 25, 25, 50, 50, new FightModelImpl(5, drops)));
        // shop
        Room shopRoom = new RoomImpl("ShopRoom", RoomImpl.DEFAULT_WIDTH_ROOM, RoomImpl.DEFAULT_HEIGHT_ROOM);
        Map<Item, Integer> shopItems = new HashMap<>();
        for (final Item item : PotionFactory.getInstance().getItems()) {
            shopItems.put(item, 10); // 10 of each potions
        }
        shopItems.put(AntidoteFactory.getInstance().getAntidote(), 10);
        final Set<Weapon> weapons = WeaponFactory.getInstance().getItems();
        shopItems.put(weapons.stream().skip(rnd.nextInt(weapons.size()-1)).findFirst().get(), 1); // a random weapon
        shopItems.put(weapons.stream().skip(rnd.nextInt(weapons.size()-1)).findFirst().get(), 1); // a random weapon
        final Set<Armor> armors = ArmorFactory.getInstance().getItems();
        shopItems.put(armors.stream().skip(rnd.nextInt(armors.size()-1)).findFirst().get(), 1); // a random weapon
        shopItems.put(armors.stream().skip(rnd.nextInt(armors.size()-1)).findFirst().get(), 1); // a random weapon
		int wallet = 10000;
        shopRoom.addEntity(NPCFactory.createShopNPC("Merchant", 50, 50, 75, 75, new Shop(shopItems, wallet)));
        // Room links
        RoomLink roomLink1 = new RoomLinkImpl("link", 200, 200, 75, 75, startRoom, shopRoom);
        startRoom.addEntity(roomLink1);
        RoomLink roomLink2 = new RoomLinkImpl("link2", 150, 150, 75, 75, shopRoom, endRoom);
        shopRoom.addEntity(roomLink2);
        RoomLink roomLink3 = new RoomLinkImpl("link3", 150, 150, 50, 50, startRoom, endRoom);
        endRoom.addEntity(roomLink3);
        w.addRoom(startRoom);
        w.addRoom(shopRoom);
        w.addRoom(endRoom);
        w.setStartRoom(startRoom);
        return w;
    }
}
