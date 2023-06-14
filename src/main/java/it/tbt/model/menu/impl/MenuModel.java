package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class MenuModel {
    private List<MenuItem> items;
    private int focus = 0;
    public MenuModel(List<it.tbt.model.menu.api.MenuItem> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);

    }

    public int getFocus(){
        return focus;
    }

    public void setFocus(int focus){
        this.focus = focus;
    }

    public List<MenuItem> getItems() {
        return items;
    }

}
