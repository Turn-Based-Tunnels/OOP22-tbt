package it.tbt.controller.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manage config file in ClassPath.
 */
class ClassPathManagerImpl extends ConfigManagerImpl {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);

    /**
     * Get the required config file as a BufferedReader.
     * @param fileName
     * @return a BufferedReader
     */
    @Override
    protected Optional<BufferedReader> getConfigReader(final String fileName) {
        final InputStream reader =
            ClassLoader.getSystemResourceAsStream(
                fileName.replace("\\", "/")
            );
        if (reader == null) {
            logger.throwing(className, "getConfigReader", new FileNotFoundException());
            return Optional.empty();
        } else {
            return Optional.of(
                new BufferedReader(
                    new InputStreamReader(reader, StandardCharsets.UTF_8)
                )
            );
        }
    }
}
