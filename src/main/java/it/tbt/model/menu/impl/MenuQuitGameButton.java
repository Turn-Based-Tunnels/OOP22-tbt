package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;

public class MenuQuitGameButton extends MenuButton{
    public MenuQuitGameButton(String text) {
        super(text);
    }
    @Override
    public ButtonCommand getAction(){
        return new ButtonCommand() {

            @Override
            public void execute() {
                System.exit(0);
            }
        };

    }
}