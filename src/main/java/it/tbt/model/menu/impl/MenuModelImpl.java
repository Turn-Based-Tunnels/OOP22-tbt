package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.api.MenuModel;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MenuModelImpl} class represents a menu model containing a list of menu items.
 */
public class MenuModelImpl implements MenuModel, StateTrigger {
    private final List<MenuItem> items;
    private StateObserver stateObserver;
    private final String title;
    private int focus = 0;


    /**
     * Constructs a new {@code MenuModelImpl} with the specified title and items.
     *
     * @param title the title of the menu
     * @param items the list of menu items
     */
    public MenuModelImpl (final String title, final List<it.tbt.model.menu.api.MenuItem> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);
        this.title  =title;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String  getTitle(){
        return this.title;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getFocus(){
        return focus;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus(final int focus){
        this.focus = focus;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MenuItem> getItems() {
        return items;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void toExplore(){
        this.stateObserver.onExplore();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
