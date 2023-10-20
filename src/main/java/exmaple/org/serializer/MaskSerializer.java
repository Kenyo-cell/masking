package exmaple.org.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import exmaple.org.serializer.model.RuleMap;
import exmaple.org.serializer.model.SerializationEntity;

public class MaskSerializer extends StdSerializer<Object> {
    private final RuleMap ruleMap;
    private final JsonSerializer<Object> defSerilizer;

    public MaskSerializer(RuleMap ruleMap, JsonSerializer<Object> defaultSerializer) {
        super(Object.class);
        this.ruleMap = ruleMap;
        defSerilizer = defaultSerializer;
    }


    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ruleMap.hasRule(object.getClass())) {
            jsonGenerator.writeStartObject();
            Stream.of(object.getClass().getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Object o = field.get(object);
                        Optional.ofNullable(ruleMap.getRule(object.getClass()).get(field.getName()))
                        .ifPresentOrElse(
                            properties -> {
                                try {
                                    properties.getSerializerClass().getConstructor().newInstance()
                                        .serialize(new SerializationEntity(field.getName(), o, properties.asMap()), jsonGenerator, serializerProvider);
                                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                        | InvocationTargetException | NoSuchMethodException | SecurityException
                                        | IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            },
                            () -> {
                                try {
                                    jsonGenerator.writeFieldName(field.getName());
                                    JsonSerializer<Object> typedSerializer = serializerProvider.findTypedValueSerializer(field.getType(), true, null);
                                    
                                    if (typedSerializer == null) {
                                        // serializerProvider.findValueSerializer(field.getType(), null).serialize(o, jsonGenerator, serializerProvider);
                                        defSerilizer.serialize(object, jsonGenerator, serializerProvider);
                                    } else {
                                        typedSerializer.serialize(o, jsonGenerator, serializerProvider);
                                    }
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            });
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    
                });
                jsonGenerator.writeEndObject();
        } else {
            defSerilizer.serialize(object, jsonGenerator, serializerProvider);
        }
    }
    
}
