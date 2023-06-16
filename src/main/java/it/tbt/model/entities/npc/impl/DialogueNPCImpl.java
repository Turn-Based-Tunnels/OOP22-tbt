package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.DialogueNPC;

public class DialogueNPCImpl extends AbstractNPCImpl implements DialogueNPC {

    private String dialogue;
    public DialogueNPCImpl(String name, String dialogue) {
        super(name);
        this.dialogue=dialogue;
    }

    @Override
    public String getDialogue() {
        return this.dialogue;
    }

    @Override
    public void OnInteraction(SpatialEntity interactable) {

    }
}
