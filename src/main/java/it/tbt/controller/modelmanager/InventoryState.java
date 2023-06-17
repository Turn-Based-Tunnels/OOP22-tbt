package it.tbt.controller.modelmanager;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;

import java.util.List;
import java.util.Map;

public interface InventoryState extends ModelState {
    public List<Ally> getPartyMembers() ;

    public InventoryPhase getPhase();
    public Map<Item, Integer> getInventory();
    public void previousElement();
    public void nextElement();
    public  void performAction();
    public void nextPhase();
    public int getPartySize();
    public void previousPhase();
    public void switchToExplore();
    public int getItemSelected();
    public int getItemFocus();
    public int getAllySelected();
    public int getAllyFocused();
}
