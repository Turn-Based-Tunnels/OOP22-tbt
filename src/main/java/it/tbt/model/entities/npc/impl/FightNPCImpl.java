package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

public class FightNPCImpl extends AbstractNPCImpl implements FightNPC, StateTrigger {
    public FightNPCImpl(String name, int x, int y, int height, int width, FightModel fightModel) {
        super(name, x, y, height, width);
        this.fightModel = fightModel;
    }

    private FightModel fightModel;

    private StateObserver stateObserver;

    @Override
    public void onInteraction(SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            fightModel.initializeParty((IParty) interactable);
            this.stateObserver.onFight(fightModel);
        }
    }

    @Override
    public FightModel getFightModel() {
        return fightModel;
    }

    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
