package exmaple.org;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exmaple.org.beans.DescriptorBase;

@SpringBootApplication
public class App implements CommandLineRunner{
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private List<DescriptorBase> descriptors;

    @Override
    public void run(String... args) throws Exception {
        descriptors.forEach(DescriptorBase::printed);
    }
}
