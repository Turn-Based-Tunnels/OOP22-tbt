package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.npc.api.AllyNPC;
import it.tbt.model.party.IParty;

/**
 * The {@code AllyNPCImpl} class is an implementation of the {@link AllyNPC} interface.
 * It extends the {@link AbstractNPCImpl} class and represents an NPC ally.
 */
public class AllyNPCImpl extends AbstractNPCImpl implements AllyNPC {

    private final Ally ally;

    /**
     * Constructs a new instance of the AllyNPCImpl class with the specified name, position, dimensions, and ally.
     *
     * @param name   the name of the ally NPC
     * @param x      the X coordinate of the ally NPC's position
     * @param y      the Y coordinate of the ally NPC's position
     * @param height the height of the ally NPC
     * @param width  the width of the ally NPC
     * @param ally   the ally associated with the NPC
     * @throws IllegalArgumentException if the name is null or empty, or if the ally is null
     */
    public AllyNPCImpl(final String name, final int x, final int y, final int height, final int width, final Ally ally) {
        super(name, x, y, height, width);

        if (ally == null) {
            throw new IllegalArgumentException("Ally cannot be null");
        }

        this.ally = new Ally (ally.getName(), ally.getMaxHealth(), ally.getAttack(), ally.getSpeed(), ally.getSkills());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ally getAlly() {
        return new Ally (this.ally.getName(), this.ally.getMaxHealth(), this.ally.getAttack(), this.ally.getSpeed(), this.ally.getSkills());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(final SpatialEntity interactionTrigger) {
        if (interactionTrigger instanceof IParty) {
            ((IParty) interactionTrigger).addMember(ally);
        }
    }
}
