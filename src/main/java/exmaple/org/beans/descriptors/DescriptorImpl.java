package exmaple.org.beans.descriptors;

import org.springframework.stereotype.Component;

import exmaple.org.beans.DescriptorBase;

@Component
public class DescriptorImpl implements DescriptorBase {

    @Override
    public void printed() {
        System.out.println(this.getClass().getCanonicalName());
    }
    
}
