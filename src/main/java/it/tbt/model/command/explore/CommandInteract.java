package it.tbt.model.command.explore;

import it.tbt.model.command.api.Command;
import it.tbt.model.world.interaction.InteractionTrigger;

public class CommandInteract implements Command {


    private InteractionTrigger interactionTrigger;

    public CommandInteract(final InteractionTrigger interactionTrigger) {
        this.interactionTrigger = interactionTrigger;
    }

    /**
     *
     */
    @Override
    public void execute() {
        this.interactionTrigger.getInteractionComponent().InteractLogic();
    }
}
