package it.tbt.controller.modelmanager;

import it.tbt.model.menu.api.MenuItem;

import java.util.List;

public interface MenuState extends ModelState{
    public void NextElement();
    public void PreviousElement();

    public List<MenuItem> getItems();

    public int getFocus();

    public void toExplore();
    public String getTitle();
}
