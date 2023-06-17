package it.tbt.controller.modelmanager;

public enum InventoryPhase {
    INVENTORY,
    MEMBERS;

    private static final InventoryPhase[] vals = values();


    public InventoryPhase next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public InventoryPhase previous() {
        return vals[(Math.abs(this.ordinal() - 1) % vals.length)];
    }
}
