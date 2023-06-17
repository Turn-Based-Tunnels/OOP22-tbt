package it.tbt.model.party;

/**
 * Factory that returns different implementations of the IParty interface.
 */
public final class PartyFactory {

    private static final int defaultWidth = 50;
    private static final int defaultHeight = 50;
    private static final String defaultPartyName = "PARTY";

    /**
     * Private constructor not to give the possibility to instantiate a utility class.
     */
    private PartyFactory() { }

    /**
     * @return a Party object with default settings.
     */
    public static IParty createDefaultParty() {
       return new Party(defaultPartyName,
               (defaultWidth / 2),
               (defaultHeight / 2),
               defaultWidth,
               defaultHeight);
    }
}
