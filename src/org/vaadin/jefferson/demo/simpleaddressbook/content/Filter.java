package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.TextView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractTextField;

public class Filter extends TextView {
    private IndexedContainer data;

    public Filter(String name, IndexedContainer data) {
        super(name);
        this.data = data;
    }

    @Override
    protected AbstractTextField accept(Presentation p) {
        final AbstractTextField rendition = super.accept(p);
        rendition.setImmediate(true);

        rendition.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                String name = getName();

                data.removeContainerFilters(name);

                String value = rendition.toString();
                if (value.length() > 0 && !name.equals(value)) {
                    data.addContainerFilter(
                            name, value, true, false);
                }

                rendition.getWindow().showNotification(
                        data.size() + " matches found");
            }
        });
        return rendition;
    }
}