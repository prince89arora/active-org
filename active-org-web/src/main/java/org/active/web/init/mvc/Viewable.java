package org.active.web.init.mvc;

/**
 *
 */
public class Viewable {

    private String template;
    private Model model;
    private boolean renderable;

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

    public boolean isRenderable() {
        return this.renderable;
    }

    public void setRenderable(boolean renderable) {
        this.renderable = renderable;
    }
}
