package it.tbt.model.party;

import java.util.*;

import it.tbt.model.world.interaction.InteractionComponent;
import it.tbt.model.world.interaction.InteractionTrigger;
import it.tbt.model.world.interaction.PartyInteractionComponent;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.MovableEntityImpl;
import it.tbt.model.world.api.Room;
import it.tbt.model.statechange.ExploreStateTrigger;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.time.TimeAffected;

/**
 * Party implementation.
 */
public class Party extends MovableEntityImpl implements IParty, InteractionTrigger, ExploreStateTrigger, TimeAffected {
    private final Set<Ally> members;
    private Room currentRoom;
    private int wallet;
    private InteractionComponent interactionComponent = new PartyInteractionComponent(this);

    private List<StateObserver> stateObservers = new LinkedList<>();

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
     * Set current room.
     * @param room
     */
    @Override
    public void setCurrentRoom(final Room room) {
        this.currentRoom = room;
        notifyState();
    }

    /**
     * Get current room.
     * @return
     */
    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Move party.
     * @param xv
     * @param yv
     */
    @Override
    public void move(final int xv, final int yv) {
        if (this.currentRoom.isValidCoordinates(xv + getX(), yv + getY())) {
            setX(getX() + xv);
            setY(getY() + yv);
        }
    }

    /**
     * @param time
     */
    @Override
    public void affect(final long time) {

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

    /**
     * @return
     */
    @Override
    public InteractionComponent getInteractionComponent() {
        return this.interactionComponent;
    }

    /**
     *
     */
    @Override
    public void notifyState() {
        for(var x: stateObservers) {
            x.onExplore();
        }
    }

    /**
     * @param observer
     */
    @Override
    public void addStateObserver(StateObserver observer) {
        this.stateObservers.add(observer);
    }

    /**
     * @param observer
     */
    @Override
    public void removeStateObserver(StateObserver observer) {
        this.stateObservers.remove(observer);
    }
}