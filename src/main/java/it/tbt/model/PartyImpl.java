package it.tbt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.tbt.model.characters.Ally;

/**
 * Implementation of a Party.
 */
public class PartyImpl implements Party {
    private final List<Ally> members;
    private int wallet;

    /**
     * Constractor with array of allies.
     * @param allies
     */
    public PartyImpl(final Ally... allies) {
        this.members = new ArrayList<>(allies.length);
        for (final Ally ally : allies) {
            this.members.add(ally);
        }
    }

    /**
     * Constructor with Collection of allies.
     * @param allies
     */
    public PartyImpl(final Collection<Ally> allies) {
        this.members = new ArrayList<>(allies);
    }

    /**
     * Get the party members.
     * @return list of allies
     */
    @Override
    public List<Ally> getMembers() {
        return Collections.unmodifiableList(members);
    }

    /**
     * Get the current amount of cash available to the party.
     * @return available cash
     */
    @Override
    public int getWallet() {
        return wallet;
    }

    /**
     * Add/subtract the given amount to the wallet.
     * @param amount
     */
    @Override
    public void addCash(final int amount) {
        wallet += amount;
    }
}
