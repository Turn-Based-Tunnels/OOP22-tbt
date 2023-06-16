package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.party.IParty;
import it.tbt.model.world.interaction.Interactable;

import java.util.Map;

public class HealerNPCImpl extends AbstractNPCImpl implements it.tbt.model.entities.npc.api.HealerNPC, Interactable {
    private int healAmount;

    public HealerNPCImpl(String name, String dialogue, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    public  int getHealAmount(){
        return healAmount;
    }

    @Override
    public void OnInteraction(SpatialEntity interactable) {
        if(interactable instanceof IParty){
            for (Ally a:((IParty)interactable).getMembers()
                 ) {
                if(a.getHealth()+this.getHealAmount() <= a.getMaxHealth()){
                    a.setHealth(a.getHealth()+this.getHealAmount());
                }else{
                    a.setHealth(a.getMaxHealth());
                }
            }

        }
    }
}
