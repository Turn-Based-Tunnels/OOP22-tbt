package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

import java.util.ArrayList;
import java.util.List;

public class EndViewController implements ViewController {

    private List<Command> commands;
    private final EndState endState;

    public EndViewController(EndState endState){
       this.endState=endState;
       commands = new ArrayList<> ();
    }

    @Override
    public void onKeyPressed (int key) {
        endState.toMainMenu ();
    }

    @Override
    public List<Command> getCommands () {
        return this.commands;
    }

    @Override
    public void clean () {
        commands.clear ();
    }
}
