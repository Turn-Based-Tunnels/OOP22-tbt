package it.tbt.controller.resources;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Handle JSON config files.
 */
public final class ConfigManager {
    private static final String CLASS_NAME = ConfigManager.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    /**
     * Private constructor.
     */
    private ConfigManager() {
        throw new UnsupportedOperationException("Utility class and cannot be instantiated");
    }

    /**
     * Generate a new object from baseClass by reading a json config file.
     * @param <T> the new class
     * @param filePath config file path relative to the config directory
     * @param baseClass
     * @return the generated object
     */
    public static <T> Optional<T> parseJsonConfig(final String filePath, final Class<T> baseClass) {
        final MainResourceManager resourceManager = new MainResourceManager();
        final Optional<byte[]> config = resourceManager.readResource(filePath);
        if (config.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(
                    new Gson().fromJson(
                        new String(config.get(), StandardCharsets.UTF_8),
                        baseClass
                    )
                );
            } catch (JsonSyntaxException e) {
                LOGGER.throwing(CLASS_NAME, "parseJsonConfig", e);
                return Optional.empty();
            }
        }
    }
}
