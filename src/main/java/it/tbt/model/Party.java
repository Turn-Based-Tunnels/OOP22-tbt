package it.tbt.model;

import java.util.List;

import it.tbt.model.characters.Ally;

/**
 * Generic Party.
 */
public interface Party {

    /**
     * Get the party members.
     * @return list of allies
     */
    List<Ally> getMembers();

    /**
     * Get the current amount of cash available to the party.
     * @return available cash
     */
    int getWallet();

    /**
     * Add/subtract the given amount to the wallet.
     * @param amount
     */
    void addCash(int amount);
}
