package it.tbt.controller.resources;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

/**
 * Manage resource files in the user config directory.
 */
interface ResourceFilesManager extends ResourceManager {

    /**
     * Write the given data to the resource file.
     * @param filePath resource file path relative to the config directory
     * @param content data that has to be written
     * @return false if the write fails
     */
    @CheckReturnValue
    boolean writeResource(String filePath, byte[] content);
}
