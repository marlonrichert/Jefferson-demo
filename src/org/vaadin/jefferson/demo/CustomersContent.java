package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.Composite;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

public class CustomersContent extends Composite<ComponentContainer> {

    public CustomersContent() {
        super("root", ComponentContainer.class, CssLayout.class);
    }
}
