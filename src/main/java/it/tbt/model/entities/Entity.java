package it.tbt.model.entities;

public class Entity {

    private final String name;
    protected int x;
    protected int y;

    public Entity(final String name, final int x, final int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public final String getName() {
        return name;
    }

    public final int getY() {
        return y;
    }

    public final void setY(final int y) {
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final void setX(final int x) {
        this.x = x;
    }

}
