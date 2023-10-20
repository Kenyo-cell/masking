package exmaple.org.serializer.model;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RuleMap {
    private final Map<Class<? extends Object>, Map<String, SerializerProperties>> ruleMap;

    public boolean hasRule(Class<? extends Object> type) {
        return ruleMap.containsKey(type);
    }

    public Map<String, SerializerProperties> getRule(Class<? extends Object> type) {
        return ruleMap.get(type);
    }
}
