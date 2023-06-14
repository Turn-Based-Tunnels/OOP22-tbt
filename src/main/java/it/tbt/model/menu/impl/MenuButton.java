package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;

import static java.awt.event.KeyEvent.*;

public abstract class MenuButton extends MenuItem implements it.tbt.model.menu.api.MenuButton {



    public MenuButton(String text) {
        super(text);
    }

    @Override
    public ButtonCommand getAction() {
        return new ButtonCommand() {
            @Override
            public void execute() {

            }
        };
    }



}
