package it.tbt.model.menu.impl;

public abstract class MenuItem implements it.tbt.model.menu.api.MenuItem {
    private String text;


    @Override
    public String getText() {
        return text;
    }

    public  MenuItem(String text){
        this.text = text;
    }

}
