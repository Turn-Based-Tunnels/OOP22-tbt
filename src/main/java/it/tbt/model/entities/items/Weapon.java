package it.tbt.model.entities.items;

/**
 * Generic Weapon.
 */
public abstract class Weapon extends ItemImpl implements Equipement {
    private final int attack;

    /**
     * Default constructor.
     * @param attack
     * @param value
     */
    public Weapon(final int attack, final int value) {
        super(value);
        this.attack = attack;
    }

    /**
     * get the attack of the weapon.
     * @return int
     */
    public final int getAttack() {
        return attack;
    }
}
