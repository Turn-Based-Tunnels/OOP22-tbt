package it.tbt.model.party;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.characters.skills.SkillFactory;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.factories.AntidoteFactory;
import it.tbt.model.entities.items.factories.PotionFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Factory that returns different implementations of the IParty interface.
 */
public final class PartyFactory {

    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;
    private static final String DEFAULT_PARTY_NAME = "PARTY";

    /**
     * Private constructor not to give the possibility to instantiate a utility class.
     */
    private PartyFactory() { }

    /**
     * @return a Party object with default settings.
     */
    @SuppressWarnings("magicnumber")
    public static IParty createDefaultParty() {
        ArrayList<Ally> allies = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>(SkillFactory.getFactory().getSkills());

        allies.add(new Ally("Roberto", 50, 50, 50, new ArrayList<>(Arrays.asList(skills.get(0)))));
        allies.add(new Ally("Gianfranco", 10, 10, 70, new ArrayList<>(Arrays.asList(skills.get(1)))));
        allies.add(new Ally("Caparezza", 30, 90, 20, new ArrayList<>(Arrays.asList(skills.get(2)))));
        allies.add(new Ally("Robertino", 20, 20, 20, new ArrayList<>(Arrays.asList(skills.get(3)))));
        Party p = new Party("party", 75, 75, 75, 75, allies);
        for(final Potion potion : PotionFactory.getInstance().getItems()) {
            p.addItemToInventory(potion);
            p.addItemToInventory(potion);
        }
        p.addItemToInventory(AntidoteFactory.getInstance().getAntidote());
        p.addCash(5000);
       return new Party(DEFAULT_PARTY_NAME,
               (DEFAULT_WIDTH / 2),
               (DEFAULT_HEIGHT / 2),
               DEFAULT_WIDTH,
               DEFAULT_HEIGHT, allies);
    }
}
