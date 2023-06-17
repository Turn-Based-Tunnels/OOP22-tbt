package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.world.interaction.Interactable;

public interface NPC extends SpatialEntity, Interactable {
    public String getName();
}
