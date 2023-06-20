package it.tbt.model.fight.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.party.IParty;
import it.tbt.model.party.Party;

public class FightModelImplTest {

    private FightModelImpl fightModel;

    @BeforeEach
    public void setUp() {
        int averageEnemyStat = 10;
        final Map<Item, Double> drops = new HashMap<>();
        fightModel = new FightModelImpl(averageEnemyStat, drops);
        final IParty party = new Party("Party", 75, 75, 75, 75);
        final List<Ally> allies = new ArrayList<>();
        allies.add(new Ally("Roberto", 10, 10, 10));
        allies.add(new Ally("Roberto", 10, 10, 11));
        allies.add(new Ally("Roberto", 10, 10, 12));
        allies.add(new Ally("Roberto", 10, 10, 13));
        fightModel.initializeParty(party);
    }

    @Test
    public void testInitializeParty() {
        // Create a party
        Party party = new Party("Party", 75, 75, 75, 75);
        Ally ally1 = new Ally("Ally 1", 20, 5, 8);
        Ally ally2 = new Ally("Ally 2", 18, 7, 6);
        party.addMember(ally1);
        party.addMember(ally2);

        // Initialize the party in the fight model
        fightModel.initializeParty(party);

        // Check if the party members are correctly set
        List<Ally> allies = fightModel.getAllies();
        Assertions.assertEquals(4, allies.size());
        Assertions.assertTrue(allies.contains(ally1));
        Assertions.assertTrue(allies.contains(ally2));
    }

    @Test
    public void testGetAllies() {
        Assertions.assertNotNull(fightModel.getAllies());
    }

    @Test
    public void testGetEnemies() {
        Assertions.assertNotNull(fightModel.getEnemies());
    }

    @Test
    public void testGetCurrentAlly() {
        Assertions.assertNull(fightModel.getCurrentAlly());
    }

    @Test
    public void testSelectAction() {
        // Select to use a skill
        fightModel.selectAction(true, false, false);
        assertTrue(fightModel.isUsingSkill());
        assertFalse(fightModel.isUsingPotion());
        assertFalse(fightModel.isUsingAntidote());

        // Select to use a potion
        fightModel.selectAction(false, true, false);
        assertFalse(fightModel.isUsingSkill());
        assertTrue(fightModel.isUsingPotion());
        assertFalse(fightModel.isUsingAntidote());

        // Select to use an antidote
        fightModel.selectAction(false, false, true);
        assertFalse(fightModel.isUsingSkill());
        assertFalse(fightModel.isUsingPotion());
        assertTrue(fightModel.isUsingAntidote());
    }

    @Test
    void testAdvanceTurn() {
        Ally ally1 = fightModel.getCurrentAlly();
        fightModel.advanceTurn();
        Ally ally2 = fightModel.getCurrentAlly();
        assertNotEquals(ally2, ally1);
        assertEquals(ally2, fightModel.getCurrentAlly());
    }

    @Test
    void testEnemyAttack() {
        int sumHP = 0;
        for (final Ally a : fightModel.getAllies()) {
            sumHP += a.getHealth();
        }
        // Simulate enemy attack
        fightModel.enemyAttack();

        int sumHPAfter = 0;
        for (final Ally a : fightModel.getAllies()) {
            sumHPAfter += a.getHealth();
        }
        // Verify that allies have reduced health
        assertTrue(sumHPAfter < sumHP);
    }

    @Test
    void testPerformSelectedAction() {
        // Simulate selecting and performing a skill action
        fightModel.selectAction(false, false, false);

        int sumHPEnemy = 0;
        for (final Enemy e : fightModel.getEnemies()) {
            sumHPEnemy += e.getHealth();
        }
        fightModel.performSelectedAction();
        int sumHPEnemyAfter = 0;
        for (final Enemy e : fightModel.getEnemies()) {
            sumHPEnemyAfter += e.getHealth();
        }

        assertTrue(sumHPEnemyAfter < sumHPEnemy);
    }

    @Test
    void testSelectNextTarget() {
        // Simulate selecting the next target
        fightModel.selectNextTarget();

        // Verify that the target index has incremented
        int initialTargetIndex = fightModel.getSelectedTargetIndex();
        fightModel.selectNextTarget();
        int updatedTargetIndex = fightModel.getSelectedTargetIndex();
        assertTrue(updatedTargetIndex == initialTargetIndex + 1 || updatedTargetIndex == 3);
    }

    @Test
    void testSelectPreviousTarget() {
        // Simulate selecting the previous target
        fightModel.selectPreviousTarget();

        // Verify that the target index has decremented
        int initialTargetIndex = fightModel.getSelectedTargetIndex();
        fightModel.selectPreviousTarget();
        int updatedTargetIndex = fightModel.getSelectedTargetIndex();
        assertTrue(updatedTargetIndex == initialTargetIndex - 1 || updatedTargetIndex == 0);
    }
}
