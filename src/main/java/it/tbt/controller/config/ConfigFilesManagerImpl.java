package it.tbt.controller.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

/**
 * Manage config files in the user home directory.
 */
public class ConfigFilesManagerImpl extends ConfigManagerImpl implements ConfigFilesManager {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);
    private final String userHomePath = System.getProperty("user.home");
    private final String osName = System.getProperty("os.name");
    private final String configFolderPath;

    /**
     * Default Constructor.
     */
    public ConfigFilesManagerImpl() {
        final String relativePath;
        // Properly handle XDG Base Directory specification
        if ("Linux".equals(osName)) {
            final String val = System.getenv().get("XDG_CONFIG_HOME");
            if (val != null && val.trim().length() != 0) {
                relativePath = val;
            } else {
                relativePath = ".config";
            }
        } else {
            relativePath = "";
        }
        // Initialize configFolderPath
        this.configFolderPath = userHomePath + File.separator
                                + relativePath + File.separator
                                + "TurnBasedTunnels";
    }

    /**
     * Get the path for the folder containing the config files.
     * @return folder path string
     */
    public String getConfigFolderPath() {
        return configFolderPath;
    }

    /**
     * Check if file exists in the config path.
     * @param fileName
     * @return boolean
     */
    public boolean fileExistsInPath(final String fileName) {
        final File file = new File(configFolderPath + File.separator + fileName);
        return file.exists() && file.isFile();
    }

    /**
     * Get the required config file as a BufferedReader.
     * @param fileName
     * @return a BufferedReader
     */
    @Override
    protected Optional<BufferedReader> getConfigReader(final String fileName) {
        final String path = configFolderPath + File.separator + fileName;
        try {
            return Optional.of(
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(path),
                        StandardCharsets.UTF_8
                    )
                )
            );
        } catch (FileNotFoundException e) {
            logger.throwing(className, "getConfigReader", e);
            return Optional.empty();
        }
    }

    /**
     * Get the required config file as a BufferedWriter.
     * @param fileName
     * @return a BufferedWriter
     */
    protected Optional<BufferedWriter> getConfigWriter(final String fileName) {
        final String path = configFolderPath + File.separator + fileName;
        try {
            return Optional.of(
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(path),
                        StandardCharsets.UTF_8
                    )
                )
            );
        } catch (FileNotFoundException e) {
            logger.throwing(className, "getConfigFileWriter", e);
            return Optional.empty();
        }
    }

    /**
     * Write the given string to the config file.
     * @param fileName name of the config file
     * @param content string that has to be written
     * @return false if the write fails
     */
    @CheckReturnValue
    @Override
    public boolean writeConfig(final String fileName, final String content) {
        final String path = configFolderPath + File.separator + fileName;
        final Optional<BufferedWriter> optWriter = getConfigWriter(path);
        if (optWriter.isEmpty()) {
            return false;
        }
        final BufferedWriter bufWriter = optWriter.get();
        try {
            bufWriter.write(content);
            bufWriter.close();
            return true;
        } catch (IOException e) {
            logger.throwing(className, "writeConfig", e);
            return false;
        }
    }

    /**
     * Write the given BufferedReader to the config file.
     * @param fileName name of the config file
     * @param content BufferedReader that has to be written
     * @return false if the write or read fails
     */
    @CheckReturnValue
    protected boolean writeConfig(final String fileName, final BufferedReader content) {
        final String path = configFolderPath + File.separator + fileName;
        final Optional<BufferedWriter> optWriter = getConfigWriter(path);
        if (optWriter.isEmpty()) {
            return false;
        }
        final BufferedWriter bufWriter = optWriter.get();
        try {
            content.transferTo(bufWriter);
            bufWriter.close();
            return true;
        } catch (IOException e) {
            logger.throwing(className, "writeConfig", e);
            return false;
        }
    }
}
