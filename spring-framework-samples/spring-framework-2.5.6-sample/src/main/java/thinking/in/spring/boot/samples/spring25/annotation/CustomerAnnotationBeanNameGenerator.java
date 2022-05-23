package thinking.in.spring.boot.samples.spring25.annotation;

import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.util.Map;
import java.util.Set;

/**
 * 自定义可扫描注解 Bean 名称生成器
 */
public class CustomerAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    // 增加自定义可扫描模式注解
    private static final String STRING_REPOSITORY_ANNOTATION_CLASSNAME = "thinking.in.spring.boot.samples.spring25.annotation.StringRepository";

    // 覆盖默认只判断 Component 注解，增加自定义模式注解的判断，使 StringRepository 注解的 value 属性方法具备指定自定义 BeanName 的语义
    @Override
    protected boolean isStereotypeWithNameValue(String annotationType, Set<String> metaAnnotationTypes, Map<String, Object> attributes) {
        // 先调用父类，不影响正常非自定义注解的判断
        boolean isStereotype = super.isStereotypeWithNameValue(annotationType, metaAnnotationTypes, attributes) || annotationType.equals(STRING_REPOSITORY_ANNOTATION_CLASSNAME) ||
                (metaAnnotationTypes != null && metaAnnotationTypes.contains(STRING_REPOSITORY_ANNOTATION_CLASSNAME));
        return (isStereotype && attributes != null && attributes.containsKey("value"));
    }
}
