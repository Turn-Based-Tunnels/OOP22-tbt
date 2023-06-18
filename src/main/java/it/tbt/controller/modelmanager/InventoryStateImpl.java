package it.tbt.controller.modelmanager;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Status;
import it.tbt.model.entities.items.*;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static it.tbt.model.party.IParty.PARTY_SIZE;

public class InventoryStateImpl implements InventoryState, StateTrigger {
    private static final int NOT_SELECTED = -1;
    private IParty party;
    private InventoryPhase phase;
    private boolean itemLocked;
    private int inventoryCounter;
    private int membersCounter;
    private int memberSelected;
    private int itemCounter;
    private int itemSelected;
    private StateObserver stateObserver;
    public InventoryStateImpl(IParty party){
        this.party = party;
        this.phase = InventoryPhase.INVENTORY;
        itemLocked = false;
        itemCounter=0;
        membersCounter=0;
        itemSelected=NOT_SELECTED;
        memberSelected=NOT_SELECTED;
        if(party.getInventory().size() == 0){
            this.phase = InventoryPhase.MEMBERS;
            itemLocked=true;
        }
    }
    @Override
    public List<Ally> getPartyMembers() {
        return this.party.getMembers();
    }

    @Override
    public InventoryPhase getPhase() {
        return phase;
    }

    @Override
    public Map<Item, Integer> getInventory() {
        return this.party.getInventory();
    }

    @Override
    public void previousElement() {
        switch (this.getPhase()){
            case INVENTORY -> {
                if(this.itemSelected ==  NOT_SELECTED){
                    this.itemCounter = (this.itemCounter-1)<0?this.party.getInventory().size()-1:this.itemCounter-1;

                }else{
                    this.membersCounter = (this.membersCounter-1)<0?this.party.getMembers().size()-1:this.membersCounter-1;
                }
            }
            case MEMBERS -> {
                this.membersCounter = (this.membersCounter-1)<0?this.party.getMembers().size()-1:this.membersCounter-1;
            }
        }

    }

    @Override
    public void nextElement() {
        switch (this.getPhase()){
            case INVENTORY -> {
                if(this.itemSelected ==  NOT_SELECTED){
                    this.itemCounter = (this.itemCounter+1)%this.getInventory().size();
                }else {
                    this.membersCounter = (this.membersCounter+1)%this.getPartyMembers().size();
                }

            }
            case MEMBERS -> {
                this.membersCounter = (this.membersCounter+1)%this.getPartyMembers().size();

            }
        }
    }

    @Override
    public void performAction() {
        switch (this.getPhase()){
            case INVENTORY -> {
                if(this.itemSelected == NOT_SELECTED){
                    this.itemSelected = this.itemCounter;
                }else{
                    List<Item> items = new ArrayList<>(this.getInventory().keySet());
                    if(items.get(this.itemSelected) instanceof Consumable){
                        if(items.get(this.itemSelected) instanceof Potion){
                            if(this.getPartyMembers().get(this.membersCounter).getMaxHealth() != this.getPartyMembers().get(this.membersCounter).getHealth()){
                                this.getPartyMembers().get(this.membersCounter).setHealth(this.getPartyMembers().get(this.membersCounter).getHealth()+((Potion)items.get(this.itemSelected)).getHealPower());
                                if(this.getPartyMembers().get(this.membersCounter).getHealth() > this.getPartyMembers().get(this.membersCounter).getMaxHealth()){
                                    this.getPartyMembers().get(this.membersCounter).setHealth(this.getPartyMembers().get(this.membersCounter).getMaxHealth());
                                }
                                party.removeItemFromInventory(items.get(this.itemSelected));
                            }


                        }if(items.get(this.itemSelected) instanceof Antidote){
                            if(this.getPartyMembers().get(this.membersCounter).removeStatus(Status.POISONED)){
                                party.removeItemFromInventory(items.get(this.itemSelected));
                            }

                        }
                    }
                    if(items.get(this.itemSelected) instanceof Equipement){
                        if(items.get(this.itemSelected) instanceof Armor){
                            Armor old = null;
                            if(this.getPartyMembers().get(this.membersCounter).getArmor().isPresent()){
                                old = this.getPartyMembers().get(this.membersCounter).getArmor().get();
                            }
                            this.getPartyMembers().get(this.membersCounter).equipeArmor((Armor) items.get(this.itemSelected));
                            party.removeItemFromInventory(items.get(this.itemSelected));
                            if(old != null){
                                party.addItemToInventory(old);
                            }

                        }if(items.get(this.itemSelected) instanceof Weapon){
                            Weapon old = null;
                            if(this.getPartyMembers().get(this.membersCounter).getWeapon().isPresent()){
                                old = this.getPartyMembers().get(this.membersCounter).getWeapon().get();
                            }
                            this.getPartyMembers().get(this.membersCounter).equipeWeapon((Weapon) items.get(this.itemSelected));
                            party.removeItemFromInventory(items.get(this.itemSelected));
                            if(old != null){
                                party.addItemToInventory(old);
                            }

                        }
                    }
                    this.itemSelected = NOT_SELECTED;
                    this.itemCounter = 0;
                }

            }
            case MEMBERS -> {
                if(this.memberSelected == NOT_SELECTED){
                    this.memberSelected = this.membersCounter;
                }else{
                    if(this.memberSelected != this.membersCounter){
                        ArrayList<Ally> temp = new ArrayList<>(this.getPartyMembers());
                        Collections.swap(temp, this.membersCounter, this.memberSelected);
                        this.party.setMembers(temp);

                    }
                    this.memberSelected = NOT_SELECTED;
                }

            }
        }
    }
    @Override
    public void nextPhase(){
        if(this.itemSelected == NOT_SELECTED && this.memberSelected==NOT_SELECTED){
            this.phase = this.phase.next();
            if (itemLocked && this.phase == InventoryPhase.INVENTORY){
                this.phase= this.phase.next();
            }
        }

    }

    @Override
    public int getPartySize() {
        return PARTY_SIZE;
    }

    @Override
    public void previousPhase(){
        if(this.itemSelected == NOT_SELECTED && this.memberSelected==NOT_SELECTED){
            this.phase = this.phase.previous();
            if (itemLocked && this.phase == InventoryPhase.INVENTORY){
                this.phase= this.phase.previous();
            }
        }

    }

    @Override
    public void switchToExplore() {
        this.stateObserver.onExplore();
    }

    @Override
    public int getItemSelected() {
        return this.itemSelected;
    }

    @Override
    public int getItemFocus() {
        return this.itemCounter;
    }

    @Override
    public int getAllySelected() {
        return this.memberSelected;
    }

    @Override
    public int getAllyFocused() {
        return this.membersCounter;
    }


    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}