package org.vaadin.jefferson.demo.simpleaddressbook;

import org.vaadin.jefferson.demo.simpleaddressbook.content.SimpleAddressBook;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class SimpleAddressBookDemo extends Application {

    @Override
    public void init() {
        setMainWindow(new Window("Address Book",
                new SimpleAddressBookPresentation().visit(
                        new SimpleAddressBook())));
    }
}
