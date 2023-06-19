package it.tbt.model.command.explore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

    //Justification: The Command pattern encapsulates the objects on which perform the operations.
    @SuppressFBWarnings("EI2")
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
