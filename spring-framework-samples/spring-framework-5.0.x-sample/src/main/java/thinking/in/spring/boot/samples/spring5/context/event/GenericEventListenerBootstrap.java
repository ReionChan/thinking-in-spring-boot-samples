package thinking.in.spring.boot.samples.spring5.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * 泛型 {@link ApplicationEvent 事件} 监听引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationEvent
 * @see ApplicationListener
 * @see EventListener
 * @see ResolvableType
 * @since 1.0.0
 */
public class GenericEventListenerBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 UserEventListener，即实现 ApplicationListener ，也包含 @EventListener 方法
        context.register(UserEventListener.class);
        // 初始化上下文
        context.refresh();
        // 构造泛型事件
        GenericEvent<User> event = new GenericEvent(new User("小马哥"));
        // 发送泛型事件
        context.publishEvent(event);
        // 发送 User 对象作为事件源
        context.publishEvent(new User("mercyblitz"));
        // 关闭上下文
        context.close();
    }

public static class UserEventListener implements ApplicationListener<GenericEvent<User>> {

    @EventListener
    // 注解形式监听非事件类型的对象
    public void onUser(User user) {
        System.out.println("onUser : " + user);
    }

    @EventListener
    // 注解形式监听自定义的泛型事件
    public void onUserEvent(GenericEvent<User> event) {
        System.out.println("onUserEvent : " + event.getSource());
    }

    @Override
    // 接口形式监听自定义的泛型事件
    public void onApplicationEvent(GenericEvent<User> event) {
        System.out.println("onApplicationEvent : " + event.getSource());
    }

/*
    @Override
    // 接口形式监听非时间类型的对象，借由 PayloadApplicationEvent
    // 【注意】需要将 UserEventListener 的 implements 修改为 ApplicationListener<PayloadApplicationEvent>
    public void onApplicationEvent(PayloadApplicationEvent event) {
        System.out.println("onApplicationEvent : " + event.getPayload());
    }
 */
}

    /**
     * 用户实体类
     */
    public static class User {

        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "\'}";
        }
    }

    /**
     * 泛型事件
     *
     * @param <T> 泛型类型
     */
    // PayloadApplicationEvent 也实现 ResolvableTypeProvider 来达到提取泛型参数实际类型
    public static class GenericEvent<T>
            extends ApplicationEvent implements ResolvableTypeProvider {

        public GenericEvent(T source) {
            super(source);
        }

        @Override
        public ResolvableType getResolvableType() {
            return ResolvableType.forClassWithGenerics(getClass(),
                    ResolvableType.forInstance(getSource()));
        }

        @Override
        public T getSource() {
            return (T) super.getSource();
        }
    }
}
