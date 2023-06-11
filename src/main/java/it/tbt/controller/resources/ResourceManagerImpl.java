package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Incomplete implementation of ResourceManager.
 */
abstract class ResourceManagerImpl implements ResourceManager {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user home directory, it will be
     * read from the classpath and written in the user home directory
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     */
    abstract Optional<BufferedInputStream> getResourceInputStream(String filePath);

    /**
     * Read the required resource.
     * @param filePath resource file path relative to the config directory
     * @return the bytes read, empty if error encountered
     */
    @Override
    public Optional<byte[]> readResource(final String filePath) {
        final Optional<BufferedInputStream> optReader = getResourceInputStream(filePath);
        if (optReader.isEmpty()) {
            // propagate, do not log
            return Optional.empty();
        } else {
            final BufferedInputStream reader = optReader.get();
            byte[] ret;
            try {
                ret = reader.readAllBytes();
                reader.close();
                return Optional.of(ret);
            } catch (IOException e) {
                logger.throwing(className, "readResouce", e);
                return Optional.empty();
            }
        }
    }
}
