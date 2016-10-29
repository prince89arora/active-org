package org.active.web.init.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * Model map to transfer set of information from controller to
 * view template.
 *
 * @author princearora
 */
public class Model extends HashMap<String, Object> {

    public Model() {

    }

    public void addAttribute(String key, Object object) {
        super.put(key, object);
    }

    public void addAll(Map<String, Object> map) {
        super.putAll(map);
    }
}
