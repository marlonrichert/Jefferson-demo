package org.vaadin.jefferson.demo.simpleaddressbook.content;

import java.util.*;

import org.vaadin.jefferson.*;
import org.vaadin.jefferson.demo.simpleaddressbook.domain.*;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.*;
import com.vaadin.ui.*;

@SuppressWarnings("deprecation")
public final class ContactEditor extends Control<Form, ValueChangeListener> {
    private BeanItem<Contact> contact;

    ContactEditor() {
        super("Contact editor", Form.class, ValueChangeListener.class);
    }

    @Override
    public Form createFallback() {
        return new Form();
    }

    @Override
    protected Form accept(Presentation p) {
        setContact(contact);
        return super.accept(p);
    }

    public void setContact(BeanItem<Contact> contact) {
        Form rendition = getRendition();
        if (rendition != null) {
            rendition.setItemDataSource(
                    contact,
                    Arrays.asList(Contact.PROPERTIES));
        }
        this.contact = contact;
    }

    public BeanItem<Contact> getContact() {
        return contact;
    }
}