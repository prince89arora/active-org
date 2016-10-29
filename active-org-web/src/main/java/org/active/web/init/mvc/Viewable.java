package org.active.web.init.mvc;

/**
 * Viewable to hold template related information.
 *
 * @author princearora
 */
public class Viewable {

    private String template;
    private Model model;
    private String fullTemplatePath;
    private boolean isResolved;

    public Viewable(String template, Model model) {
        this.model =  model;
        this.template = template;
    }

    public Model getModel() {
        return this.model;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getFullTemplatePath() {
        return this.fullTemplatePath;
    }

    public void setFullTemplatePath(String fullTemplatePath) {
        this.fullTemplatePath = fullTemplatePath;
    }

    public boolean isResolved() {
        return this.isResolved;
    }

    public void setResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }
}
