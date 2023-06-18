package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuItem;

import java.util.*;

public class MenuFactory {
    public static MenuModel getMainMenu(){
        List<MenuItem> test = new ArrayList<>();

        test.addAll(Arrays.asList(new it.tbt.model.menu.impl.MenuNewGameButton("New Game"), new it.tbt.model.menu.impl.MenuQuitGameButton("Exit")));

        return  new MenuModel("Main Menu",test);
    }
    public static MenuModel getPauseMenu(){
        List<MenuItem> test = new ArrayList<>();
        test.addAll(Arrays.asList(new it.tbt.model.menu.impl.MenuNewGameButton("Continue"), new MenuQuitToTitleButton("Quit to Title Screen"),new it.tbt.model.menu.impl.MenuQuitGameButton("Exit To Desktop")));

        return  new MenuModel("Pause",test);
    }

}
