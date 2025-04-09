package summerhouse.booking.shared.util;

import com.google.gson.*;
import java.time.LocalDate;
import summerhouse.booking.shared.model.Property;

public class PropertyJsonConverter {
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate date, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(date.toString());
            }
        })
        .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, java.lang.reflect.Type type, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString());
            }
        })
        .create();

    /**
     * Converts a Property object to its JSON string representation
     * @param property The property to convert
     * @return JSON string representation of the property
     */
    public static String toJson(Property property) {
        return gson.toJson(property);
    }

    /**
     * Creates a Property object from its JSON string representation
     * @param json The JSON string to parse
     * @return Property object created from the JSON string
     */
    public static Property fromJson(String json) {
        return gson.fromJson(json, Property.class);
    }

    /**
     * Example usage of the converter
     */
    public static void main(String[] args) {
        // Create a sample property
        Property property = new Property(
            1L,
            "Seaside Villa",
            "123 Ocean Drive",
            6,
            200.0,
            LocalDate.now(),
            "Beautiful beachfront property"
        );

        // Convert to JSON
        String json = toJson(property);
        System.out.println("JSON representation:");
        System.out.println(json);

        // Convert back to Property object
        Property reconstructed = fromJson(json);
        System.out.println("\nReconstructed property:");
        System.out.println(reconstructed);
    }
} 