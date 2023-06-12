package it.tbt.model.World.collision;

import it.tbt.model.entities.SpatialEntity;

public interface CollisionDetector {
    public Boolean checkCollision(final SpatialEntity sp1, final SpatialEntity sp2);
}
