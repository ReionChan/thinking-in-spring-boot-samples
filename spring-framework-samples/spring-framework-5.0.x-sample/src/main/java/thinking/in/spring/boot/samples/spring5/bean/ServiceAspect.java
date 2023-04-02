package thinking.in.spring.boot.samples.spring5.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {

    @Before("execution(public void thinking.in.spring.boot.samples.spring5.bean.ServiceA.print())")
    public void before(JoinPoint joinPoint) {
        System.out.println("----- before ------");
    }
}
