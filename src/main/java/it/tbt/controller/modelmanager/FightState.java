package it.tbt.controller.modelmanager;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;

public interface FightState extends ModelState {

    int getSelectedTargetIndex();

    void handlePreviousTarget();

    void handleNextTarget();

    void handlePerformAction();

    void handleCycleAction(boolean cycleUp);

    List<Ally> getAllies();

    List<Enemy> getEnemies();

    boolean isUsingSkill();

    boolean isUsingAntidote();

    boolean isUsingPotion();

    Ally getCurrentAlly();
}
