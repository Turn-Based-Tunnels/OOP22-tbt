package it.tbt.controller.config;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

/**
 * Manage config files in the user home directory.
 */
interface ConfigFilesManager extends ConfigManager {

    /**
     * Write the given string to the config file.
     * @param fileName name of the config file
     * @param content string that has to be written
     * @return false if the write fails
     */
    @CheckReturnValue
    boolean writeConfig(String fileName, String content);
}
