package org.active.web.init.mvc;

/**
 *
 */
public enum ViewResolverConfig {

    JSP;

    private String basePath;

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
