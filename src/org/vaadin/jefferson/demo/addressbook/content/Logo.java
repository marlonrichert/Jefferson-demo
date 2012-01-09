package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;

import com.vaadin.ui.Embedded;

public class Logo extends View<Embedded> {
    Logo(String name, Class<Embedded> base) {
        super(name, base);
    }

    @Override
    public Embedded createFallback() {
        return new Embedded();
    }
}