package org.active.web.init.exception;

/**
 * Exception for missing template file.
 * 
 * @author princearora
 */
public class TemplateNotFoundException extends Exception {

    private String path;
    private Throwable throwable;

    public TemplateNotFoundException(String path, Throwable throwable) {
        super(throwable);
        this.path = path;
        this.throwable = throwable;
    }

    public TemplateNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public TemplateNotFoundException(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return String.format("Cannot find template file at location %s Exception %s",
                this.path, this.throwable);
    }

    public String getMessage() {
        return String.format("Cannot find template file at location %s Exception %s",
                this.path, this.throwable);
    }
}
