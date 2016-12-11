package org.active.services.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ServiceContext {

    private static ServiceContext serviceContext = null;

    private Map<String, Object> serviceList;

    private ServiceContext() {}
    
    public static ServiceContext getContext() {
    	if (serviceContext != null) {
    		return serviceContext;
    	}
    	serviceContext = new ServiceContext();
        serviceContext.serviceList = new HashMap<>();
        return serviceContext;
    }

    public <T> T getService(Class<T> clazz) {
        if (serviceList.get(clazz.getName()) != null) {
            return (T) serviceList.get(clazz.getName());
        }
        Object object = null;
        for (Map.Entry<String, Object> entry : serviceList.entrySet()) {
            if (clazz.equals(entry.getValue())) {
                object = entry.getValue();
                break;
            }
        }
        return clazz.cast(object);
    }

    public <T> T getService(String id) {
        return (T) serviceList.get(id);
    }

    protected <T> void setService(T t, String id) {
        if (id != null) {
            serviceList.put(id, t);
            return;
        }

        serviceList.put(t.getClass().getName(), t);
    }

}
