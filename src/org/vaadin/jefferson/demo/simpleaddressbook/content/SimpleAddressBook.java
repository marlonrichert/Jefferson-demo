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

    private IndexedContainer data = createDummyData();

    private ContactEditor contactEditor = new ContactEditor();
    private ContactList contactList = new ContactList(
            data, new ContactListHandler());
    private ButtonView contactRemove = new ContactRemove(
            REMOVE_CONTACT, new ContactRemoveHandler());

    public SimpleAddressBook() {
        super("Content root");

        setChildren(
                new SimpleComposite(NAVIGATION,
                        contactList,
                        new SimpleComposite(TOOLBAR,
                                new ButtonView(ADD_CONTACT,
                                        new ContactAddHandler()),
                                contactRemove,
                                new Filter(FIRST_NAME, data),
                                new Filter(LAST_NAME, data),
                                new Filter(COMPANY, data))),
                contactEditor);
    }

    private static IndexedContainer createDummyData() {
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

        IndexedContainer container = new IndexedContainer();
        for (String p : fields) {
            container.addContainerProperty(p, String.class, "");
        }

        // Create dummy data by randomly combining first and last names
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Object id = container.addItem();
            container.getContainerProperty(id, FIRST_NAME).setValue(
                    firstNames[random.nextInt(firstNames.length)]);
            container.getContainerProperty(id, LAST_NAME).setValue(
                    lastNames[random.nextInt(lastNames.length)]);
        }

        return container;
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

    private final class ContactAddHandler implements
            Button.ClickListener {
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
}
