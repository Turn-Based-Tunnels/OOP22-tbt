package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.ArrayList;
import java.util.List;

public class MenuQuitToTitleButton extends  MenuButton implements StateTrigger {

    List<StateObserver> observers = new ArrayList<>();
    private StateObserver stateObserver;

    public MenuQuitToTitleButton(String text) {
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

    /**
     * @param stateObserver
     */
    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
