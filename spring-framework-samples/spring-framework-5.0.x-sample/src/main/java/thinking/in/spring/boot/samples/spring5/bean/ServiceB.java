package thinking.in.spring.boot.samples.spring5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void print() {
        System.out.println(serviceA);
    }
}
