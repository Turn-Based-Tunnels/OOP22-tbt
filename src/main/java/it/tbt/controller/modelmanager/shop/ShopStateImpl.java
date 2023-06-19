package it.tbt.controller.modelmanager.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.tbt.model.entities.items.Item;
import it.tbt.model.shop.Shop;

/**
 * Shop state class.
 */
public class ShopStateImpl implements ShopState {
    private final Shop shop;
    private int focusParty;
    private int focusShop;
    private boolean selector; // true = party
    private final List<ShopItem> partyItems;
    private final List<ShopItem> shopItems;

    /**
     * Default constructor.
     * @param shop
     */
    public ShopStateImpl(final Shop shop) {
        this.shop = shop;
        partyItems = new ArrayList<>(
            shop.getPartyItems().entrySet().stream().map(
                (Map.Entry<Item, Integer> item) -> {
                    return new ShopItem(
                        item.getKey().getName(),
                        item.getValue(),
                        item.getKey().getValue()
                    );

                }
            ).collect(Collectors.toList())
        );
        shopItems = new ArrayList<>(
            shop.getShopInventory().entrySet().stream().map(
                (Map.Entry<Item, Integer> item) -> {
                    return new ShopItem(
                        item.getKey().getName(),
                        item.getValue(),
                        item.getKey().getValue()
                    );

                }
            ).collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextElement() {
        if (selector) {
            focusParty = (focusParty + 1) % partyItems.size();
        } else {
            focusShop = (focusShop + 1) % shopItems.size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void previousElement() {
        if (selector) {
            focusParty = (focusParty - 1) < 0 ? partyItems.size() - 1 : focusParty - 1;
        } else {
            focusShop = (focusShop - 1) < 0 ? shopItems.size() - 1 : focusShop - 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPartyFocus() {
        return focusParty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShopFocus() {
        return focusShop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPartyListFocused() {
        return selector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToExplore() {
        shop.goToExplore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPartyItems() {
        return List.copyOf(partyItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getShopItems() {
        return List.copyOf(shopItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPartyWallet() {
        return shop.getPartyWallet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShopWallet() {
        return shop.getShopWallet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if (selector) {
            // party buy from shop
            if (partyItems.get(focusParty).getCount() > 0) {
                final ShopItem removedItem = partyItems.remove(focusShop);
                shop.buy(removedItem.getName());
                if (shopItems.contains(removedItem)) {
                    shopItems.get(focusShop).incCount();
                } else {
                    shopItems.add(new ShopItem(removedItem));
                }
                partyItems.get(focusParty).decCount();
                if (partyItems.get(focusParty).getCount() <= 0) {
                    partyItems.remove(focusParty);
                }
            }
        } else {
            // party sell to shop
            if (shopItems.get(focusShop).getCount() > 0) {
                final ShopItem removedItem = shopItems.remove(focusShop);
                shop.buy(removedItem.getName());
                if (partyItems.contains(removedItem)) {
                    partyItems.get(focusParty).incCount();
                } else {
                    partyItems.add(new ShopItem(removedItem));
                }
                shopItems.get(focusShop).decCount();
                if (shopItems.get(focusShop).getCount() <= 0) {
                    shopItems.remove(focusShop);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleList() {
        selector = !selector;
    }
}
