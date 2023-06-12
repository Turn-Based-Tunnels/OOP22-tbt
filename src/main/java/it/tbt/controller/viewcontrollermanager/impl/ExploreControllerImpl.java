package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ExploreController;
import it.tbt.model.command.api.Command;
import it.tbt.model.command.explore.CommandInteract;
import it.tbt.model.command.explore.CommandMove;
import it.tbt.model.world.interaction.InteractionTrigger;

import java.util.LinkedList;
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

    /**
     *
     */
    @Override
    public void interactWithProximity() {
        if(this.modelState.getParty() instanceof InteractionTrigger) {
            this.commands.add(new CommandInteract((InteractionTrigger) this.modelState.getParty()));
        }

    }

}
