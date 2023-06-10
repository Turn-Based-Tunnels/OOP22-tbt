package it.tbt.Model.World.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.tbt.Model.Entities.Entity;
import it.tbt.Model.World.api.Room;
import it.tbt.Model.World.api.World;
import it.tbt.Model.World.api.WorldCreationStrategy;

import java.io.FileReader;
import java.io.IOException;

public class FileWorldCreationStrategy implements WorldCreationStrategy {
    private String file_name;

    public FileWorldCreationStrategy(final String fp) {
        this.file_name = fp;
    }

    /**
     * @return
     */
    @Override
    public World createWorld() {

        try (FileReader fileReader = new FileReader(file_name)) {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(fileReader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            World world = new WorldImpl();

            // Read and process rooms
            JsonArray roomsArray = jsonObject.getAsJsonArray("rooms");
            for (JsonElement roomElement : roomsArray) {
                JsonObject roomObject = roomElement.getAsJsonObject();
                String roomName = roomObject.get("name").getAsString();

                Room room = new RoomImpl(roomName);

                // Read and process objects within the room
                JsonArray objectsArray = roomObject.getAsJsonArray("objects");
                for (JsonElement objectElement : objectsArray) {
                    JsonObject objectObject = objectElement.getAsJsonObject();
                    String objectName = objectObject.get("name").getAsString();
                    String objectType = objectObject.get("type").getAsString();
                    JsonObject position = objectObject.getAsJsonObject("position");
                    // Create the object based on its type and add it to the room
                    Entity gameObject = new Entity(objectName,position.get("x").getAsInt(),position.get("y").getAsInt());
                    room.addEntity(gameObject);
                }

                // Add the room to the world
                world.addRoom(room);
            }
            return world;


        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        };
        return null;
    }
}
