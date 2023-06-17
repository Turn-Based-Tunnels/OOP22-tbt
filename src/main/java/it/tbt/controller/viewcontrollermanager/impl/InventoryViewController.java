package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuSelect;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class InventoryViewController implements ViewController {

    List<Command> commands = new ArrayList<>();
    private InventoryState inventoryState;
    public InventoryViewController(final InventoryState inventoryState){
        this.inventoryState = inventoryState;
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
                        inventoryState.previousElement();
                    }
                });
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        inventoryState.nextElement();
                    }
                });
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        inventoryState.performAction();
                    }
                });
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: this.commands.add(new Command() {
                @Override
                public void execute() {
                    inventoryState.nextPhase();
                }
            });
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        inventoryState.previousPhase();
                    }
                });
                break;
            case KeyEvent.VK_ESCAPE:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        inventoryState.switchToExplore();
                    }
                });
                break;
        }
    }
    @Override
    public List<Command> getCommands() {
        return this.commands;
    }
    @Override
    public void clean() {
        this.commands.clear();
    }
}
