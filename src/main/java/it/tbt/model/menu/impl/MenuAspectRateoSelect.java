package it.tbt.model.menu.impl;

import it.tbt.model.command.api.Command;
import it.tbt.model.command.menu.ButtonCommand;

import java.util.NavigableMap;

public class MenuAspectRateoSelect extends MenuSelect<Double> {
    public MenuAspectRateoSelect(String text, NavigableMap<String, Double> options) {
        super(text, options);
    }


    @Override
    public ButtonCommand nextOption() {
        return new ButtonCommand() {

            @Override
            public void execute() {
                setSelectedOptionIndex(getOptions().higherKey(getSelectedOptionIndex())==null? getOptions().firstKey() :  getOptions().higherKey(getSelectedOptionIndex()));
            }
        };
    }

    @Override
    public ButtonCommand previousOption() {
        return new ButtonCommand() {

            @Override
            public void execute() {
                setSelectedOptionIndex(getOptions().lowerKey(getSelectedOptionIndex())==null? getOptions().lastKey() :  getOptions().lowerKey(getSelectedOptionIndex()));
            }
        };
    }
}
