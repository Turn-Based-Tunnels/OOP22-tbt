package it.tbt.model.Command.explore;

import it.tbt.model.Command.api.Command;
import it.tbt.model.World.interaction.InteractionTrigger;

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
