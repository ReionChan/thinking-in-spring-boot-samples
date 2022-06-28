package thinking.in.spring.boot.samples.spring5.context.event;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * {@link EventListener @EventListener} 监听多 Spring 事件的引导类，检验：
 * <ul>
 * <li>是否支持单一 {@link ApplicationContextEvent} 参数</li>
 * <li>是否支持</li>
 * </ul>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EventListener
 * @since 1.0.0
 */
public class AnnotatedEventListenerOnMultiEventsBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 @EventListener 类 MyMultiEventsListener
        context.register(MyMultiEventsListener.class);
        // 初始化上下文
        context.refresh();
        // 关闭上下文
        context.close();
    }

    /**
     * 具体 {@link EventListener}类，提供不同监听多 Spring 事件方法
     */
    public static class MyMultiEventsListener {

        // 设置无参形式
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onEvent() {
            System.out.println("onEvent");
        }

        // 设置单个参数，此参数类型为两个事件的父类
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onApplicationContextEvent(ApplicationContextEvent event) {
            System.out.println("onApplicationContextEvent : " + event.getClass().getSimpleName());
        }

        // 设置两个参数，报错：Maximum one parameter is allowed for event listener method
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onEvents(ContextRefreshedEvent refreshedEvent, ContextClosedEvent contextClosedEvent) {
            System.out.println("onEvents : " + refreshedEvent.getClass().getSimpleName()
                    + " , " + contextClosedEvent.getClass().getSimpleName());
        }
    }
}
