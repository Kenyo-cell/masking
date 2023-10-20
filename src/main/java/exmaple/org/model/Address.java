package exmaple.org.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Address {
    String state;
    String city;
    String street;
    String home;
    String flat;
}
