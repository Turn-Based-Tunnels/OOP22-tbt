package it.tbt.model.entities.items;

/**
 * Generic Potion.
 */
public class Potion extends ItemImpl implements Consumable {
    private final int healPower;

    /**
     * Default constructor.
     * @param value
     * @param healPower
     */
    public Potion(final int value, final int healPower) {
        super(value);
        this.healPower = healPower;
    }

    /**
     * Get how much the potion heal.
     * @return int
     */
    public int getHealPower() {
        return healPower;
    }
}
