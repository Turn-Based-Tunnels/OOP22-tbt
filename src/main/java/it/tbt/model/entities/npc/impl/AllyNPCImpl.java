package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.npc.api.AllyNPC;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

public class AllyNPCImpl extends AbstractNPCImpl implements AllyNPC, StateTrigger {

    StateObserver stateObserver;
    Ally ally;
    public AllyNPCImpl(String name, int x, int y, int height, int width, Ally ally){
        super(name, x, y, height, width);
        this.ally = ally;

    }

    @Override
    public Ally getAlly() {
        return this.ally;
    }

    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver= stateObserver;
    }

    @Override
    public void onInteraction(SpatialEntity interactionTrigger) {
        if (interactionTrigger instanceof IParty) {
            ((IParty)interactionTrigger).addMember(ally);
        }

    }
}
