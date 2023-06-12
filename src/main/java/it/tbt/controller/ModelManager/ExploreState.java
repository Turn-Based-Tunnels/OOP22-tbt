package it.tbt.controller.ModelManager;

import it.tbt.model.Party.IParty;
import it.tbt.model.World.api.Room;

public interface ExploreState extends ModelState {
    public IParty getParty();

    public Room getRoom();


}
