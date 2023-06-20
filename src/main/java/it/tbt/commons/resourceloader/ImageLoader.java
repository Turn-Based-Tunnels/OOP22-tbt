package it.tbt.commons.resourceloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for getting paths of images mapped to classes of the game.
 */

public final class ImageLoader {
    private static final String CHARSET = "UTF-8";
    /**
     * Path to the file that contains the paths for images and their mapping classes.
     */
    private static String filepath = "tbt/images.json";
    private static ImageLoader instance = new ImageLoader();
    private Map<Class<?>, String> imageObjectMap;

    /**
     * Utility class should not have public constructor.
     */
    private ImageLoader() {
        // Initialize the map and populate it from a JSON file
        imageObjectMap = new HashMap<>();
        loadMappingsFromFile(filepath);
    }

    /**
     * @return Instance of this Singleton class
     */
    public static ImageLoader getInstance() {
        return instance;
    }

    /**
     * @param classToBeFound
     * @return the path to the image corresponding to the class passed by parameter.
     */
    public String getFilePath(final Class<?> classToBeFound) {
        // Retrieve the object associated with the image path
        return imageObjectMap.get(classToBeFound);
    }

    /**
     * Loads all the paths and matches to the corresponding class in the internal map.
     * @param filePath
     */
    private void loadMappingsFromFile(final String filePath) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tbt/images.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, CHARSET);
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(inputStreamReader);
            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                // Iterate through each object in the array
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        // Extract the image path and object class
                        String imagePath = jsonObject.get("imagePath").getAsString();
                        Class<?> objectClass = Class.forName(jsonObject.get("objectClass").getAsString());
                        // Add the mapping to the imageObjectMap
                        imageObjectMap.put(objectClass, imagePath);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
