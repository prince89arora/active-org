package org.active.services.core;

import org.active.services.annotations.Service;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.Set;

/**
 *
 */
public class ContextLoader {

    private static final Logger log = Logger.getLogger(ContextLoader.class);

    private static ServiceContext serviceContext;

    public static void loadServices(String servicePackage) {
        serviceContext = ServiceContext.getContext();
        log.info("....Starting Service scanning....");
        getAllService(servicePackage);
    }

    private static void getAllService(String servicePackage) {
        Reflections reflections = new Reflections(servicePackage);
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Service.class);
        classSet.forEach(aClass -> {
            try {
                serviceContext.setService(aClass.newInstance(), null);
                log.info("Added service instance for -> "+ aClass.getName());
            } catch (InstantiationException | IllegalAccessException ex) {
                log.error("Cannot include service instance for -> "+ aClass.getName());
            }
        });
    }
}
