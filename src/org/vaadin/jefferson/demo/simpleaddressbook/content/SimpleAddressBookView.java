package org.vaadin.jefferson.demo.simpleaddressbook.content;

import java.util.Random;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.simpleaddressbook.domain.Contact;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class SimpleAddressBookView extends SimpleComposite {
    private static final int NR_OF_CONTACTS = 1000;
    public static final String ADD_CONTACT = "Add contact";
    public static final String REMOVE_CONTACT = "Remove contact";
    public static final String NAVIGATION = "Navigation";
    public static final String TOOLBAR = "Toolbar";

    private ContactEditor contactEditor = new ContactEditor();
    private ContactList contactList = new ContactList();
    private ButtonControl contactAdd = new ButtonControl(
            ADD_CONTACT, new ContactAddHandler());
    private ContactRemoveControl contactRemoveControl = new ContactRemoveControl(
            contactList);
    private SimpleComposite nav = new SimpleComposite(NAVIGATION);
    private SimpleComposite toolbar = new SimpleComposite(TOOLBAR);

    public SimpleAddressBookView() {
        super("Content root");

        setChildren(
                nav.setChildren(
                        contactList,
                        toolbar.setChildren(
                                contactAdd,
                                contactRemoveControl,
                                new FilterControl(
                                        Contact.LAST_NAME, contactList),
                                new FilterControl(
                                        Contact.FIRST_NAME, contactList),
                                new FilterControl(
                                        Contact.COMPANY, contactList))),
                contactEditor);

        // Create dummy data by randomly combining first and last names
        String[] firstNames = {
                "Peter", "Alice", "Joshua", "Mike", "Olivia", "Nina", "Alex",
                "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa", "Marge",
        };
        String[] lastNames = {
                "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons",
                "Verne", "Scott", "Allison", "Gates", "Rowling", "Barks",
                "Ross", "Schneider", "Tate",
        };
        Random random = new Random();
        Contact[] contacts = new Contact[NR_OF_CONTACTS];
        for (int i = 0; i < NR_OF_CONTACTS; i++) {
            contacts[i] = new Contact();
            contacts[i].setFirstName(firstNames[random
                    .nextInt(firstNames.length)]);
            contacts[i]
                    .setLastName(lastNames[random.nextInt(lastNames.length)]);
        }

        contactList.setChoices(contacts);
        contactList.setListener(new ContactListHandler());
    }

    private class ContactListHandler implements Property.ValueChangeListener {
        public void valueChange(ValueChangeEvent event) {
            Contact contact = contactList.getSelection()[0];
            contactEditor.setContact(contactList.getModel().getItem(contact));
            contactRemoveControl.setContact(contact);
        }
    }

    private final class ContactAddHandler implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            contactList.addNewContact();
        }
    }
}
