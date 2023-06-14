package it.tbt.model.menu.api;

import it.tbt.model.command.menu.ButtonCommand;

import java.util.Map;

public interface MenuSelect <I>{

    public Map<String, I> getOptions();
    public String getLabel();

    public ButtonCommand nextOption();

    public ButtonCommand previousOption();

    public  void setSelectedOptionIndex(String key);

    public String getSelectedOptionIndex();


}
