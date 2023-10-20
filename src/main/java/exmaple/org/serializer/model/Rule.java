package exmaple.org.serializer.model;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rule {
    private final Class<? extends Object> type;
    private final Map<String, SerializerProperties> ruleMap;
}
