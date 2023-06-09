package it.tbt.controller.config;

import java.util.Optional;

/**
 * Manages config files.
 */
interface ConfigManager {

    /**
     * Read the required config file.
     * @param fileName
     * @return the string read, empty if error encountered
     */
    Optional<String> readConfig(String fileName);
}
