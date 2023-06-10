package it.tbt.controller.ModelManager;

import it.tbt.model.GameState;
import it.tbt.model.party.IParty;
import it.tbt.model.World.api.Room;
import it.tbt.model.World.api.World;
import it.tbt.model.time.TimeAffected;

public class GameStateManager implements IGameStateManager {

    private GameState gameState;
    private IParty party;
    private World world;

    private ModelState modelState;

    private Boolean hasStateChanged;

    public GameStateManager(final IParty party, final World world) {
        this.party = party;
        this.world = world;
        this.triggerExplore();
    }

    /**
     * @return
     */
    @Override
    public ModelState getStateModel() {
        return this.modelState;
    }

    /**
     * @return
     */
    @Override
    public GameState getState() {
        return gameState;
    }

    /**
     * @return
     */
    @Override
    public Boolean hasStateChanged() {
        var x = hasStateChanged;
        if(hasStateChanged == true) {
            hasStateChanged = false;
        }
        return x;
    }

    /**
     *
     */
    @Override
    public void updateState(float timePassed) {
        Room r = party.getCurrentRoom();
        r.getEntities().stream().filter(l->l instanceof TimeAffected).forEach(l->((TimeAffected) l).affect(timePassed));
    }

    /**
     * @return
     */
    @Override
    public IParty getParty() {
        return this.party;
    }

    /**
     * @param party
     */
    @Override
    public void setParty(IParty party) {
        this.party = party;
    }

    /**
     * @return
     */
    @Override
    public World getWorld() {
        return this.world;
    }

    /**
     * @param world
     */
    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * @return
     */
    @Override
    public Boolean isOver() {
        return this.party.getX()==40;
    }

    protected void triggerCombatState() {

    }

    protected void triggerExplore() {
        if(this.gameState != GameState.EXPLORE) {
            this.hasStateChanged = true;
        } else {
            this.hasStateChanged = false;
        }
        this.gameState = GameState.EXPLORE;
        this.modelState = new ExploreStateImpl(this.party.getCurrentRoom(), this.party);
    }
}
