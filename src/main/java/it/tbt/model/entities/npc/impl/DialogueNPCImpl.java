package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.DialogueNPC;

public class DialogueNPCImpl extends AbstractNPCImpl implements DialogueNPC {

    private String dialogue;
    public DialogueNPCImpl(String name, int x, int y ,int height, int width, String dialogue) {
        super(name, x, y, height, width);
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
