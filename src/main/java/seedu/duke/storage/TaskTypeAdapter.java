package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import seedu.duke.task.Task;

public class TaskTypeAdapter implements JsonSerializer<Task>, JsonDeserializer<Task> {
    private static final String JSON_TYPE_KEY = "type";

    private final Gson gson;

    public TaskTypeAdapter() {
        this.gson = new Gson();
    }

    @Override
    public Task deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonElement = jsonObject.get(JSON_TYPE_KEY);
        Class<? extends Task> taskType = getObjectClass(jsonElement.getAsString());
        return gson.fromJson(jsonObject, taskType);
    }

    @Override
    public JsonElement serialize(Task task, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = (JsonObject) gson.toJsonTree(task);
        jsonObject.addProperty(JSON_TYPE_KEY, task.getClass().getName());
        return jsonObject;
    }

    private Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
