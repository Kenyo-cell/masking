package exmaple.org.serializer.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class PropertiesBase {
    public static final Function<Object, Integer> CAST_INT = o -> (int) o;

    protected final Map<String, Object> properties;

    protected PropertiesBase() {
        properties = new HashMap<>();
    }

    protected PropertiesBase(Map<String, Object> properties) {
        this.properties = properties;
    }

    public final Object getProperty(String key) {
        return properties.get(key);
    }

    public final <T> T castedProperty(String key, Class<T> castClass) {
        return castClass.cast(properties.get(key));
    }

    public final <T> T mappedProperty(String key, Function<Object, T> mapFunction) {
        return mapFunction.apply(properties.get(key));
    }

    public final void addProperty(String key, Object value) {
        properties.put(key, value);
    }
}
