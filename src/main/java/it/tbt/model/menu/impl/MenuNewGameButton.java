package it.tbt.model.menu.impl;

import it.tbt.model.command.api.Command;
import it.tbt.model.command.menu.ButtonCommand;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.ArrayList;
import java.util.List;

public class MenuNewGameButton extends MenuButton implements StateTrigger {
    public MenuNewGameButton(String text) {
        super(text);
    }
    @Override
    public ButtonCommand getAction(){
        return new ButtonCommand() {
            @Override
            public void execute() {
                stateObserver.onExplore();
            }
        };
    }

    StateObserver stateObserver;

    /**
     * @param stateObserver
     */
    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
