package it.tbt.control.menu.impl;

import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.impl.MenuAspectRateoSelect;
import it.tbt.model.menu.impl.MenuModel;

import java.util.*;

public class MenuFactory {
    public static MenuModel getMainMenu(){
        List<MenuItem> test = new ArrayList<>();
        NavigableMap<String, Double> rateos = new TreeMap<>();
        rateos.put("16:9", 16.0 / 9.0);
        rateos.put("4:3", 4.0 / 3.0);
        rateos.put("3:2", 3.0 / 2.0);
        test.addAll(Arrays.asList(new it.tbt.model.menu.impl.MenuNewGameButton("New Game"), new it.tbt.model.menu.impl.MenuQuitGameButton("Exit"), new MenuAspectRateoSelect("Proporzioni", rateos), new it.tbt.model.menu.impl.MenuFullScreenButton("Full Screen")));

        return  new MenuModel(test);
    }
}
