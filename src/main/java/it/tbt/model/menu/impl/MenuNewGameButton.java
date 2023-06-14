package it.tbt.model.menu.impl;

import it.tbt.model.command.api.Command;
import it.tbt.model.command.menu.ButtonCommand;
import it.tbt.model.statechange.ExploreStateTrigger;
import it.tbt.model.statechange.StateObserver;

import java.util.ArrayList;
import java.util.List;

public class MenuNewGameButton extends MenuButton implements ExploreStateTrigger {
    public MenuNewGameButton(String text) {
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
            o.onExplore();
        }
    }

    List<StateObserver> observers = new ArrayList<>();

    @Override
    public void addStateObserver(StateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeStateObserver(StateObserver observer) {
        observers.remove(observer);
    }
}
