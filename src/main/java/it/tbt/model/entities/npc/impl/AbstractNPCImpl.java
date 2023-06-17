package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.EntityImpl;
import it.tbt.model.entities.npc.api.NPC;

public abstract class AbstractNPCImpl extends EntityImpl implements NPC{

    private int x;
    private int y;
    private int height;
    private int width;

    public AbstractNPCImpl(String name, int x, int y, int height, int width){
        super(name);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
