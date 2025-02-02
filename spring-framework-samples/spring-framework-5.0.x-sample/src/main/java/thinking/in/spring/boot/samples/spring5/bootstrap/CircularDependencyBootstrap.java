/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package thinking.in.spring.boot.samples.spring5.bootstrap;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import thinking.in.spring.boot.samples.spring5.bean.ServiceA;

/**
 * {@link CircularDependencyBootstrap} 引导类
 *
 * @since 1.0.0
 */
@Component
@ComponentScan(basePackageClasses = ServiceA.class)
@EnableAspectJAutoProxy
public class CircularDependencyBootstrap {

    public static void main(String[] args) {
        // 注册当前引导类作为 Configuration Class
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CircularDependencyBootstrap.class);
        // 获取 Bean
        ServiceA serviceA = (ServiceA) context.getBean("serviceA");
        serviceA.print();
        context.close();
    }
}



