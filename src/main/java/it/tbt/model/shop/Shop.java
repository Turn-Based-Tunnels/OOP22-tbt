package it.tbt.model.shop;


import java.util.Map;
import java.util.Optional;

import it.tbt.model.entities.characters.Inventory;
import it.tbt.model.entities.items.Item;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

/**
 * Shop model class.
 */
public class Shop implements StateTrigger {
    private StateObserver stateObserver;
    private final IParty party;
    private final Inventory shopInventory;
    private int wallet;

    /**
     * Default constructor.
     * @param party
     * @param shopItems
     * @param wallet
     */
    public Shop(final IParty party, final Map<Item, Integer> shopItems, final int wallet) {
        this.party = party;
        this.shopInventory = new Inventory(shopItems);
        this.wallet = wallet;
    }

    /**
     * Check if the given map have an item with the given name.
     * @param name
     * @param map
     * @return an optional containing the item, if it has been found
     */
    private Optional<Item> mapContainsItem(final String name, final Map<Item, Integer> map) {
        for (final Item item : map.keySet()) {
            if (item.getName().equals(name)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    /**
     * Go back to the explore state.
     */
    public void toExplore(){
        this.stateObserver.onExplore();
    }

    /**
     * Return the party items.
     * @return the party items
     */
    public Map<Item, Integer> getPartyItems() {
        return party.getInventory();
    }

    /**
     * Return the party items.
     * @return the shop items
     */
    public Map<Item, Integer> getShopInventory() {
        return shopInventory.getItems();
    }

    /**
     * Shop sell to party.
     * @param name
     * @return true if the transaction fails
     */
    public boolean sell(final String name) {
        final Optional<Item> optItem = mapContainsItem(name, shopInventory.getItems());
        if (!optItem.isEmpty()) {
            final Item item = optItem.get();
            // check party wallet and move item
            if (party.getWallet() - item.getValue() > 0 && shopInventory.removeItem(item)) {
                party.addItemToInventory(item);
                //payment
                wallet = wallet + item.getValue();
                party.addCash(-item.getValue());
                return false;
            }
        }
        // item not found, party does not have enough money,
        // or the shop's inventory does not have the item
        return true;
    }

    /**
     * Shop buy from party.
     * @param name
     * @return true if the fransaction fails
     */
    public boolean buy(final String name) {
        Optional<Item> optItem = mapContainsItem(name, party.getInventory());
        if (!optItem.isEmpty()) {
            Item item = optItem.get();
            // check shop wallet and move item
            if (wallet - item.getValue() > 0 && party.removeItemFromInventory(item)) {
                shopInventory.addItem(item);
                wallet = wallet - item.getValue();
                party.addCash(item.getValue());
                return false;
            }
        }
        // item not found, shop does not have enough money,
        // or the party's inventory does not have the item
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}