package thinking.in.spring.boot.samples.spring5.context.event;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link EventListener @EventListener} {@link Async 异步}监听方法引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EventListener
 * @see Async
 * @since 1.0.0
 */
public class AnnotatedAsyncEventListenerBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 异步 @EventListener 类 MyAsyncEventListener
        context.register(MyAsyncEventListener.class);
        println(" Spring 应用上下文正在初始化...");
        // 初始化上下文
        context.refresh();
        // 关闭上下文
        context.close();
    }

    @EnableAsync() // 需要激活异步，否则 @Async 无效
    public static class MyAsyncEventListener {
        /*
        @EventListener(ContextRefreshedEvent.class)
        @Async
        // 1. 原始类型返回值的方法
        // 运行期 AopInvocationException 异常：Null return value from advice does not match primitive return type
        public boolean onPrimitiveTypeContextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener on primitive boolean type: " + event.getClass().getSimpleName());
            return true;
        }
        */

        @EventListener(ContextRefreshedEvent.class)
        @Async
        // 2. 装箱后的返回值类型的方法
        public Boolean onContextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener on boxing Boolean type: " + event.getClass().getSimpleName());
            return true;
        }

        @EventListener(ContextRefreshedEvent.class)
        @Async
        // 3. protected 访问修饰符的方法
        protected void onProtectedContextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener on protected Method: " + event.getClass().getSimpleName());
        }

        @EventListener(ContextRefreshedEvent.class)
        @Async
        // 4. default 的方法
        void onDefaultContextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener on default Method: " + event.getClass().getSimpleName());
        }

        /*
        @EventListener(ContextRefreshedEvent.class)
        @Async
        // 5. private 访问修饰符的方法
        // 编译报错：@Async 要求方法必须能被重写，private 或 static 方法不支持
        private void onPrivateContextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener on private Method: " + event.getClass().getSimpleName());
        }
         */

    }

    /**
     * 输出内容并附加当前线程信息
     *
     * @param content 输出内容
     */
    private static void println(String content) {
        // 当前线程名称
        String threadName = Thread.currentThread().getName();
        System.out.println("[ 线程 " + threadName + " ] : " + content);
    }
}
