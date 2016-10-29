package org.active.web.init.mvc;

/**
 * Configuration pojo for view resolver.
 *
 * @author princearora
 */
public class ViewResolverConfig {

    private final String basePath;
    private final String extension;

    public ViewResolverConfig(String basePath, String extension) {
        this.basePath = basePath;
        this.extension = extension;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public String getExtension() {
        return this.extension;
    }

}
