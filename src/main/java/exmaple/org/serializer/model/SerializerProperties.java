package exmaple.org.serializer.model;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonSerializer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SerializerProperties extends PropertiesBase {
    Class<? extends JsonSerializer<SerializationEntity>> serializerClass;

    public SerializerProperties(Class<? extends JsonSerializer<SerializationEntity>> serializerClass, Map<String, Object> props) {
        super(props);
        this.serializerClass = serializerClass;
    }
}
