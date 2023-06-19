package it.tbt.model.command.explore;

import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.PauseTrigger;

/**
 * Command for triggering a Pause GameState.
 */
public class CommandPause implements Command {
    private PauseTrigger pauseTrigger;

    /**
     * @param pauseTrigger the pause trigger which will be used to trigger the Pause GameState
     */
    public CommandPause(final PauseTrigger pauseTrigger) {
        this.pauseTrigger = pauseTrigger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        pauseTrigger.triggerPause();
    }

}
