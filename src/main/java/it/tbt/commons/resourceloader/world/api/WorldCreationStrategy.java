package it.tbt.commons.resourceloader.world.api;

import it.tbt.model.world.api.World;

/**
 * Interface for different WorldCreation objects who can create World
 * object with different methods.
 */

public interface WorldCreationStrategy {

    /**
     * @return the World object created
     */
    World createWorld();
}
