package it.tbt.model.characters;

import java.util.Map;
import java.util.Set;
import java.util.EnumSet;
import java.util.Collections;
import java.util.Optional;

import it.tbt.model.Inventory;
import it.tbt.model.Status;
import it.tbt.model.items.Armor;
import it.tbt.model.items.Item;
import it.tbt.model.items.Weapon;

/**
 * Generic Character.
 */
public class CharacterImpl implements Character {
    private static final long serialVersionUID = 4416007599695354122L;
    private final String name;
    private final int maxHealth;
    private final int speed;
    private final Inventory inventory;
    private int health;
    private final int attack;
    private final Set<Status> statuses;
    private Optional<Weapon> weapon;
    private Optional<Armor> armor;

    /**
     * Default constructor.
     * @param name
     * @param health
     * @param attack
     * @param speed
     */
    protected CharacterImpl(
        final String name,
        final int health,
        final int attack,
        final int speed
    ) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.statuses = EnumSet.noneOf(Status.class);
        this.inventory = new Inventory();
        this.weapon = Optional.empty();
        this.armor = Optional.empty();
    }

    /**
     * Get the character's name.
     * @return character's name
     */
    @Override
    public final String getName() {
        return name;
    }

    /**
     * Get the current character's health.
     * @return current character's health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Get the maximum character's health.
     * @return maximum character's health
     */
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Get the attack of the character.
     * @return character's attack
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Get the character's speed.
     * @return character's speed
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * The character take damage, decreasing the health if INVINCIBLE status is found.
     * @param damage        the damage taken by the character
     */
    @Override
    public void takeDamage(final int damage) {
        if (!statuses.contains(Status.INVINCIBLE)) {
            health -= damage;
        }
    }

    /**
     * set custom health, the character health must be always under maxHealth.
     * @param health
     */
    @Override
    public void setHealth(final int health) {
        this.health = health < this.maxHealth ? health : this.maxHealth;
    }

    /**
     * Get all the active statuses.
     * @return a Set of the active statuses
     */
    @Override
    public Set<Status> getStatuses() {
        return Collections.unmodifiableSet(statuses);
    }

    /**
     * Add a status to the character.
     * @param status
     */
    @Override
    public void addStatus(final Status status) {
        statuses.add(status);
    }

    /**
     * Remove the given status.
     * @param status
     * @return true if the status was found and removed, false otherwise
     */
    @Override
    public boolean removeStatus(final Status status) {
        // return true if action is performed
        return statuses.remove(status);
    }

    /**
     * Get the character inventory.
     * @return map of <item, count> representing the character's intentory
     */
    @Override
    public Map<Item, Integer> getInventory() {
        return inventory.getItems();
    }

    /**
     * Add an item to the inventory.
     * @param item
     */
    @Override
    public void addItemToInventory(final Item item) {
        inventory.addItem(item);
    }

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    @Override
    public boolean removeItemFromInventory(final Item item) {
        return inventory.removeItem(item);
    }

    /**
     * Get the character's equipped weapon.
     * @return Optional<Weapon>
     */
    @Override
    public Optional<Weapon> getWeapon() {
        return weapon;
    }

    /**
     * Get the weapon attack.
     * @return int
     */
    @Override
    public int getWeaponAttack() {
        return weapon.isEmpty() ? 0 : weapon.get().getAttack();
    }

    /**
     * Equipe the given weapon.
     * @param weapon
     */
    @Override
    public void equipeWeapon(final Weapon weapon) {
        this.weapon = Optional.of(weapon);
    }

    /**
     * Get the character's equipped armor.
     * @return Optional<Armor>
     */
    @Override
    public Optional<Armor> getArmor() {
        return armor;
    }

    /**
     * Get the armor defence.
     * @return int
     */
    @Override
    public int getArmorDefence() {
        return armor.isEmpty() ? 0 : armor.get().getDefence();
    }

    /**
     * Equipe the given armor.
     * @param armor
     */
    @Override
    public void equipeArmor(final Armor armor) {
        this.armor = Optional.of(armor);
    }
}
