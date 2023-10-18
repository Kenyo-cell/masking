package exmaple.org.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.JsonSerializer;

import exmaple.org.model.Person;
import exmaple.org.serializer.model.Rule;
import exmaple.org.serializer.model.RuleMap;
import exmaple.org.serializer.model.SerializationEntity;
import exmaple.org.serializer.model.SerializerProperties;
import exmaple.org.serializer.variant.IntermediateSymbolSerializer;

@org.springframework.context.annotation.Configuration

public class Configuration {
    @Bean
    public Rule personRule() {
        return new Rule(Person.class, Map.of(
            "lastName", new SerializerProperties(IntermediateSymbolSerializer.class),
            "cardNumber", IntermediateSymbolSerializer.class
        ));
    }

    @Bean
    public RuleMap ruleMap(@Autowired List<Rule> rules) {
        return new RuleMap(rules.stream()
            .collect(Collectors.toMap(
                rule -> rule.getType(),
                rule -> rule.getRuleMap(),
                (f, s) -> f
            )));
    }
}
