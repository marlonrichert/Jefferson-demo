package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.content.TextControl;

import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;

public class FilterControl extends TextControl {
    private ContactList contactList;

    FilterControl(String name, ContactList contactList) {
        super(name);
        setListener(new FilterChangeHandler());
        this.contactList = contactList;
    }

    private class FilterChangeHandler implements TextChangeListener {
        public void textChange(TextChangeEvent event) {
            String text = event.getText().trim();
            String name = getName();
            contactList.filter(name,
                    text.isEmpty()
                            ? null
                            : new SimpleStringFilter(name, text, true, false));
        }
    }

}