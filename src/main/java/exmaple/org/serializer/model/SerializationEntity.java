package exmaple.org.serializer.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SerializationEntity extends PropertiesBase {
    String fieldName;
    Serializable value;
    

    public SerializationEntity(String fieldName, String value) {
        super();
        this.fieldName = fieldName;
        this.value = value;
    }
}
