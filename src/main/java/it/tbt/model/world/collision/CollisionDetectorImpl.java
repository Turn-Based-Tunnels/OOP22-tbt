package it.tbt.model.world.collision;

import it.tbt.model.entities.SpatialEntity;

public class CollisionDetectorImpl implements CollisionDetector{
    /**
     * @param sp1
     * @param sp2
     * @return
     */
    @Override
    public Boolean checkCollision(SpatialEntity sp1, SpatialEntity sp2) {
        return sp1.getX()==sp2.getX() && sp1.getY()==sp2.getY();
    }
}
