package com.kevin.simulate.spring.boot.component;

import com.kevin.simulate.spring.boot.configuration.AutoConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author kevin
 */
public class ImportSelector implements DeferredImportSelector {


  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    ServiceLoader<AutoConfiguration> serviceLoader = ServiceLoader.load(AutoConfiguration.class);
    List<String> list = new ArrayList<>();
    for (AutoConfiguration autoConfiguration : serviceLoader) {
      list.add(autoConfiguration.getClass().getName());
    }

    return list.toArray(new String[0]);
  }
}
