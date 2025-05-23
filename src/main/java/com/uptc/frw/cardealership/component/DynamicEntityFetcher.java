package com.uptc.frw.cardealership.component;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class DynamicEntityFetcher {

    private final ApplicationContext applicationContext;

    public DynamicEntityFetcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object searchById(Class<?> entityClass, Object id) {
        String repositoryBeanName = getRepositoryBeanName(entityClass);

        try {
            Object repository = applicationContext.getBean(repositoryBeanName);

            Method findById = repository.getClass().getMethod("findById", Object.class);
            Optional<?> result = (Optional<?>) findById.invoke(repository, id);

            return result.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error buscando entidad para auditoría: " + e.getMessage(), e);
        }
    }

    private String getRepositoryBeanName(Class<?> entityClass) {
        String className = entityClass.getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1) + "Repository";
    }
}
