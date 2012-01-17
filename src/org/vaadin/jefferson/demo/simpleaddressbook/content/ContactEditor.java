package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;

import com.vaadin.ui.Form;

public final class ContactEditor extends View<Form> {
    ContactEditor() {
        super("Contact editor", Form.class);
    }

    @Override
    public Form createFallback() {
        return new Form();
    }

    @Override
    protected Form accept(Presentation p) {
        Form rendition = super.accept(p);
        rendition.setImmediate(true);
        return rendition;
    }
}