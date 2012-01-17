package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.demo.addressbook.content.AddressBook;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.Application;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class AddressBookDemo extends Application {
    private PersonContainer dataSource = PersonContainer.createWithTestData();

    @Override
    public void init() {
        setMainWindow(new Window("Address Book Demo application"));

        setTheme("contacts");

        getMainWindow().setContent(
                new AddressBookPresentation().visit(
                        new AddressBook(dataSource)));
    }
}
