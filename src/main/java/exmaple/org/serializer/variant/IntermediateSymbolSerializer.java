package exmaple.org.serializer.variant;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import exmaple.org.serializer.model.SerializationEntity;

@Component
public class IntermediateSymbolSerializer extends JsonSerializer<SerializationEntity> {

    @Override
    public void serialize(SerializationEntity object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (object.getValue() == null) {
            serializerProvider.findKeySerializer(object.getFieldName().getClass(), null)
                .serialize(object.getFieldName(), jsonGenerator, serializerProvider);
            serializerProvider.findValueSerializer(object.getValue().getClass(), null)
                .serialize(object.getValue(), jsonGenerator, serializerProvider);
        } else {
            jsonGenerator.writeFieldName(object.getFieldName());
            jsonGenerator.writeString(this.mask(
                String.valueOf(object.getValue()),
                object.mappedProperty("f", SerializationEntity.CAST_INT),
                object.mappedProperty("s", SerializationEntity.CAST_INT)
            ));
        }
    }

    private String mask(String origin, int before, int against) {
        if (before > origin.length() || against > origin.length()) {
            return origin.substring(origin.length() - 1) + "*";
        }
        return origin.substring(0, before) + "*" + origin.substring(origin.length() - against);
    }
    
}
