package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manage resource file in ClassPath.
 */
class SystemResourceManagerImpl extends ResourceManagerImpl {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);

    /**
     * Get a BufferedInputStream to read from the file.
     * If the file does not exists in the user home directory, it will be
     * read from the classpath and written in the user home directory
     * @param filePath resource file path relative to the root of the class path
     * @return BufferedInputStream
     */
    @Override
    protected Optional<BufferedInputStream> getResourceInputStream(final String filePath) {
        final InputStream reader =
            ClassLoader.getSystemResourceAsStream(
                filePath.replace("\\", "/")
            );
        if (reader == null) {
            logger.throwing(className, "getResourceInputStream", new FileNotFoundException());
            return Optional.empty();
        } else {
            return Optional.of(new BufferedInputStream(reader));
        }
    }
}
