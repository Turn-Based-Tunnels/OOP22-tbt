package it.tbt.model.World.interaction;

import it.tbt.model.entities.SpatialEntity;

public interface Interactable {
    void OnInteraction(SpatialEntity interactable);
}
