package org.active.services.ref;

import org.active.services.annotations.Service;

/**
 * Created by princearora on 10/12/16.
 */
@Service
public class TestService {

    private String name = "myname";

    public String getName() {
        return name;
    }
}
