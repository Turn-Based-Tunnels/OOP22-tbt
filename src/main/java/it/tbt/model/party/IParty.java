package it.tbt.model.party;

import java.util.List;

import it.tbt.model.entities.MovableEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.world.api.Room;

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
}
