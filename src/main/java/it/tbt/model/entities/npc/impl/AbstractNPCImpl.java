package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.npc.api.NPC;

public abstract class AbstractNPCImpl implements NPC {

    private String name;

    public AbstractNPCImpl(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }
}
