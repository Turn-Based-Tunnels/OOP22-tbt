package it.tbt.controller.viewcontrollermanager.api;

public interface ExploreController extends ViewController {

    static int DEFAULT_MOVE_X = 5;
    static int DEFAULT_MOVE_Y = 5;

    /**
     * Moves the IParty in the X-axis by a positive amount
     */
    public void moveRight();

    /**
     * Moves the IParty in the Y-axis by a positive amount
     */
    public void moveDown();

    /**
     * Moves the IParty in the Y-axis by a negative amount
     */
    public void moveUp();

    /**
     * Moves the IParty in the X-axis by a negative amount
     */
    public void moveLeft();

    /**
     * The IParty interacts with the environment
     */
    public void interactWithProximity();
}
