package it.tbt.model.menu.impl;

import java.util.NavigableMap;

/**
 * The {@code MenuSelect} class represents a selectable menu with options.
 * It extends the {@link MenuItem} class and implements the {@link it.tbt.model.menu.api.MenuSelect} interface.
 *
 * @param <I> the type of the menu options
 */
public abstract class MenuSelect<I> extends MenuItem implements it.tbt.model.menu.api.MenuSelect {

    private final NavigableMap<String, I> options;
    private String selectedOptionIndex;

    /**
     * Creates a new instance of {@code MenuSelect} with the specified text and options.
     *
     * @param text    the text of the menu
     * @param options the map of options
     */
    public MenuSelect(final String text, final NavigableMap<String, I> options) {
        super(text);
        this.options = options;
        selectedOptionIndex = options.keySet().iterator().next();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public  void setSelectedOptionIndex(final String key){
        this.selectedOptionIndex = String.copyValueOf(key.toCharArray());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedOptionIndex(){
        return this.selectedOptionIndex;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<String, I> getOptions() {
        return (options);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return selectedOptionIndex;
    }


}