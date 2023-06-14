package it.tbt.model.menu.impl;

import it.tbt.model.command.menu.ButtonCommand;

import java.util.Map;
import java.util.NavigableMap;

import static java.awt.event.KeyEvent.*;

public abstract class MenuSelect<I> extends MenuItem implements it.tbt.model.menu.api.MenuSelect {

    private NavigableMap<String, I> options;
    private String selectedOptionIndex;

    @Override
    public  void setSelectedOptionIndex(String key){
        this.selectedOptionIndex = String.copyValueOf(key.toCharArray());
    }

    @Override
    public String getSelectedOptionIndex(){
        return this.selectedOptionIndex;
    }


    public MenuSelect(String text, NavigableMap<String, I> options) {
        super(text);
        this.options = options;
        selectedOptionIndex = options.keySet().iterator().next();
    }

    @Override
    public NavigableMap<String, I> getOptions() {
        return (options);
    }

    @Override
    public String getLabel() {
        return selectedOptionIndex;
    }

    public ButtonCommand nextOption(){
        return new ButtonCommand() {
            @Override
            public void execute() {

            }
        };
    }

    public ButtonCommand previousOption(){
        return new ButtonCommand() {
            @Override
            public void execute() {

            }
        };
    }


}