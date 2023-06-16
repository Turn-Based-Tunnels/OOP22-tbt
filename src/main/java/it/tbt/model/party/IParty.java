package it.tbt.model.party;

import java.util.List;
import java.util.Map;

import it.tbt.model.entities.MovableEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import it.tbt.model.world.api.Room;
import javafx.util.Pair;

/**
 * Generic Party.
 */
public interface IParty extends MovableEntity {

    /**
     * Set current room.
     * @param room
     */
    void setCurrentRoom(Room room);

    /**
     * Get current room.
     * @return room
     */
    Room getCurrentRoom();

    /**
     * Move party.
     * @param xv
     * @param yv
     */
    void move(int xv, int yv);

    /**
     * Get the party members.
     * @return list of allies
     */
    List<Ally> getMembers();

    /**
     * Get the current amount of cash available to the party.
     * @return available cash
     */
    int getWallet();

    /**
     * Add/subtract the given amount to the wallet.
     * @param amount
     */
    void addCash(int amount);

    /**
     * Get the character inventory.
     * @return map of <item, count> representing the character's intentory
     */
    Map<Item, Integer> getInventory();

    /**
     * Add an item to the inventory.
     * @param item
     */
    void addItemToInventory(Item item);

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    boolean removeItemFromInventory(Item item);

    /**
     * Return the stored dialogue.
     * @return Pair < Speaker, Content >
     */
    public Pair<String, String> getDialogue();

    /**
     * Set the stored dialogue.
     * @param  dialogue Pair < Speaker, Content >
     */
    public void setDialogue(Pair<String, String> dialogue);
}
