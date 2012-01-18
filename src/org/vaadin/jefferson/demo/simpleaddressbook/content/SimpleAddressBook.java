package org.vaadin.jefferson.demo.simpleaddressbook.content;

import java.util.Random;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class SimpleAddressBook extends SimpleComposite {
    public static final String ADD_CONTACT = "Add contact";
    public static final String REMOVE_CONTACT = "Remove contact";
    public static final String NAVIGATION = "Navigation";
    public static final String TOOLBAR = "Toolbar";

    static final String COMPANY = "Company";
    static final String FIRST_NAME = "First Name";
    static final String LAST_NAME = "Last Name";

    private IndexedContainer data = new IndexedContainer();

    private ContactEditor contactEditor = new ContactEditor();
    private ContactList contactList = new ContactList(
            data, new ContactListHandler());
    private ButtonView contactAdd = new ButtonView(
            ADD_CONTACT, new ContactAddHandler());
    private ButtonView contactRemove = new ContactRemove(
            REMOVE_CONTACT, new ContactRemoveHandler());
    private SimpleComposite nav = new SimpleComposite(NAVIGATION);
    private SimpleComposite toolbar = new SimpleComposite(TOOLBAR);

    public SimpleAddressBook() {
        super("Content root");

        setChildren(
                nav.setChildren(
                        contactList,
                        toolbar.setChildren(
                                contactAdd,
                                contactRemove,
                                new Filter(LAST_NAME, data),
                                new Filter(FIRST_NAME, data),
                                new Filter(COMPANY, data))),
                contactEditor);

        // Create dummy data by randomly combining first and last names
        String[] fields = {
                FIRST_NAME, LAST_NAME, COMPANY, "Mobile Phone", "Work Phone",
                "Home Phone", "Work Email", "Home Email", "Street", "Zip",
                "City", "State", "Country",
        };
        String[] firstNames = {
                "Peter", "Alice", "Joshua", "Mike", "Olivia", "Nina", "Alex",
                "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa", "Marge",
        };
        String[] lastNames = {
                "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons",
                "Verne", "Scott", "Allison", "Gates", "Rowling", "Barks",
                "Ross", "Schneider", "Tate",
        };
        for (String p : fields) {
            data.addContainerProperty(p, String.class, "");
        }
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Object id = data.addItem();
            data.getContainerProperty(id, FIRST_NAME).setValue(
                    firstNames[random.nextInt(firstNames.length)]);
            data.getContainerProperty(id, LAST_NAME).setValue(
                    lastNames[random.nextInt(lastNames.length)]);
        }
    }

    private class ContactListHandler implements Property.ValueChangeListener {
        public void valueChange(ValueChangeEvent event) {
            Table table = contactList.getRendition();
            Object id = table.getValue();
            contactEditor.getRendition().setItemDataSource(
                    id == null ? null : table.getItem(id));
            contactRemove.getRendition().setEnabled(id != null);
        }
    }

    private final static class ContactRemove extends ButtonView {
        private ContactRemove(String name, ClickListener listener) {
            super(name, listener);
        }

        @Override
        protected Button accept(Presentation presentation) {
            Button rendition = super.accept(presentation);
            rendition.setEnabled(false);
            return rendition;
        }
    }

    private class ContactRemoveHandler implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            Table table = contactList.getRendition();
            table.removeItem(table.getValue());
            table.select(null);
        }
    }

    private final class ContactAddHandler
            implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            Table table = contactList.getRendition();

            // Add new contact "John Doe" as the first item
            Object id = ((IndexedContainer) table
                    .getContainerDataSource()).addItemAt(0);
            table.getItem(id).getItemProperty(SimpleAddressBook.FIRST_NAME)
                    .setValue("John");
            table.getItem(id).getItemProperty(SimpleAddressBook.LAST_NAME)
                    .setValue("Doe");

            // Select the newly added item and scroll to the
            // item
            table.setValue(id);
            table.setCurrentPageFirstItemId(id);
        }
    }
}
