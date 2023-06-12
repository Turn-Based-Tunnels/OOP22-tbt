package it.tbt.controller.ModelManager;


import it.tbt.model.World.api.Room;
import it.tbt.model.party.IParty;



public interface ExploreState extends ModelState {
    public IParty getParty();

    public Room getRoom();


}
