package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.demo.simpleaddressbook.domain.Contact;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class ContactRemoveControl extends ButtonControl {
    private Contact contact;
    public ContactList contactList;

    ContactRemoveControl(ContactList contactList) {
        super(SimpleAddressBookView.REMOVE_CONTACT);
        this.contactList = contactList;
        setListener(new ContactRemoveHandler());
    }

    public void setContact(Contact contact) {
        Button rendition = getRendition();
        if (rendition != null) {
            rendition.setEnabled(contact != null);
        }
        this.contact = contact;
    }

    @Override
    protected Button accept(Presentation presentation) {
        Button rendition = super.accept(presentation);
        setContact(contact);
        return rendition;
    }

    private class ContactRemoveHandler implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            contactList.removeContact(contactList.getSelection()[0]);
        }
    }

}