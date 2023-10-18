package exmaple.org.serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonSerializer;

import exmaple.org.serializer.model.SerializationEntity;
import exmaple.org.serializer.variant.IntermediateSymbolSerializer;

@Component
public class SerializerFactory {
    @Autowired
    private IntermediateSymbolSerializer intermediateSymbolSerializer;

    public JsonSerializer<SerializationEntity> createSerializerFunction() {
        return intermediateSymbolSerializer; 
    }
}
