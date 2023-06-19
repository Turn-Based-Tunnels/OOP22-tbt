package it.tbt.model;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.SpatialEntityImpl;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.interaction.Interactable;
import java.util.Optional;

/**
 * Entity which on interaction triggers the {@link GameState#ENDING} GameState.
 */
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
    public EndEntity(final String name,
                     final int x,
                     final int y,
                     final int width,
                     final int height) {
        super(name, x, y, width, height);
        this.stateObserver = Optional.empty();
    }

    /**
     * Triggers the {@link GameState#ENDING} GameState.
     * @param interactionTrigger
     */
    @Override
    public void onInteraction(final SpatialEntity interactionTrigger) {
        if (stateObserver.isEmpty()) {
            throw new IllegalStateException("Entity not properly initialized.");
        }
        stateObserver.get().onEnding("You have reached an end entity!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = Optional.of(stateObserver);
    }
}