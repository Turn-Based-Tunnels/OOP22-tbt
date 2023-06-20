package it.tbt.model.menu.impl;

import it.tbt.model.fight.api.FightModel;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.shop.Shop;
import it.tbt.model.statechange.StateObserver;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MenuModelImplTest {

    @Test
    void testGetTitle() {
        // Create a sample list of menu items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton("Item 1"));
        items.add(new MenuQuitGameButton("Item 2"));

        // Create a MenuModelImpl instance
        MenuModelImpl menuModel = new MenuModelImpl("Test Menu", items);

        // Assert that the title is correct
        assertEquals("Test Menu", menuModel.getTitle());
        assertEquals(items, menuModel.getItems());
    }

    @Test
    void testGetFocus() {
        // Create a sample list of menu items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton("Item 1"));
        items.add(new MenuQuitGameButton("Item 2"));

        // Create a MenuModelImpl instance
        MenuModelImpl menuModel = new MenuModelImpl("Test Menu", items);

        // Assert that the initial focus is 0
        assertEquals(0, menuModel.getFocus());

        // Set the focus to a different value
        menuModel.setFocus(1);

        // Assert that the focus has been updated
        assertEquals(1, menuModel.getFocus());
    }

    @Test
    void testGetItems() {
        // Create a sample list of menu items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton("Item 1"));
        items.add(new MenuQuitGameButton("Item 2"));


        // Create a MenuModelImpl instance
        MenuModelImpl menuModel = new MenuModelImpl("Test Menu", items);

        // Get the menu items from the menu model
        List<MenuItem> retrievedItems = menuModel.getItems();

        // Assert that the retrieved items match the original items
        assertIterableEquals(items, retrievedItems);

    }

    @Test
    void testToExplore() {
        // Create a sample list of menu items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton("Item 1"));
        items.add(new MenuQuitGameButton("Item 2"));

        // Create a MenuModelImpl instance
        MenuModelImpl menuModel = new MenuModelImpl("Test Menu", items);

        // Create a mock StateObserver
        StateObserverMock observerMock = new StateObserverMock();

        // Set the mock StateObserver on the menu model
        menuModel.setStateObserver(observerMock);

        // Call the toExplore() method
        menuModel.toExplore();

        // Assert that the onExplore() method of the mock observer has been called
        assertTrue(observerMock.isExploreCalled());
    }

    // A mock implementation of the StateObserver interface for testing purposes
    private static class StateObserverMock implements StateObserver {
        private boolean exploreCalled = false;

        @Override
        public void onExplore() {
            exploreCalled = true;
        }

        @Override
        public void onMenu() {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onFight(final FightModel fightModel) {

        }

        @Override
        public void onInventory() {

        }

        @Override
        public void onShop(final Shop shop) {

        }

        @Override
        public void onEnding(final String message) {

        }

        public boolean isExploreCalled() {
            return exploreCalled;
        }
    }
}
