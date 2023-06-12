package it.tbt.controller.viewcontrollermanager.api;

import it.tbt.model.command.api.Command;
import java.util.List;

public interface ViewController {
    public void addCommand(final Command c);
    public List<Command> getCommands();

    public void clean();
}
