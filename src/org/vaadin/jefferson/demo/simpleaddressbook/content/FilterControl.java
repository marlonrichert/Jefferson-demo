package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.content.TextControl;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;

public class FilterControl extends TextControl {
    FilterControl(String name, IndexedContainer data) {
        super(name);
        setListener(new TextChangeHandler(name, data));
    }

    private final static class TextChangeHandler implements TextChangeListener {
        private final String name;
        private final IndexedContainer data;

        private TextChangeHandler(String name, IndexedContainer data) {
            this.name = name;
            this.data = data;
        }

        public void textChange(TextChangeEvent event) {
            data.removeContainerFilters(name);

            String text = event.getText();
            if (text.length() > 0 && !name.equals(text)) {
                data.addContainerFilter(name, text, true, false);
            }
        }
    }
}