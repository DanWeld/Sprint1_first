package shared.util;

import com.google.gson.*;
import java.time.LocalDate;
import java.lang.reflect.Type;
import java.io.*;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString());
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            return LocalDate.parse(json.getAsString());
        }
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public static void saveToFile(Object obj, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T loadFromFile(String filename, Class<T> type) {
        try (Reader reader = new FileReader(filename)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
} 