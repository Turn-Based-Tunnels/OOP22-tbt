package it.tbt.model.items;

/**
 * Generic Potion.
 */
public class Potion extends Consumable {
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
