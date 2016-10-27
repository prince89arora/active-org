package org.active.web.init.mvc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Model implements Serializable {

    private Map<String, Object> objectMap = null;

    public Model() {
        this.objectMap = new HashMap<String, Object>();
    }

    public void set(String key, Object value) {
        this.objectMap.put(key, value);
    }

    public Object get(String key) {
        return this.objectMap.get(key);
    }
}
