package it.tbt.controller.viewcontrollermanager.impl;

import java.util.ArrayList;
import java.util.List;

import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.viewcontrollermanager.api.FightController;
import it.tbt.model.command.api.Command;

import java.awt.event.KeyEvent;

public class FightControllerImpl implements FightController {

    private FightState model;
    private List<Command> commands = new ArrayList<>();

    public FightControllerImpl(FightState model) {
        this.model = model;
        this.clean();
    }

    @Override
    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    @Override
    public void clean() {
        commands.clear();
    }

    @Override
    public void onKeyPressed(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                // controller.handlePreviousTarget();
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handlePreviousTarget();
                    }

                });
                break;
            case KeyEvent.VK_RIGHT:
                // controller.handleNextTarget();
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleNextTarget();
                    }

                });
                break;
            case KeyEvent.VK_UP:
                // controller.handleCycleAction(true);
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(true);
                    }

                });
                break;
            case KeyEvent.VK_DOWN:
                // controller.handleCycleAction(false);
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(false);
                    }

                });
                break;
            case KeyEvent.VK_ENTER:
                // controller.handlePerformAction();
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handlePerformAction();
                    }

                });
                break;
            default:
                break;
        }
    }
}
