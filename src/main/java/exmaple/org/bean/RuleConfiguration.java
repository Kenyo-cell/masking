package exmaple.org.bean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exmaple.org.model.Person;
import exmaple.org.serializer.model.Rule;
import exmaple.org.serializer.model.RuleMap;
import exmaple.org.serializer.model.SerializerProperties;
import exmaple.org.serializer.variant.IntermediateSymbolSerializer;

@Configuration
public class RuleConfiguration {
    @Bean
    public Rule personRule() {
        return new Rule(Person.class, Map.of(
            "lastName", new SerializerProperties(IntermediateSymbolSerializer.class, Map.of("f", 1, "s", 1)),
            "cardNumber", new SerializerProperties(IntermediateSymbolSerializer.class, Map.of("f", 3, "s", 3))
        ));
    }

    @Bean
    public RuleMap ruleMap(@Autowired List<Rule> rules) {
        return new RuleMap(rules.stream().collect(Collectors.toMap(
            Rule::getType,
            Rule::getRuleMap,
            (f, s) -> f
        )));
    }
}
