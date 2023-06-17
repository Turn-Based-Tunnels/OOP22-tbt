package it.tbt.model.command.explore;

import it.tbt.model.command.api.Command;
import it.tbt.model.party.IParty;

/**
 * Command for moving the IParty.
 */
public final class CommandMove implements Command {
    private IParty party;
    private int xVar;
    private int yVar;

    /**
     * @param party
     * @param xVar
     * @param yVar
     */
    public CommandMove(final IParty party, final int xVar, final int yVar) {
        this.party = party;
        this.xVar = xVar;
        this.yVar = yVar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.party.move(this.xVar, this.yVar);
    }
}
