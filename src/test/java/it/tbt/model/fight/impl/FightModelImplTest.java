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

/**
 * This class is used to test the implementation of the {@link FightModelImpl}
 * class.
 */
public final class FightModelImplTest {

    private FightModelImpl fightModel;

    private static final int BASE_HEALTH = 10;
    private static final int BASE_ATTACK = 10;
    private static final int ALLY1_SPEED = 100;
    private static final int ALLY2_SPEED = 110;
    private static final int ALLY3_SPEED = 120;
    private static final int ALLY4_SPEED = 130;
    private static final int DEFAULT_POSITION_AND_SIZE = 75;

    @SuppressWarnings("MagicNumber")
    @BeforeEach
    public void setUp() {
        int averageEnemyStat = 10;
        final Map<Item, Double> drops = new HashMap<>();
        final List<Ally> allies = new ArrayList<>();
        allies.add(new Ally("Turgico", BASE_HEALTH, BASE_ATTACK, ALLY1_SPEED));
        allies.add(new Ally("Marco", BASE_HEALTH, BASE_ATTACK, ALLY2_SPEED));
        allies.add(new Ally("Gianfranco", BASE_HEALTH, BASE_ATTACK, ALLY3_SPEED));
        allies.add(new Ally("Roberto", BASE_HEALTH, BASE_ATTACK, ALLY4_SPEED));
        fightModel = new FightModelImpl(averageEnemyStat, drops);
        final IParty party = new Party("Party", DEFAULT_POSITION_AND_SIZE, DEFAULT_POSITION_AND_SIZE,
                DEFAULT_POSITION_AND_SIZE, DEFAULT_POSITION_AND_SIZE, allies);

        fightModel.initializeParty(party);
    }

    /**
     * Tests the initialization of the party in the fight model.
     */
    @SuppressWarnings("MagicNumber")
    @Test
    public void testInitializeParty() {
        // Create a party
        Party party = new Party("Party", DEFAULT_POSITION_AND_SIZE, DEFAULT_POSITION_AND_SIZE,
                DEFAULT_POSITION_AND_SIZE, DEFAULT_POSITION_AND_SIZE);
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

    /**
     * Tests the retrieval of all allies from the fight model.
     */
    @Test
    public void testGetAllies() {
        Assertions.assertNotNull(fightModel.getAllies());
    }

    /**
     * Tests the retrieval of all enemies from the fight model.
     */
    @Test
    public void testGetEnemies() {
        Assertions.assertNotNull(fightModel.getEnemies());
    }

    /**
     * Tests the retrieval of the current ally from the fight model.
     */
    @Test
    public void testGetCurrentAlly() {
        Assertions.assertNotNull(fightModel.getCurrentAlly());
    }

    /**
     * Tests the selection of an action in the fight model.
     */
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

    /**
     * Tests the advancement of the turn in the fight model.
     */
    @Test
    void testAdvanceTurn() {
        Ally ally1 = fightModel.getCurrentAlly();
        fightModel.advanceTurn();
        Ally ally2 = fightModel.getCurrentAlly();
        assertNotEquals(ally2, ally1);
        assertEquals(ally2, fightModel.getCurrentAlly());
    }

    /**
     * Tests the performance of the selected action in the fight model.
     */
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

    /**
     * Tests the selection of the next target in the fight model.
     */
    @SuppressWarnings("MagicNumber")
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

    /**
     * Tests the selection of the previous target in the fight model.
     */
    @SuppressWarnings("MagicNumber")
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
