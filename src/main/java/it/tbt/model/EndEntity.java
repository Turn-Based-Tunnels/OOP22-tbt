package it.tbt.model;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.SpatialEntityImpl;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.interaction.Interactable;

import java.util.Optional;

public class EndEntity extends SpatialEntityImpl implements Interactable, StateTrigger {

    private Optional<StateObserver> stateObserver;

    /**
     * Default constructor.
     *
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public EndEntity(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    /**
     * Performs the operation on its interaction .
     * Different outcomes can be achieved based on the type of the parameter.
     *
     * @param interactionTrigger
     */
    @Override
    public void onInteraction(final SpatialEntity interactionTrigger) {
        if(stateObserver.isEmpty()) {
            throw new IllegalStateException("Entity not properly initialized.");
        }
        stateObserver.get().onEnding("You have reached an end entity!");
    }

    /**
     * This object shall have a reference to a StateObserver in order to notify it for changes of the GameState.
     *
     * @param stateObserver
     */
    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = Optional.of(stateObserver);
    }
}
