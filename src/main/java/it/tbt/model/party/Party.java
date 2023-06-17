package it.tbt.model.party;

import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.interaction.InteractionComponent;
import it.tbt.model.world.interaction.InteractionTrigger;
import it.tbt.model.world.interaction.PartyInteractionComponent;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Inventory;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.MovableEntityImpl;
import it.tbt.model.world.api.Room;
import it.tbt.model.statechange.StateObserver;
import javafx.util.Pair;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

/**
 * Party implementation.
 */
public class Party extends MovableEntityImpl implements IParty, InteractionTrigger, StateTrigger {
    private final Set<Ally> members;
    private Room currentRoom;
    private int wallet;
    private final Inventory inventory;
    private StateObserver stateObserver;
    private final InteractionComponent interactionComponent = new PartyInteractionComponent(this);
    private Pair<String, String> dialogue;

    /**
     * Constructor without party members.
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Party(
        final String name,
        final int x,
        final int y,
        final int width,
        final int height
    ) {
        super(name, x, y, width, height);
        this.members = new HashSet<>();
        this.inventory = new Inventory();
    }

    /**
     * Constructor with Collection of allies.
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     * @param c
     */
    public Party(
        final String name,
        final int x,
        final int y,
        final int width,
        final int height,
        final Collection<Ally> c
    ) {
        super(name, x, y, width, height);
        this.members = new HashSet<>(c);
        this.inventory = new Inventory();
    }

    /**
     * Set current room.
     * @param room
     */
    @Override
    public void setCurrentRoom(final Room room) {
        this.currentRoom = room;
        this.stateObserver.onExplore();
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
        if (this.currentRoom.isValidCoordinates(xv + getX(), yv + getY(), getWidth(), getHeight())) {
            setX(getX() + xv);
            setY(getY() + yv);
        }
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
     * Add the given ally to the party.
     * @param ally
     * @return true if the ally has been added
     */
    @Override
    public boolean addMember(final Ally ally) {
        return members.add(ally);
    }

    /**
     * Remove the given ally from the party.
     * @param ally
     * @return true if the ally has been removed
     */
    @Override
    public boolean removeMember(final Ally ally) {
        return members.remove(ally);
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
     * Triggers the interaction logic of the interaction component.
     */
    @Override
    public void triggerInteraction() {
        this.interactionComponent.interactLogic();
    }

    /**
     * @param stateObserver
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
    /**
     * Get the character inventory.
     * @return map of <item, count> representing the character's intentory
     */
    @Override
    public Map<Item, Integer> getInventory() {
        return inventory.getItems();
    }

    /**
     * Add an item to the inventory.
     * @param item
     */
    @Override
    public void addItemToInventory(final Item item) {
        inventory.addItem(item);
    }

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    @Override
    public boolean removeItemFromInventory(final Item item) {
        return inventory.removeItem(item);
    }
    @Override
    public Pair<String, String> getDialogue() {
        return dialogue;
    }
    @Override
    public void setDialogue(Pair<String, String> dialogue) {
        this.dialogue = dialogue;
    }
}
