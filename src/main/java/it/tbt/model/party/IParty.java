package it.tbt.model.party;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.World.api.Room;

/**
 * Generic Party.
 */
public interface IParty {
    void setCurrentRoom(Room room);
    Room getCurrentRoom();
    void move(int xv, int yv);
    int getX();
    int getY();

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
