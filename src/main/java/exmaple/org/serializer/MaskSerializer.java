package exmaple.org.serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import exmaple.org.serializer.model.RuleMap;
import lombok.SneakyThrows;

@Component
public class MaskSerializer extends JsonSerializer<Serializable> {
    @Autowired
    private RuleMap ruleMap;

    @Override
    @SneakyThrows
    public void serialize(Serializable object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (ruleMap.hasRule(object.getClass())) {
            Stream.of(object.getClass().getDeclaredFields())
                .forEach(field -> {
                    Object o = field.get(object);
                    Optional.ofNullable(ruleMap.getRule(object.getClass()).get(field.getName()))
                        .ifPresentOrElse(
                            serializer -> serializer.getConstructor().serialize(o, jsonGenerator, serializerProvider),
                            () -> {
                                serializerProvider.findKeySerializer(field.getType(), null).serialize(o, jsonGenerator, serializerProvider);
                                serializerProvider.findValueSerializer(o, null).serialize(o, jsonGenerator, serializerProvider);
                            });
                });
        }
    }
    
}
