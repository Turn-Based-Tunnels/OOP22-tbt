package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.Map;

public class FightNPCImpl extends AbstractNPCImpl implements FightNPC, StateTrigger {
    public FightNPCImpl(String name, int x, int y ,int height, int width, Object fightModel) {
        super(name, x, y, height, width);
        this.fightModel = fightModel;
    }

    private Object fightModel;

    private StateObserver stateObserver;
    @Override
    public void OnInteraction(SpatialEntity interactable) {
        /*todo*/
    }
    @Override
    public Object getFightModel(){
        return fightModel;
    }

    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
