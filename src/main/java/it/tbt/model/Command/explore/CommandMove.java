package it.tbt.model.Command.explore;

import it.tbt.model.Command.api.Command;
import it.tbt.model.Party.IParty;

public class CommandMove implements Command {

    private IParty party;
    private int xVar;
    private int yVar;
    public CommandMove(IParty party, int xVar, int yVar) {
        this.party = party;
        this.xVar = xVar;
        this.yVar = yVar;
    }

    /**
     *
     */
    @Override
    public void execute() {
        this.party.move(this.xVar, this.yVar);
    }
}
