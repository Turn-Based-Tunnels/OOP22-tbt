package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.control.input.api.InputListener;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuStateImpl;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuSelect;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PauseMenuController implements ViewController, StateTrigger {

    private StateObserver stateObserver;
    private MenuStateImpl modelState;
    private List<Command> commands;

    public PauseMenuController(final MenuStateImpl menuStateImpl){
        this.modelState = menuStateImpl;
        this.clean();
    }

    @Override
    public void onKeyPressed(int key) {
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        modelState.PreviousElement();
                    }
                });
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        modelState.NextElement();
                    }
                });
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                if(modelState.getItems().get(modelState.getFocus()) instanceof MenuButton){
                    this.commands.add((Command) ((MenuButton) modelState.getItems().get(modelState.getFocus())).getAction());
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if(modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>){
                    this.commands.add((Command) ((MenuSelect) modelState.getItems().get(modelState.getFocus())).nextOption());
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if(modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>){
                    this.commands.add((Command)((MenuSelect) modelState.getItems().get(modelState.getFocus())).previousOption());
                }
                break;
            case KeyEvent.VK_ESCAPE:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        stateObserver.onExplore();
                    }
                });
                break;
        }

    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public void clean() {
        this.commands = new LinkedList<>();
    }
    /**
     * @param stateObserver
     */
    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
