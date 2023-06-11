package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Optional;

/**
 * Manages resource files, combining those in the CalssPath and those in the user home.
 */
public class MainResourceManager implements ResourceFilesManager {
    private final ResourceFilesManagerImpl resourceFilesManager = new ResourceFilesManagerImpl();
    private final SystemResourceManagerImpl classPathManager = new SystemResourceManagerImpl();

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user home directory, it will be
     * read from the classpath and written in the user home directory
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     */
    public Optional<BufferedInputStream> getResourceInputStream(final String filePath) {
        if (!resourceFilesManager.fileExistsInPath(filePath)) {
            final Optional<BufferedInputStream> optInClassPathStream =
                classPathManager.getResourceInputStream(filePath);
            if (optInClassPathStream.isEmpty()) {
                // propagate, do not log
                return Optional.empty();
            } else {
                if (!resourceFilesManager.writeResource(filePath, optInClassPathStream.get())) {
                    // propagate, do not log
                    return Optional.empty();
                }
            }
        }
        return resourceFilesManager.getResourceInputStream(filePath);
    }

    /**
     * Get the required resource file as a BufferedOutputStream.
     * @param filePath resource file path relative to the config directory
     * @return a BufferedOutputStream
     */
    public Optional<BufferedOutputStream> getResourceOutputStream(final String filePath) {
        return resourceFilesManager.getResourceOutputStream(filePath);
    }

    /**
     * Read the required resource file from the user config path.
     * If the file does not exists, try reading it from the ClassPath
     * and save a copy in the user config path
     * @param filePath resource file path relative to the config directory
     * @return the string read, empty if error encountered
     */
    @Override
    public Optional<byte[]> readResource(final String filePath) {
        if (!resourceFilesManager.fileExistsInPath(filePath)) {
            final Optional<BufferedInputStream> optInClassPathStream =
                classPathManager.getResourceInputStream(filePath);
            if (optInClassPathStream.isEmpty()) {
                // propagate, do not log
                return Optional.empty();
            } else {
                if (!resourceFilesManager.writeResource(filePath, optInClassPathStream.get())) {
                    // propagate, do not log
                    return Optional.empty();
                }
            }
        }
        return resourceFilesManager.readResource(filePath);
    }

    /**
     * Write the given string to the file in the user config path.
     * @param filePath resource file path relative to the config directory
     * @param content string that has to be written
     * @return false if the write fails
     */
    @Override
    public boolean writeResource(final String filePath, final byte[] content) {
        return resourceFilesManager.writeResource(filePath, content);
    }
}
