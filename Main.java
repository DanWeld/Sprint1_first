--module-path "C:\JavaFX\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml

Property property = new Property("1", "Beach House", ...);
String json = JsonUtils.toJson(property);

String json = "..."; // your JSON string
Property property = JsonUtils.fromJson(json, Property.class);

List<Property> properties = new ArrayList<>();
// Add properties...
JsonUtils.saveListToFile(properties, "properties.json");

List<Property> loadedProperties = JsonUtils.loadListFromFile("properties.json", Property.class);
