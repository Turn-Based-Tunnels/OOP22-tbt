package it.tbt.commons.resourceloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static final String FILE_SEPARATOR =System.getProperty("file.separator");
    private static String FILE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main" +FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "tbt" + FILE_SEPARATOR + "images.json";
    private static ImageLoader instance;
    private Map<Class<?>, String> imageObjectMap;

    private ImageLoader() {
        // Initialize the map and populate it from a JSON file
        imageObjectMap = new HashMap<>();
        System.out.println(FILE_PATH);
        loadMappingsFromFile(FILE_PATH);

    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public String getFilePath(Class<?> classToBeFound) {
        // Retrieve the object associated with the image path
        return imageObjectMap.get(classToBeFound);
    }

    private void loadMappingsFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(reader);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}