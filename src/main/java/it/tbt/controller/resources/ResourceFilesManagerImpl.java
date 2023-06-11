package it.tbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

/**
 * Manage resource files in the user home directory.
 */
public class ResourceFilesManagerImpl extends ResourceManagerImpl implements ResourceFilesManager {
    private final String className = this.getClass().getName();
    private final Logger logger = Logger.getLogger(className);
    private final String userHomePath = System.getProperty("user.home");
    private final String osName = System.getProperty("os.name");
    private final String configFolderPath;

    /**
     * Default Constructor.
     * XDG Base Directory specification:
     * @see <a href="https://www.freedesktop.org/wiki/Software/xdg-user-dirs/">XDG User Dirs</a>
     * Windows (incomplete) specification:
     * @see <a href="https://learn.microsoft.com/en-us/windows/deployment/usmt/usmt-recognized-environment-variables
       #variables-that-are-recognized-only-in-the-user-context">recognized-environment-variables</a>
     * Mac/OS X specification:
     * @see <a href="https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/
       FileSystemProgrammingGuide/MacOSXDirectories/MacOSXDirectories.html">Mac OS X Directories</a>
     * @see <a href="https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/
       FileSystemProgrammingGuide/FileSystemOverview/FileSystemOverview.html">FileSystem Overview</a>
     */
    public ResourceFilesManagerImpl() {
        final Map<String, String> env = System.getenv();
        StringBuilder path = new StringBuilder(userHomePath); // default to userHomePath
        if (osName.toLowerCase(Locale.ENGLISH).contains("linux")) {
            // Properly handle XDG Base Directory specification
            final String val = env.get("XDG_CONFIG_HOME");
            if (val != null && val.trim().length() > 0) {
                path = new StringBuilder(val);
            } else {
                path.append(File.separator).append(".config");
            }
        } else if (osName.toLowerCase(Locale.ENGLISH).contains("windows")) {
            // Windows config directory
            final String val = System.getenv("LOCALAPPDATA");
            if (val != null && val.trim().length() > 0) {
                path = new StringBuilder(val);
            } else {
                path.append(File.separator).append("AppData")
                    .append(File.separator).append("Local");
            }
        } else if (
            osName.toLowerCase(Locale.ENGLISH).contains("mac")
            || osName.toLowerCase(Locale.ENGLISH).contains("os x")
        ) {
            // Mac/OS X config directory
            path.append(File.separator).append("Library/Application Support");
        }

        // Initialize configFolderPath
        this.configFolderPath = path + File.separator + "TurnBasedTunnels";

        // if configFolderPath does not exists, create it
        final File dir = new File(this.configFolderPath);
        if (!dir.exists() || !dir.isDirectory()) {
            try {
                Files.createDirectories(Paths.get(this.configFolderPath));
            } catch (IOException e) {
                logger.throwing(className, "ResourceFilesManagerImpl", e);
                // retry before giving up
                try {
                    Files.createDirectories(Paths.get(this.configFolderPath));
                } catch (IOException f) {
                    logger.throwing(className, "ResourceFilesManagerImpl", f);
                    // give up
                }
            }
        }
    }

    /**
     * Get the path for the folder containing the resource files.
     * @return folder path string
     */
    public String getConfigFolderPath() {
        return configFolderPath;
    }

    /**
     * Check if file exists in the config path.
     * @param filePath resource file path relative to the config directory
     * @return boolean
     */
    public boolean fileExistsInPath(final String filePath) {
        final File file = new File(configFolderPath + File.separator + filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Check if the directory exists in the config path.
     * @param dirPath directory path relative to the config directory
     * @return boolean
     */
    public boolean dirExistsInPath(final String dirPath) {
        final File dir = new File(configFolderPath + File.separator + dirPath);
        return dir.exists() && dir.isDirectory();
    }

    /**
     * Get a BufferedInputStream to read from the file.
     * @param filePath resource file path relative to the config directory
     * @return BufferedInputStream
     */
    @Override
    protected Optional<BufferedInputStream> getResourceInputStream(final String filePath) {
        final String path = configFolderPath + File.separator + filePath;
        try {
            return Optional.of(
                new BufferedInputStream(
                    new FileInputStream(path)
                )
            );
        } catch (FileNotFoundException e) {
            logger.throwing(className, "getResourceInputStream", e);
            return Optional.empty();
        }
    }

    /**
     * Get the required resource file as a BufferedOutputStream.
     * @param filePath resource file path relative to the config directory
     * @return a BufferedOutputStream
     */
    protected Optional<BufferedOutputStream> getResourceOutputStream(final String filePath) {
        final String path = configFolderPath + File.separator + filePath;
        try {
            return Optional.of(
                new BufferedOutputStream(
                    new FileOutputStream(path)
                )
            );
        } catch (FileNotFoundException e) {
            logger.throwing(className, "getResourceOutputStream", e);
            return Optional.empty();
        }
    }

    /**
     * Write the given string to the resource file.
     * @param filePath name of the resource file
     * @param content string that has to be written
     * @return false if the write fails
     */
    @CheckReturnValue
    @Override
    public boolean writeResource(final String filePath, final byte[] content) {
        final String path = configFolderPath + File.separator + filePath;
        final Optional<BufferedOutputStream> optWriter = getResourceOutputStream(path);
        if (optWriter.isEmpty()) {
            return false;
        }
        final BufferedOutputStream bufWriter = optWriter.get();
        try {
            bufWriter.write(content);
            bufWriter.close();
            return true;
        } catch (IOException e) {
            logger.throwing(className, "writeResource", e);
            return false;
        }
    }

    /**
     * Write the given InputStream to the resource file.
     * @param filePath name of the resource file
     * @param content InputStream that has to be written
     * @return false if the write or read fails
     */
    @CheckReturnValue
    protected boolean writeResource(final String filePath, final InputStream content) {
        final String path = configFolderPath + File.separator + filePath;
        final Optional<BufferedOutputStream> optWriter = getResourceOutputStream(path);
        if (optWriter.isEmpty()) {
            return false;
        }
        final BufferedOutputStream bufWriter = optWriter.get();
        try {
            content.transferTo(bufWriter);
            bufWriter.close();
            return true;
        } catch (IOException e) {
            logger.throwing(className, "writeResource", e);
            return false;
        }
    }
}
