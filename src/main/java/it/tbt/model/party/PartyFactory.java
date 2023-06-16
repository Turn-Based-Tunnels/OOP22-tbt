package it.tbt.model.party;

import it.tbt.model.world.api.Room;

public final class PartyFactory {

    private static int DEFAULT_WIDTH = 50;
    private static int DEFAULT_HEIGHT = 50;
    private static String DEFAULT_PARTY_NAME = "PARTY";

   public static IParty createDefaultParty() {
       return new Party(DEFAULT_PARTY_NAME, Room.DEFAULT_START_X + (DEFAULT_WIDTH/2), Room.DEFAULT_START_Y + (DEFAULT_HEIGHT/2), DEFAULT_HEIGHT, DEFAULT_WIDTH);
   }
}
