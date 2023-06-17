package it.tbt.model.fight.api;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateTrigger;

public interface FightModel extends StateTrigger {

    Ally getCurrentAlly();

    List<Ally> getAllies();

    List<Enemy> getEnemies();

    int getSelectedTargetIndex();

    void selectPreviousTarget();

    void selectNextTarget();

    void performSelectedAction();

    boolean isUsingSkill();

    void selectAction(boolean b, boolean c, boolean d);

    boolean isUsingPotion();

    boolean isUsingAntidote();

    void initializeParty(IParty party);

}
