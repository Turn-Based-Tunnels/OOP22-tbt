package it.tbt.model.world.interaction;

import it.tbt.model.entities.SpatialEntity;

public interface Interactable {
    void OnInteraction(SpatialEntity interactable);
}
