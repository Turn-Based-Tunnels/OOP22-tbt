package it.tbt.controller.viewcontrollermanager.impl;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

/**
 * ViewController for the shop.
 */
public class ShopController implements ViewController {
    private List<Command> commands;
    private final ShopState shopState;

    /**
     * Default constructor.
     * @param shopState
     */
    public ShopController(final ShopState shopState) {
        this.shopState = shopState;
        commands = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        shopState.previousElement();
                    }
                });
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        shopState.nextElement();
                    }
                });
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        shopState.toggleList();
                    }
                });
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
            this.commands.add(new Command() {
                @Override
                public void execute() {
                    shopState.execute();
                }
            });
                break;
            case KeyEvent.VK_ESCAPE:
                this.commands.add(new Command() {
                    @Override
                    public void execute() {
                        shopState.goToExplore();
                    }
                });
                break;
            default:
                // ignore input
                break;
        }
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
        this.commands = new LinkedList<>();
    }
}
