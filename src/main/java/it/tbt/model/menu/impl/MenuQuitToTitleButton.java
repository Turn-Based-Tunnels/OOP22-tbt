package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;
import it.tbt.model.statechange.MenuStateTrigger;
import it.tbt.model.statechange.StateObserver;

import java.util.ArrayList;
import java.util.List;

public class MenuQuitToTitleButton extends  MenuButton implements MenuStateTrigger {

    List<StateObserver> observers = new ArrayList<>();

    public MenuQuitToTitleButton(String text) {
        super(text);
    }


    @Override
    public ButtonCommand getAction(){
        return new ButtonCommand() {
            @Override
            public void execute() {
                notifyState();
            }
        };
    }

    @Override
    public void notifyState() {
        for (var o: observers
        ) {
            o.onMenu();
        }
    }

    @Override
    public void addStateObserver(StateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeStateObserver(StateObserver observer) {
        observers.remove(observer);
    }
}
