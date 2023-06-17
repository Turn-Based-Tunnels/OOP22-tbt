package it.tbt.controller.modelmanager;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.fight.api.FightModel;

public class FightStateImpl implements FightState {

    private FightModel model;

    public FightStateImpl(FightModel model) {
        this.model = model;
    }

    @Override
    public int getSelectedTargetIndex() {
        return model.getSelectedTargetIndex();
    }

    public void handlePreviousTarget() {
        model.selectPreviousTarget();
        // view.updateSelectedTarget(model.getSelectedEnemy());
    }

    @Override
    public void handleNextTarget() {
        model.selectNextTarget();
    }

    public void handlePerformAction() {
        model.performSelectedAction();
    }

    public void handleCycleAction(boolean cycleUp) {
        if (cycleUp) {
            if (model.isUsingSkill()) {
                model.selectAction(false, false, true);
            } else if (model.isUsingPotion()) {
                model.selectAction(false, false, false);
            } else if (model.isUsingAntidote()) {
                model.selectAction(false, true, false);
            } else {
                model.selectAction(true, false, false);
            }
        } else {
            if (model.isUsingSkill()) {
                model.selectAction(false, false, false);
            } else if (model.isUsingPotion()) {
                model.selectAction(false, false, true);
            } else if (model.isUsingAntidote()) {
                model.selectAction(true, false, false);
            } else {
                model.selectAction(false, true, false);
            }
        }
    }

    @Override
    public List<Ally> getAllies() {
        return model.getAllies();
    }

    @Override
    public List<Enemy> getEnemies() {
        return model.getEnemies();
    }

    @Override
    public boolean isUsingSkill() {
        return model.isUsingSkill();
    }

    @Override
    public boolean isUsingAntidote() {
        return model.isUsingAntidote();
    }

    @Override
    public boolean isUsingPotion() {
        return model.isUsingPotion();
    }

    @Override
    public Ally getCurrentAlly() {
        return model.getCurrentAlly();
    }

}
