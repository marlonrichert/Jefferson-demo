package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.demo.simpleaddressbook.domain.Contact;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Table;

public final class ContactList extends SelectionControl<Contact> {
    public ContactList() {
        super("Contact list", Contact.class);
    }

    @Override
    public Table createFallback() {
        return new Table();
    }

    public void removeContact(Contact contact) {
        getRendition().getContainerDataSource().removeItem(contact);
    }

    public void addNewContact() {
        AbstractSelect rendition = getRendition();

        Contact contact = new Contact();

        @SuppressWarnings("unchecked")
        BeanItemContainer<Contact> container = (BeanItemContainer<Contact>)
                rendition.getContainerDataSource();
        container.addItemAt(0, contact);

        rendition.select(contact);
        if (rendition instanceof Table) {
            ((Table) rendition).setCurrentPageFirstItemId(contact);
        }
    }

    public void filter(Object propertyId, Filter filter) {
        @SuppressWarnings("unchecked")
        BeanItemContainer<Contact> container = (BeanItemContainer<Contact>)
                getRendition().getContainerDataSource();

        container.removeContainerFilters(propertyId);
        if (filter != null) {
            container.addContainerFilter(filter);
        }
    }
}