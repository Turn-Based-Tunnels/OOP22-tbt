package it.tbt.controller.modelmanager;

import it.tbt.model.menu.impl.MenuItem;
import it.tbt.model.menu.impl.MenuModel;

import java.util.List;

public class MenuStateImpl implements MenuState{

    private MenuModel menuModel;

    public MenuStateImpl(final MenuModel menu){
        this.menuModel = menu;
    }

    public void NextElement(){
        menuModel.setFocus((menuModel.getFocus()+1)%menuModel.getItems().size() );
    }
    public void PreviousElement(){
        menuModel.setFocus(Math.abs((menuModel.getFocus()-1)%menuModel.getItems().size()));
    }

    public List<it.tbt.model.menu.api.MenuItem> getItems(){
        return menuModel.getItems();
    }

    public int getFocus(){
        return menuModel.getFocus();
    }
}
