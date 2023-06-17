package it.tbt.controller.modelmanager;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;

public interface FightState extends ModelState {

    public int getSelectedTargetIndex();

    public void handlePreviousTarget();

    public void handleNextTarget();

    public void handlePerformAction();

    public void handleCycleAction(boolean cycleUp);

    public List<Ally> getAllies();

    public List<Enemy> getEnemies();

    public boolean isUsingSkill();

    public boolean isUsingAntidote();

    public boolean isUsingPotion();
}
