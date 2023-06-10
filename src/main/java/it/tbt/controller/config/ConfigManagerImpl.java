package it.tbt.controller.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Incomplete implementation of ConfigManager.
 */
abstract class ConfigManagerImpl implements ConfigManager {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);

    /**
     * Get the config file.
     * If it does not exists in the user home directory, it will be read from the
     * classpath and written in the user home directory
     * @param fileName
     * @return BufferedReader
     */
    abstract Optional<BufferedReader> getConfigReader(String fileName);

    /**
     * Read the required config file.
     * @param fileName
     * @return the string read, empty if error encountered
     */
    @Override
    public Optional<String> readConfig(final String fileName) {
        final Optional<BufferedReader> optReader = getConfigReader(fileName);
        if (optReader.isEmpty()) {
            // propagate, do not log
            return Optional.empty();
        } else {
            final BufferedReader reader = optReader.get();
            final StringBuffer ret = new StringBuffer();
            String tmp;
            try {
                tmp = reader.readLine();
                while (tmp != null) {
                    ret.append(tmp);
                    tmp = reader.readLine();
                }
                reader.close();
                return Optional.of(ret.toString());
            } catch (IOException e) {
                logger.throwing(className, "readConfig", e);
                return Optional.empty();
            }
        }
    }
}
