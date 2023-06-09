package it.tbt.controller.config;

import java.io.BufferedReader;
import java.util.Optional;

/**
 * Manages config files, combining those in the CalssPath and those in the user home.
 */
public class MainConfigManager implements ConfigFilesManager {
    private final ConfigFilesManagerImpl configFilesManager = new ConfigFilesManagerImpl();
    private final ClassPathManagerImpl classPathManager = new ClassPathManagerImpl();

    /**
     * Read the required config file from the user config path.
     * If the file does not exists, try reading it from the ClassPath
     * and save a copy in the user config path
     * @param fileName
     * @return the string read, empty if error encountered
     */
    @Override
    public Optional<String> readConfig(final String fileName) {
        if (!configFilesManager.fileExistsInPath(fileName)) {
            final Optional<BufferedReader> optInClassPathStream =
                classPathManager.getConfigReader(fileName);
            if (optInClassPathStream.isEmpty()) {
                // propagate, do not log
                return Optional.empty();
            } else {
                if (!configFilesManager.writeConfig(fileName, optInClassPathStream.get())) {
                    // propagate, do not log
                    return Optional.empty();
                }
            }
        }
        return configFilesManager.readConfig(fileName);
    }

    /**
     * Write the given string to the file in the user config path.
     * @param fileName name of the config file
     * @param content string that has to be written
     * @return false if the write fails
     */
    @Override
    public boolean writeConfig(final String fileName, final String content) {
        return configFilesManager.writeConfig(fileName, content);
    }
}
