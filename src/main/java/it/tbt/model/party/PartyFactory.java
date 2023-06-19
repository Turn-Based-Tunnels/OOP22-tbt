package it.tbt.model.party;

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
    public static IParty createDefaultParty() {
       return new Party(DEFAULT_PARTY_NAME,
               (DEFAULT_WIDTH / 2),
               (DEFAULT_HEIGHT / 2),
               DEFAULT_WIDTH,
               DEFAULT_HEIGHT);
    }
}
