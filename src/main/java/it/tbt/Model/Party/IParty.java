package it.tbt.Model.Party;

import it.tbt.Model.World.api.Room;

public interface IParty {
    void setCurrentRoom(Room room);
    Room getCurrentRoom();
    void move(int xv, int yv);
    public int getX();
    public int getY();

}
