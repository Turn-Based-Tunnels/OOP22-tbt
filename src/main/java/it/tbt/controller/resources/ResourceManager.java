package it.tbt.controller.resources;

import java.util.Optional;

/**
 * Manages resource files.
 */
interface ResourceManager {

    /**
     * Read the required file.
     * @param filePath resource file path relative to the config directory
     * @return the bytes read, empty if error encountered
     */
    Optional<byte[]> readResource(String filePath);
}
