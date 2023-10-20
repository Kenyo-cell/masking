package exmaple.org.serializer;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;

import exmaple.org.serializer.model.RuleMap;

@Component
public class MaskSerializerBeanModifier extends BeanSerializerModifier {
    @Autowired
    private RuleMap ruleMap;

    @Override
    public JsonSerializer<Object> modifySerializer(SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
        return new MaskSerializer(ruleMap, (JsonSerializer<Object>) serializer);
    }
}
