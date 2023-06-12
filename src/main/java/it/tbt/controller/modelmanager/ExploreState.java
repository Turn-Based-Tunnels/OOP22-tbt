package it.tbt.controller.modelmanager;


import it.tbt.model.world.api.Room;
import it.tbt.model.party.IParty;



public interface ExploreState extends ModelState {
    public IParty getParty();

    public Room getRoom();


}
