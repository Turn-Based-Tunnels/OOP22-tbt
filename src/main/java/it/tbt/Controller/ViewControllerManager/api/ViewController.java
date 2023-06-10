package it.tbt.Controller.ViewControllerManager.api;

import it.tbt.Model.Command.api.Command;
import java.util.List;

public interface ViewController {
    public void addCommand(final Command c);
    public List<Command> getCommands();

    public void clean();
}
