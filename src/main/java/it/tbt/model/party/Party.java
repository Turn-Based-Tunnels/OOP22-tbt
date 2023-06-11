package it.tbt.model.party;

import java.util.Collection;
import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.Entity;
import it.tbt.model.World.api.Room;
import it.tbt.model.time.TimeAffected;

import java.util.HashSet;
import java.util.Set;

/**
 * Party implementation.
 */
public class Party extends Entity implements IParty, TimeAffected {
    private final Set<Ally> members;
    private Room currentRoom;
    private int wallet;

    /**
     * Constructor without party members.
     * @param name
     * @param x
     * @param y
     */
    public Party(final String name, final int x, final int y) {
        super(name, x, y);
        this.members = new HashSet<>();
    }

    /**
     * Constructor with Collection of allies.
     * @param name
     * @param x
     * @param y
     * @param c
     */
    public Party(final String name, final int x, final int y, final Collection<Ally> c) {
        super(name, x, y);
        this.members = new HashSet<>(c);
    }

    /**
     * @param room
     */
    @Override
    public void setCurrentRoom(final Room room) {
        this.currentRoom = room;
    }

    /**
     * @return
     */
    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * @param xv
     * @param yv
     */
    @Override
    public void move(final int xv, final int yv) {
        if(this.currentRoom.isValidCoordinates(xv + this.x, yv + this.y)) {
            this.x += xv;
            this.y += yv;
        }
    }

    /**
     * @param time
     */
    @Override
    public void affect(final float time) {

    }

    /**
     * Get the party members.
     * @return list of allies
     */
    @Override
    public List<Ally> getMembers() {
        return List.copyOf(members);
    }

    /**
     * Get the current amount of cash available to the party.
     * @return available cash
     */
    @Override
    public int getWallet() {
        return wallet;
    }

    /**
     * Add/subtract the given amount to the wallet.
     * @param amount
     */
    @Override
    public void addCash(final int amount) {
        wallet += amount;
    }
}
