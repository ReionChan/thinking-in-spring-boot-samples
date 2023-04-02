package thinking.in.spring.boot.samples.spring5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import thinking.in.spring.boot.samples.spring5.bean.ServiceB;

@Component
public class ServiceA {

    @Autowired
    private ServiceB serviceB;

    public void print() {
        System.out.println(this);
        System.out.println(serviceB);
    }
}
