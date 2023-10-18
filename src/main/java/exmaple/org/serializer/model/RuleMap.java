package exmaple.org.serializer.model;

import java.util.Map;
import java.io.Serializable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RuleMap {
    private final Map<Class<? extends Serializable>, Map<String, SerializerProperties>> ruleMap;

    public boolean hasRule(Class<? extends Serializable> type) {
        return ruleMap.containsKey(type);
    }

    public Map<String, SerializerProperties> getRule(Class<? extends Serializable> type) {
        return ruleMap.get(type);
    }
}
