package exmaple.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import exmaple.org.model.Address;
import exmaple.org.model.Person;
import exmaple.org.serializer.MaskSerializerBeanModifier;

@SpringBootApplication
@Configurable
public class App implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private MaskSerializerBeanModifier beanModifier;

    private final Person person = Person.builder()
        .firstName("Muhammad")
        .lastName("Ali")
        .cardNumber("12345678098765")
        .address(
            Address.builder()
                .city("Moscow")
                .build()
            )
        .build();

    @Override
    public void run(String... args) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new SimpleModule("masking").setSerializerModifier(beanModifier))
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .setSerializationInclusion(Include.NON_NULL);
        System.out.println(objectMapper.writeValueAsString(person));
    }
}
