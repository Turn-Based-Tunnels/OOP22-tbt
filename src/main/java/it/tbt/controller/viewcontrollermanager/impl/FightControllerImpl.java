package it.tbt.controller.viewcontrollermanager.impl;

import java.util.ArrayList;
import java.util.List;

import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.viewcontrollermanager.api.FightController;
import it.tbt.model.command.api.Command;

import java.awt.event.KeyEvent;

/**
 * Implementation of the {@link FightController} interface.
 * This class handles user input during a fight and triggers corresponding
 * actions in the fight model.
 */
public final class FightControllerImpl implements FightController {

    private final FightState model;
    private final List<Command> commands = new ArrayList<>();

    /**
     * Constructs a new instance of the {@link FightControllerImpl} class with the
     * specified {@link FightState}.
     *
     * @param model the {@link FightState} representing the fight state
     */
    public FightControllerImpl(final FightState model) {
        this.model = model;
        this.clean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clean() {
        commands.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                // controller.handlePreviousTarget();
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handlePreviousTarget();
                    }

                });
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                // controller.handleNextTarget();
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleNextTarget();
                    }

                });
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                // controller.handleCycleAction(true);
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(true);
                    }

                });
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                // controller.handleCycleAction(false);
                this.commands.add(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(false);
                    }

                });
                break;
            case KeyEvent.VK_E:
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
