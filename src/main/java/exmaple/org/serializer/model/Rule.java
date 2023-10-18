package exmaple.org.serializer.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rule {
    private final Class<? extends Serializable> type;
    private final Map<String, SerializerProperties> ruleMap;
}
