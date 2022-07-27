package com.kevin.simulate.spring.boot.annotation;

import java.util.Map;
import java.util.Objects;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author kevin
 */
public class OnClassCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Map<String, Object> annotationAttributes =
        metadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
    Objects.requireNonNull(annotationAttributes);
    String className = (String) annotationAttributes.get("value");

    try {
      ClassLoader classLoader = context.getClassLoader();
      Objects.requireNonNull(classLoader);
      classLoader.loadClass(className);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }
}
