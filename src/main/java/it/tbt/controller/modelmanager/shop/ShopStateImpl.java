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

    @Override
    public void nextElement() {
        if (selector) {
            focusParty = (focusParty + 1) % partyItems.size();
        } else {
            focusShop = (focusShop + 1) % shopItems.size();
        }
    }

    @Override
    public void previousElement() {
        if (selector) {
            focusParty = (focusParty - 1) < 0 ? partyItems.size() - 1 : focusParty - 1;
        } else {
            focusShop = (focusShop - 1) < 0 ? shopItems.size() - 1 : focusShop - 1;
        }
    }

    @Override
    public int getFocus() {
        return selector ? focusParty : focusShop;
    }

    @Override
    public void toExplore() {
        shop.toExplore();
    }

    @Override
    public List<ShopItem> getPartyItems() {
        return List.copyOf(partyItems);
    }

    @Override
    public List<ShopItem> getShopItems() {
        return List.copyOf(shopItems);
    }

    @Override
    public void execute() {
        if (selector) {
            // party sell to shop
            if(shopItems.get(focusParty).getCount() > 0) {
                final ShopItem removedItem = shopItems.remove(focusParty);
                shop.buy(removedItem.getName());
                if (partyItems.contains(removedItem)) {
                    partyItems.get(focusParty).incCount();
                } else {
                    partyItems.add(new ShopItem(removedItem));
                }
                shopItems.get(focusParty).decCount();
            }
        } else {
            // party buy from shop
            if(partyItems.get(focusParty).getCount() > 0) {
                final ShopItem removedItem = shopItems.remove(focusParty);
                shop.buy(removedItem.getName());
                if (shopItems.contains(removedItem)) {
                    shopItems.get(focusParty).incCount();
                } else {
                    shopItems.add(new ShopItem(removedItem));
                }
                partyItems.get(focusParty).decCount();
            }
        }
    }

    @Override
    public void toggleList() {
        selector = !selector;
    }
}
