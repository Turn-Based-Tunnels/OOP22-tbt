package it.tbt.controller.modelmanager;

import it.tbt.model.menu.impl.MenuModelImpl;

import java.util.List;

/**
 * The {@code MenuStateImpl} class represents the implementation of the {@link MenuState} interface.
 * It manages the state of a menu in the application's controller.
 */
public class MenuStateImpl implements MenuState {
    private final MenuModelImpl menuModel;

    /**
     * Constructs a new {@code MenuStateImpl} with the specified menu model.
     *
     * @param menu the menu model
     */
    public MenuStateImpl(final MenuModelImpl menu) {
        this.menuModel = menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void NextElement() {
        menuModel.setFocus((menuModel.getFocus() + 1) % menuModel.getItems().size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void PreviousElement() {
        menuModel.setFocus((menuModel.getFocus() - 1) < 0 ? menuModel.getItems().size() - 1 : menuModel.getFocus() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<it.tbt.model.menu.api.MenuItem> getItems() {
        return menuModel.getItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFocus() {
        return menuModel.getFocus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerExplore() {
        menuModel.toExplore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return menuModel.getTitle();
    }
}