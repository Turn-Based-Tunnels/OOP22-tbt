package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.npc.api.NPC;

public abstract class AbstractNPCImpl implements NPC {

    private String name;
    private int x;
    private int y;
    private int height;
    private int width;

    public AbstractNPCImpl(String name, int x, int y, int height, int width){
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public String getName() {
        return name;
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
