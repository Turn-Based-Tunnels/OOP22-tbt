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
        double x1 = sp1.getX() - sp1.getWidth() / 2;
        double y1 = sp1.getY() - sp1.getHeight() / 2;
        double x2 = sp1.getX() + sp1.getWidth() / 2;
        double y2 = sp1.getY() + sp1.getHeight() / 2;

        double x3 = sp2.getX() - sp2.getWidth() / 2;
        double y3 = sp2.getY() - sp2.getHeight() / 2;
        double x4 = sp2.getX() + sp2.getWidth() / 2;
        double y4 = sp2.getY() + sp2.getHeight() / 2;
        return (x1 <= x4 && x2 >= x3 && y1 <= y4 && y2 >= y3) || (x1 >= x3 && x2 <= x4 && y1 >= y3 && y2 <= y4);
    }
}
