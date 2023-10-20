package exmaple.org.serializer.model;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SerializationEntity extends PropertiesBase {
    String fieldName;
    Object value;
    

    public SerializationEntity(String fieldName, Object value) {
        super();
        this.fieldName = fieldName;
        this.value = value;
    }

    public SerializationEntity(String fieldName, Object value, Map<String, Object> props) {
        super(props);
        this.fieldName = fieldName;
        this.value = value;
    }
}
