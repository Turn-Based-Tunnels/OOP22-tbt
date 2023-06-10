package it.tbt.Controller.ViewControllerManager.impl;

import it.tbt.Controller.ModelManager.ExploreState;
import it.tbt.Controller.ViewControllerManager.api.ExploreController;
import it.tbt.Controller.ViewControllerManager.api.ViewController;
import it.tbt.Model.Command.api.Command;
import it.tbt.Model.Command.explore.CommandMove;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class ExploreControllerImpl implements ExploreController {

    private List<Command> commands;

    private ExploreState modelState;

    public ExploreControllerImpl(final ExploreState exploreStateImpl) {
        this.modelState = exploreStateImpl;
        this.clean();
    }

    @Override
    public void addCommand(Command c) {
        this.commands.add(c);
    }

    /**
     * @return
     */
    @Override
    public List<Command> getCommands() {
        return this.commands;
    }

    /**
     *
     */
    @Override
    public void clean() {
        this.commands = new LinkedList<>();
    }

    public void moveRight() {
        this.commands.add(new CommandMove(this.modelState.getParty(), 1,0));
    }

    /**
     *
     */
    @Override
    public void moveDown() {
        this.commands.add(new CommandMove(this.modelState.getParty(),0,1));
    }

    /**
     *
     */
    @Override
    public void moveUp() {
        this.commands.add(new CommandMove(this.modelState.getParty(), 0, -1));
    }

    /**
     *
     */
    @Override
    public void moveLeft() {
        this.commands.add(new CommandMove(this.modelState.getParty(), -1, 0));
    }

}
