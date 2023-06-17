package it.tbt.controller.modelmanager;

import it.tbt.model.menu.impl.MenuItem;
import it.tbt.model.menu.impl.MenuModel;

import java.util.List;

public class MenuStateImpl implements MenuState{

    private MenuModel menuModel;

    public MenuStateImpl(final MenuModel menu){
        this.menuModel = menu;
    }

    @Override
    public void NextElement(){
        menuModel.setFocus((menuModel.getFocus()+1)%menuModel.getItems().size() );
    }
    @Override
    public void PreviousElement(){
        menuModel.setFocus(Math.abs((menuModel.getFocus()-1)%menuModel.getItems().size()));
    }

    @Override
    public List<it.tbt.model.menu.api.MenuItem> getItems(){
        return menuModel.getItems();
    }

    @Override
    public int getFocus(){
        return menuModel.getFocus();
    }

    @Override
    public void toExplore() {
        menuModel.toExplore();
    }
}
